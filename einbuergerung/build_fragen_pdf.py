#!/usr/bin/env python3
"""Build landscape A4 PDF from BAMF catalog clips (question + options + images)."""

from __future__ import annotations

import re
from pathlib import Path

import fitz  # PyMuPDF

ROOT = Path(__file__).resolve().parent
KATALOG_CANDIDATES = [
    Path("/tmp/einbuergerung/katalog.pdf"),
    ROOT / "katalog.pdf",
]
OUT_PDF = ROOT / "einbuergerungstest-fragen-berlin.pdf"
OUT_PDF_8UP = ROOT / "einbuergerungstest-fragen-berlin-8up.pdf"
ARTIFACT_PDF = Path("/opt/cursor/artifacts/einbuergerungstest-fragen-berlin.pdf")
ARTIFACT_PDF_8UP = Path("/opt/cursor/artifacts/einbuergerungstest-fragen-berlin-8up.pdf")
BAMF_LOGO_PATH = ROOT / "bamf-logo.png"

# BAMF page geometry (A4 portrait)
CONTENT_LEFT = 55.0
CONTENT_RIGHT = 545.0
FOOTER_Y = 748.0
CLIP_PAD_TOP = 2.0
CLIP_PAD_BEFORE_NEXT = 6.0
RENDER_SCALE = 2.0  # 144 dpi-ish


def find_katalog() -> Path:
    for path in KATALOG_CANDIDATES:
        if path.exists():
            return path
    raise FileNotFoundError("BAMF katalog.pdf not found")


def find_section_pages(doc: fitz.Document) -> tuple[int, int, int]:
    bw = berlin = branden = None
    for i, page in enumerate(doc):
        text = page.get_text() or ""
        if bw is None and "Fragen für das Bundesland Baden-Württemberg" in text:
            bw = i
        if berlin is None and "Fragen für das Bundesland Berlin" in text:
            berlin = i
        if branden is None and "Fragen für das Bundesland Brandenburg" in text:
            branden = i
    if bw is None or berlin is None or branden is None:
        raise ValueError("Could not locate Bundesland sections in BAMF PDF")
    return bw, berlin, branden


def collect_aufgaben(doc: fitz.Document, page_start: int, page_end: int) -> list[dict]:
    items: list[dict] = []
    for i in range(page_start, page_end):
        page = doc[i]
        for block in page.get_text("dict")["blocks"]:
            if block.get("type") != 0:
                continue
            for line in block.get("lines", []):
                text = "".join(span["text"] for span in line["spans"]).strip()
                match = re.match(r"^Aufgabe\s+(\d+)$", text)
                if match:
                    items.append(
                        {
                            "page": i,
                            "nr": int(match.group(1)),
                            "y": line["bbox"][1],
                        }
                    )
    return items


def clip_rect_for(items: list[dict], idx: int) -> fitz.Rect:
    item = items[idx]
    y0 = item["y"] - CLIP_PAD_TOP
    if idx + 1 < len(items) and items[idx + 1]["page"] == item["page"]:
        y1 = items[idx + 1]["y"] - CLIP_PAD_BEFORE_NEXT
    else:
        y1 = FOOTER_Y
    return fitz.Rect(CONTENT_LEFT, y0, CONTENT_RIGHT, y1)


def tighten_clip(page: fitz.Page, clip: fitz.Rect) -> fitz.Rect:
    """Shrink clip to actual drawn/text content inside the Aufgabe region."""
    blocks = page.get_text("dict", clip=clip)["blocks"]
    if not blocks:
        return clip
    x0 = clip.x1
    y0 = clip.y1
    x1 = clip.x0
    y1 = clip.y0
    found = False
    for block in blocks:
        bx0, by0, bx1, by1 = block["bbox"]
        # ignore near-empty
        if by1 - by0 < 1 or bx1 - bx0 < 1:
            continue
        found = True
        x0 = min(x0, bx0)
        y0 = min(y0, by0)
        x1 = max(x1, bx1)
        y1 = max(y1, by1)
    if not found:
        return clip
    # keep full content width for consistent alignment, only tighten vertically a bit
    pad = 4.0
    return fitz.Rect(
        clip.x0,
        max(clip.y0, y0 - pad),
        clip.x1,
        min(clip.y1, y1 + pad),
    )


def render_clip(page: fitz.Page, clip: fitz.Rect) -> fitz.Pixmap:
    mat = fitz.Matrix(RENDER_SCALE, RENDER_SCALE)
    return page.get_pixmap(matrix=mat, clip=clip, alpha=False)


