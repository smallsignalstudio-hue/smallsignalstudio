#!/usr/bin/env python3
"""Build landscape A4 PDF with 300 + Berlin 10 Einbürgerungstest questions (no answers)."""

from __future__ import annotations

import json
import re
from pathlib import Path

from reportlab.lib.pagesizes import A4, landscape
from reportlab.lib.units import mm
from reportlab.pdfbase import pdfmetrics
from reportlab.pdfbase.ttfonts import TTFont
from reportlab.pdfgen import canvas

ROOT = Path(__file__).resolve().parent
SOURCE_CANDIDATES = [
    ROOT / "katalog_text.txt",
    Path("/tmp/einbuergerung/katalog_text_clean.txt"),
    Path(
        "/home/ubuntu/.cursor/projects/agent-repos-smallsignalstudio/"
        "agent-tools/e4cdcd1f-ab45-41ce-a7bf-5cd636253034.txt"
    ),
]
OUT_PDF = ROOT / "einbuergerungstest-fragen-berlin.pdf"
OUT_JSON = ROOT / "fragen-berlin.json"

IMAGE_HINT_NUMBERS = {
    21,
    55,
    70,
    181,
    209,
    216,
    226,
    235,
}


def load_source_text() -> str:
    for path in SOURCE_CANDIDATES:
        if path.exists():
            return path.read_text(encoding="utf-8")
    raise FileNotFoundError("No BAMF catalog text source found")


def extract_questions(section: str) -> list[tuple[int, str]]:
    parts = re.split(r"\n?Aufgabe\s+(\d+)\n?", section)
    questions: list[tuple[int, str]] = []
    i = 1
    while i < len(parts) - 1:
        num = int(parts[i])
        body = parts[i + 1]
        q_lines: list[str] = []
        for line in body.strip().split("\n"):
            s = line.strip()
            if not s:
                continue
            if s.startswith(("", "□", "©", "Teil II")):
                break
            if re.match(r"^Bild \d+", s):
                break
            if "" in s or "□" in s:
                idx = min(i for i in (s.find(""), s.find("□")) if i >= 0)
                before = s[:idx].strip()
                if before:
                    q_lines.append(before)
                break
            q_lines.append(s)
        q = re.sub(r"\s+", " ", " ".join(q_lines)).strip()
        q = re.sub(r"\s*©.*$", "", q).strip()
        questions.append((num, q))
        i += 2
    return questions


def parse_catalog(text: str) -> list[dict]:
    m_bw = re.search(r"Teil II Fragen für das Bundesland Baden-Württemberg\n", text)
    m_berlin = re.search(r"Teil II Fragen für das Bundesland Berlin\n", text)
    m_branden = re.search(r"Teil II Fragen für das Bundesland Brandenburg\n", text)
    if not (m_bw and m_berlin and m_branden):
        raise ValueError("Could not locate catalog sections")

    general = extract_questions(text[: m_bw.start()])
    berlin = extract_questions(text[m_berlin.start() : m_branden.start()])
    if len(general) != 300:
        raise ValueError(f"Expected 300 general questions, got {len(general)}")
    if len(berlin) != 10:
        raise ValueError(f"Expected 10 Berlin questions, got {len(berlin)}")

    items: list[dict] = []
    for num, q in general:
        text_q = q
        if num in IMAGE_HINT_NUMBERS or "dieses Bild" in q or "Wappen" in q and num in {
            21,
            209,
        }:
            if "[Bild]" not in text_q:
                text_q = f"{text_q} [Bild]"
        items.append(
            {
                "nr": num,
                "section": "allgemein",
                "frage": text_q,
            }
        )

    berlin_image = {1, 8}
    for num, q in berlin:
        text_q = q
        if num in berlin_image and "[Bild]" not in text_q:
            text_q = f"{text_q} [Bild]"
        items.append(
            {
                "nr": 300 + num,
                "section": "berlin",
                "landes_nr": num,
                "frage": text_q,
            }
        )
    return items


def register_font() -> str:
    candidates = [
        "/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf",
        "/usr/share/fonts/truetype/liberation/LiberationSans-Regular.ttf",
        "/usr/share/fonts/truetype/freefont/FreeSans.ttf",
    ]
    bold_candidates = [
        "/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf",
        "/usr/share/fonts/truetype/liberation/LiberationSans-Bold.ttf",
        "/usr/share/fonts/truetype/freefont/FreeSansBold.ttf",
    ]
    for regular, bold in zip(candidates, bold_candidates):
        if Path(regular).exists() and Path(bold).exists():
            pdfmetrics.registerFont(TTFont("Body", regular))
            pdfmetrics.registerFont(TTFont("Body-Bold", bold))
            return "Body"
    return "Helvetica"


def wrap_text(c: canvas.Canvas, text: str, font: str, size: float, max_width: float) -> list[str]:
    words = text.split()
    lines: list[str] = []
    current = ""
    for word in words:
        trial = word if not current else f"{current} {word}"
        if c.stringWidth(trial, font, size) <= max_width:
            current = trial
        else:
            if current:
                lines.append(current)
            current = word
    if current:
        lines.append(current)
    return lines or [""]