def build_question_pixmaps(doc: fitz.Document) -> list[dict]:
    bw, berlin_page, branden = find_section_pages(doc)
    general_items = collect_aufgaben(doc, 1, bw)
    berlin_items = collect_aufgaben(doc, berlin_page, branden)
    if len(general_items) != 300:
        raise ValueError(f"Expected 300 general Aufgaben, got {len(general_items)}")
    if len(berlin_items) != 10:
        raise ValueError(f"Expected 10 Berlin Aufgaben, got {len(berlin_items)}")

    questions: list[dict] = []

    def add_group(items: list[dict], section: str) -> None:
        for idx, item in enumerate(items):
            page = doc[item["page"]]
            clip = tighten_clip(page, clip_rect_for(items, idx))
            pix = render_clip(page, clip)
            questions.append(
                {
                    "section": section,
                    "nr": item["nr"],
                    "display_nr": item["nr"] if section == "allgemein" else 300 + item["nr"],
                    "pix": pix,
                    "width": pix.width,
                    "height": pix.height,
                }
            )

    add_group(general_items, "allgemein")
    add_group(berlin_items, "berlin")
    return questions


def extract_bamf_logo(doc: fitz.Document) -> bytes:
    """Take the official BAMF header logo (eagle + text) from the catalog."""
    if BAMF_LOGO_PATH.exists():
        return BAMF_LOGO_PATH.read_bytes()
    # First content page embeds the logo as image xref; fall back to clip
    page = doc[1]
    images = page.get_images(full=True)
    if images:
        extracted = doc.extract_image(images[0][0])
        data = extracted["image"]
        BAMF_LOGO_PATH.write_bytes(data)
        return data
    clip = fitz.Rect(9.2, 15.0, 161.2, 119.0)
    pix = page.get_pixmap(matrix=fitz.Matrix(3, 3), clip=clip, alpha=False)
    data = pix.tobytes("png")
    BAMF_LOGO_PATH.write_bytes(data)
    return data


def new_page(out: fitz.Document) -> fitz.Page:
    # Landscape A4: 842 x 595 points
    return out.new_page(width=842, height=595)


def draw_header(
    page: fitz.Page,
    title: str,
    page_no: int,
    page_count: int,
    logo_bytes: bytes | None = None,
) -> None:
    if logo_bytes:
        # Keep BAMF logo compact in the landscape header
        logo_w, logo_h = 118.0, 81.0
        page.insert_image(fitz.Rect(12, 6, 12 + logo_w, 6 + logo_h), stream=logo_bytes)
        title_x = 12 + logo_w + 10
        title_y = 28
    else:
        title_x = 18
        title_y = 16

    if title:
        page.insert_text(
            (title_x, title_y),
            title,
            fontsize=8,
            fontname="helv",
            color=(0, 0, 0),
        )
    page.insert_text(
        (842 - 18 - 70, 18),
        f"Seite {page_no} / {page_count}",
        fontsize=8,
        fontname="helv",
        color=(0, 0, 0),
    )


def fit_rect(box: fitz.Rect, img_w: float, img_h: float, pad: float = 4.0) -> fitz.Rect:
    inner = fitz.Rect(box.x0 + pad, box.y0 + pad, box.x1 - pad, box.y1 - pad)
    scale = min(inner.width / img_w, inner.height / img_h)
    w = img_w * scale
    h = img_h * scale
    x = inner.x0 + (inner.width - w) / 2
    y = inner.y0  # top-align for reading flow
    return fitz.Rect(x, y, x + w, y + h)


TARGET_PER_PAGE = 8  # 2 columns x 4 rows on landscape A4


def pack_pages(questions: list[dict]) -> list[list[dict]]:
    """Pack ~8 questions per page; only very tall image clips use fewer."""
    pages: list[list[dict]] = []
    current: list[dict] = []

    def natural_h(q: dict) -> float:
        return q["height"] / RENDER_SCALE

    def is_very_tall(q: dict) -> bool:
        # Full photo / map style clips (e.g. Bundestag photo)
        return natural_h(q) >= 420

    def is_tall(q: dict) -> bool:
        # Coat-of-arms / multi-image clips
        return natural_h(q) >= 280

    i = 0
    while i < len(questions):
        q = questions[i]

        if is_very_tall(q):
            if current:
                pages.append(current)
                current = []
            pages.append([q])
            i += 1
            continue

        if is_tall(q):
            if current:
                pages.append(current)
                current = []
            # up to 4 tall questions in a 2x2
            chunk = [q]
            j = i + 1
            while j < len(questions) and len(chunk) < 4:
                nq = questions[j]
                if is_very_tall(nq):
                    break
                if not is_tall(nq):
                    break
                chunk.append(nq)
                j += 1
            pages.append(chunk)
            i = j
            continue

        current.append(q)
        if len(current) == TARGET_PER_PAGE:
            pages.append(current)
            current = []
        i += 1

    if current:
        pages.append(current)
    return pages


def slots_for(count: int) -> list[fitz.Rect]:
    margin_x = 12.0
    margin_top = 22.0  # compact header only (logo is cover-only)
    margin_bottom = 10.0
    gap = 5.0
    page_w, page_h = 842.0, 595.0
    usable_w = page_w - 2 * margin_x
    usable_h = page_h - margin_top - margin_bottom

    if count == 1:
        return [fitz.Rect(margin_x, margin_top, page_w - margin_x, page_h - margin_bottom)]

    if count == 2:
        w = (usable_w - gap) / 2
        return [
            fitz.Rect(margin_x, margin_top, margin_x + w, page_h - margin_bottom),
            fitz.Rect(margin_x + w + gap, margin_top, page_w - margin_x, page_h - margin_bottom),
        ]

    if count <= 4:
        # 2x2
        cols, rows = 2, 2
    else:
        # 5..8 -> 2 columns x 4 rows
        cols, rows = 2, 4

    w = (usable_w - (cols - 1) * gap) / cols
    h = (usable_h - (rows - 1) * gap) / rows
    rects: list[fitz.Rect] = []
    for idx in range(count):
        row = idx // cols
        col = idx % cols
        x0 = margin_x + col * (w + gap)
        y0 = margin_top + row * (h + gap)
        rects.append(fitz.Rect(x0, y0, x0 + w, y0 + h))
    return rects


def build_pdf(questions: list[dict], logo_bytes: bytes | None) -> None:
    pages_plan = pack_pages(questions)
    out = fitz.open()

    cover = new_page(out)
    if logo_bytes:
        cover.insert_image(fitz.Rect(60, 140, 220, 250), stream=logo_bytes)
    cover.insert_text((60, 280), "Einbürgerungstest", fontsize=28, fontname="helv", color=(0, 0, 0))
    cover.insert_text(
        (60, 315),
        "310 Fragen mit Antwortoptionen · Bundesland Berlin",
        fontsize=12,
        fontname="helv",
        color=(0, 0, 0),
    )
    cover.insert_text(
        (60, 340),
        "300 allgemeine Fragen + 10 Fragen für Berlin",
        fontsize=10,
        fontname="helv",
        color=(0, 0, 0),
    )
    cover.insert_text(
        (60, 365),
        "Quelle: BAMF-Gesamtfragenkatalog (Stand 07.05.2025) · ca. 8 Fragen pro Seite",
        fontsize=9,
        fontname="helv",
        color=(0, 0, 0),
    )

    # Determine when to insert Berlin divider: before first page containing berlin
    first_berlin_page = next(
        i for i, group in enumerate(pages_plan) if any(q["section"] == "berlin" for q in group)
    )

    total_content_sheets = len(pages_plan) + 1  # + berlin divider
    sheet_no = 0

    for i, group in enumerate(pages_plan):
        if i == first_berlin_page:
            sheet_no += 1
            div = new_page(out)
            div.insert_text(
                (60, 260),
                "Teil II · Fragen für das Bundesland Berlin",
                fontsize=18,
                fontname="helv",
                color=(0, 0, 0),
            )
            div.insert_text(
                (60, 290),
                "10 landesbezogene Fragen (Aufgabe 1-10)",
                fontsize=10,
                fontname="helv",
                color=(0, 0, 0),
            )

        sheet_no += 1
        page = new_page(out)
        title = "Einbürgerungstest · allgemeine Fragen"
        if any(q["section"] == "berlin" for q in group):
            title = "Einbürgerungstest · Berlin"
        # Logo only on cover page to keep question layout stable
        draw_header(page, title, sheet_no, total_content_sheets, logo_bytes=None)

        boxes = slots_for(len(group))
        for q, box in zip(group, boxes):
            # No box borders — BAMF clips only
            dest = fit_rect(box, q["width"], q["height"], pad=2)
            jpeg = q["pix"].tobytes("jpeg", jpg_quality=85)
            page.insert_image(dest, stream=jpeg, keep_proportion=True)

    out.save(OUT_PDF, deflate=True, garbage=4)
    out.close()
    ARTIFACT_PDF.parent.mkdir(parents=True, exist_ok=True)
    pdf_bytes = OUT_PDF.read_bytes()
    ARTIFACT_PDF.write_bytes(pdf_bytes)
    OUT_PDF_8UP.write_bytes(pdf_bytes)
    ARTIFACT_PDF_8UP.write_bytes(pdf_bytes)


def main() -> None:
    katalog = find_katalog()
    doc = fitz.open(katalog)
    logo_bytes = extract_bamf_logo(doc)
    questions = build_question_pixmaps(doc)
    doc.close()
    build_pdf(questions, logo_bytes)
    print(f"Wrote {len(questions)} questions -> {OUT_PDF}")
    print(f"Also -> {OUT_PDF_8UP}")
    print(f"Artifact -> {ARTIFACT_PDF}")


if __name__ == "__main__":
    main()