def draw_cover(c: canvas.Canvas, font: str, page_w: float, page_h: float) -> None:
    margin = 18 * mm
    c.setStrokeColorRGB(0.12, 0.18, 0.28)
    c.setLineWidth(1.4)
    c.rect(margin, margin, page_w - 2 * margin, page_h - 2 * margin)

    c.setFillColorRGB(0.12, 0.18, 0.28)
    c.setFont(f"{font}-Bold" if font != "Helvetica" else "Helvetica-Bold", 28)
    c.drawCentredString(page_w / 2, page_h / 2 + 28, "Einbürgerungstest")

    c.setFont(font if font != "Helvetica" else "Helvetica", 16)
    c.drawCentredString(page_w / 2, page_h / 2 - 4, "310 Fragen · nur Fragen · Berlin")

    c.setFont(font if font != "Helvetica" else "Helvetica", 10)
    c.setFillColorRGB(0.35, 0.38, 0.42)
    c.drawCentredString(
        page_w / 2,
        page_h / 2 - 32,
        "300 allgemeine Fragen + 10 Fragen für das Bundesland Berlin",
    )
    c.drawCentredString(
        page_w / 2,
        page_h / 2 - 48,
        "Quelle: BAMF-Gesamtfragenkatalog „Leben in Deutschland“ / Einbürgerungstest (Stand 07.05.2025)",
    )
    c.drawCentredString(
        page_w / 2,
        margin + 12,
        "Querformat A4 · ca. 4 Fragen pro Seite · ohne Antwortoptionen",
    )
    c.showPage()


def draw_question_box(
    c: canvas.Canvas,
    *,
    x: float,
    y: float,
    w: float,
    h: float,
    item: dict,
    font: str,
) -> None:
    c.setStrokeColorRGB(0.22, 0.26, 0.32)
    c.setFillColorRGB(0.98, 0.98, 0.97)
    c.setLineWidth(0.9)
    c.roundRect(x, y, w, h, 4, stroke=1, fill=1)

    pad = 5 * mm
    label = f"Frage {item['nr']}"
    if item["section"] == "berlin":
        label = f"Berlin {item['landes_nr']} · Frage {item['nr']}"

    c.setFillColorRGB(0.12, 0.18, 0.28)
    c.setFont(f"{font}-Bold" if font != "Helvetica" else "Helvetica-Bold", 9)
    c.drawString(x + pad, y + h - 7 * mm, label)

    c.setStrokeColorRGB(0.82, 0.84, 0.86)
    c.setLineWidth(0.5)
    c.line(x + pad, y + h - 8.5 * mm, x + w - pad, y + h - 8.5 * mm)

    body_font = font if font != "Helvetica" else "Helvetica"
    size = 11
    max_width = w - 2 * pad
    lines = wrap_text(c, item["frage"], body_font, size, max_width)
    # Shrink if needed to fit the box
    while len(lines) * (size + 3) > h - 16 * mm and size > 8:
        size -= 0.5
        lines = wrap_text(c, item["frage"], body_font, size, max_width)

    c.setFillColorRGB(0.1, 0.1, 0.12)
    c.setFont(body_font, size)
    text_top = y + h - 14 * mm
    leading = size + 3
    for i, line in enumerate(lines):
        c.drawString(x + pad, text_top - i * leading, line)


def build_pdf(items: list[dict]) -> None:
    font = register_font()
    page_w, page_h = landscape(A4)
    c = canvas.Canvas(str(OUT_PDF), pagesize=landscape(A4))
    c.setTitle("Einbürgerungstest Fragen – Berlin (310)")
    c.setAuthor("BAMF Fragenkatalog (Stand 07.05.2025)")

    draw_cover(c, font, page_w, page_h)

    margin_x = 10 * mm
    margin_y = 10 * mm
    gap_x = 5 * mm
    gap_y = 5 * mm
    header_h = 8 * mm
    usable_w = page_w - 2 * margin_x
    usable_h = page_h - 2 * margin_y - header_h
    box_w = (usable_w - gap_x) / 2
    box_h = (usable_h - gap_y) / 2

    per_page = 4
    total_pages = (len(items) + per_page - 1) // per_page

    for page_idx in range(total_pages):
        chunk = items[page_idx * per_page : (page_idx + 1) * per_page]
        # Header
        c.setFillColorRGB(0.12, 0.18, 0.28)
        c.setFont(f"{font}-Bold" if font != "Helvetica" else "Helvetica-Bold", 9)
        c.drawString(margin_x, page_h - margin_y - 4 * mm, "Einbürgerungstest · Fragen ohne Antworten")
        c.setFont(font if font != "Helvetica" else "Helvetica", 8)
        c.setFillColorRGB(0.4, 0.42, 0.45)
        c.drawRightString(
            page_w - margin_x,
            page_h - margin_y - 4 * mm,
            f"Seite {page_idx + 1} / {total_pages}",
        )

        positions = [
            (margin_x, page_h - margin_y - header_h - box_h),
            (margin_x + box_w + gap_x, page_h - margin_y - header_h - box_h),
            (margin_x, margin_y),
            (margin_x + box_w + gap_x, margin_y),
        ]
        for item, (x, y) in zip(chunk, positions):
            draw_question_box(c, x=x, y=y, w=box_w, h=box_h, item=item, font=font)
        c.showPage()

    c.save()


def main() -> None:
    if OUT_JSON.exists():
        items = json.loads(OUT_JSON.read_text(encoding="utf-8"))
        if len(items) != 310:
            raise ValueError(f"Expected 310 questions in JSON, got {len(items)}")
    else:
        text = load_source_text()
        items = parse_catalog(text)
        OUT_JSON.write_text(json.dumps(items, ensure_ascii=False, indent=2), encoding="utf-8")
        print(f"JSON -> {OUT_JSON}")

    build_pdf(items)
    print(f"Wrote {len(items)} questions -> {OUT_PDF}")


if __name__ == "__main__":
    main()
