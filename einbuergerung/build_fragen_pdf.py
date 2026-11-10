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
ARTIFACT_PDF = Path("/opt/cursor/artifacts/einbuergerungstest-fragen-berlin.pdf")

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


def new_page(out: fitz.Document) -> fitz.Page:
    # Landscape A4: 842 x 595 points
    return out.new_page(width=842, height=595)


def draw_header(page: fitz.Page, title: str, page_no: int, page_count: int) -> None:
    page.insert_text(
        (18, 16),
        title,
        fontsize=8,
        fontname="helv",
        color=(0, 0, 0),
    )
    page.insert_text(
        (842 - 18 - 60, 16),
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


def pack_pages(questions: list[dict]) -> list[list[dict]]:
    """Pack ~4 questions per page; use fewer when clips are very tall."""
    # Landscape usable area roughly 806 x 555 after margins/header
    usable_w = 806.0
    usable_h = 555.0
    cell_w = (usable_w - 8) / 2
    cell_h = (usable_h - 8) / 2

    pages: list[list[dict]] = []
    current: list[dict] = []

    def aspect_needs_half(q: dict) -> bool:
        # If image is taller than ~1.15x cell after width-fit, give half/full page
        width_scale = cell_w / (q["width"] / RENDER_SCALE)
        fitted_h = (q["height"] / RENDER_SCALE) * width_scale
        return fitted_h > cell_h * 1.05

    def aspect_needs_full(q: dict) -> bool:
        width_scale = usable_w / (q["width"] / RENDER_SCALE)
        fitted_h = (q["height"] / RENDER_SCALE) * width_scale
        return fitted_h > usable_h * 0.92

    i = 0
    while i < len(questions):
        q = questions[i]
        if aspect_needs_full(q):
            if current:
                pages.append(current)
                current = []
            pages.append([q])
            i += 1
            continue
        if aspect_needs_half(q):
            if len(current) >= 3:
                pages.append(current)
                current = []
            # pair up to 2 half-page questions
            if current and len(current) == 1 and aspect_needs_half(current[0]):
                current.append(q)
                pages.append(current)
                current = []
            elif current and not aspect_needs_half(current[0]):
                pages.append(current)
                current = [q]
            else:
                current.append(q)
                if len(current) == 2:
                    pages.append(current)
                    current = []
            i += 1
            continue

        if len(current) == 4:
            pages.append(current)
            current = []
        # don't mix pending half-page leftovers awkwardly
        if current and any(aspect_needs_half(x) for x in current):
            pages.append(current)
            current = []
        current.append(q)
        if len(current) == 4:
            pages.append(current)
            current = []
        i += 1

    if current:
        pages.append(current)
    return pages


def slots_for(count: int) -> list[fitz.Rect]:
    margin_x = 18.0
    margin_top = 24.0
    margin_bottom = 14.0
    gap = 8.0
    page_w, page_h = 842.0, 595.0
    usable_w = page_w - 2 * margin_x
    usable_h = page_h - margin_top - margin_bottom

    if count == 1:
        return [fitz.Rect(margin_x, margin_top, page_w - margin_x, page_h - margin_bottom)]
    if count == 2:
        # side by side
        w = (usable_w - gap) / 2
        return [
            fitz.Rect(margin_x, margin_top, margin_x + w, page_h - margin_bottom),
            fitz.Rect(margin_x + w + gap, margin_top, page_w - margin_x, page_h - margin_bottom),
        ]
    # 3 or 4 -> 2x2 grid (3 leaves one empty)
    w = (usable_w - gap) / 2
    h = (usable_h - gap) / 2
    return [
        fitz.Rect(margin_x, margin_top, margin_x + w, margin_top + h),
        fitz.Rect(margin_x + w + gap, margin_top, page_w - margin_x, margin_top + h),
        fitz.Rect(margin_x, margin_top + h + gap, margin_x + w, page_h - margin_bottom),
        fitz.Rect(margin_x + w + gap, margin_top + h + gap, page_w - margin_x, page_h - margin_bottom),
    ][:count]


def build_pdf(questions: list[dict]) -> None:
    pages_plan = pack_pages(questions)
    out = fitz.open()

    # Cover
    cover = new_page(out)
    cover.insert_text((60, 220), "Einbürgerungstest", fontsize=28, fontname="helv", color=(0, 0, 0))
    cover.insert_text(
        (60, 260),
        "310 Fragen mit Antwortoptionen · Bundesland Berlin",
        fontsize=12,
        fontname="helv",
        color=(0, 0, 0),
    )
    cover.insert_text(
        (60, 290),
        "Quelle: BAMF-Gesamtfragenkatalog „Leben in Deutschland“ / Einbürgerungstest (Stand 07.05.2025)",
        fontsize=9,
        fontname="helv",
        color=(0, 0, 0),
    )
    cover.insert_text(
        (60, 320),
        "Querformat A4 · Originalfragen inkl. Bilder · ohne Markierung der richtigen Antwort",
        fontsize=9,
        fontname="helv",
        color=(0, 0, 0),
    )
    cover.draw_rect(fitz.Rect(50, 190, 792, 350), color=(0, 0, 0), width=0.6)

    # Section divider before Berlin
    berlin_index = next(i for i, q in enumerate(questions) if q["section"] == "berlin")

    content_page_count = len(pages_plan)
    # account for berlin divider
    divider_after_page = None
    q_cursor = 0
    for p_i, group in enumerate(pages_plan):
        if any(q["section"] == "berlin" for q in group) and divider_after_page is None:
            # insert divider before this page if first berlin question starts here
            if group[0]["section"] == "berlin" or any(q is questions[berlin_index] for q in group):
                divider_after_page = p_i
                break
        q_cursor += len(group)

    # Rebuild page sequence with divider
    rendered_pages: list[tuple[str, list[dict] | None]] = [("cover", None)]
    for p_i, group in enumerate(pages_plan):
        if divider_after_page is not None and p_i == divider_after_page:
            rendered_pages.append(("divider", None))
        rendered_pages.append(("content", group))

    # We already created cover; create remaining
    # First remove empty approach: recreate cleaner
    out.close()
    out = fitz.open()

    # Cover again
    cover = new_page(out)
    cover.draw_rect(fitz.Rect(40, 170, 802, 360), color=(0, 0, 0), width=0.5)
    cover.insert_text((60, 220), "Einbürgerungstest", fontsize=28, fontname="helv", color=(0, 0, 0))
    cover.insert_text(
        (60, 255),
        "310 Fragen mit Antwortoptionen · Bundesland Berlin",
        fontsize=12,
        fontname="helv",
        color=(0, 0, 0),
    )
    cover.insert_text(
        (60, 290),
        "300 allgemeine Fragen + 10 Fragen für Berlin",
        fontsize=10,
        fontname="helv",
        color=(0, 0, 0),
    )
    cover.insert_text(
        (60, 315),
        "Quelle: BAMF-Gesamtfragenkatalog (Stand 07.05.2025)",
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
            div.draw_rect(fitz.Rect(40, 220, 802, 320), color=(0, 0, 0), width=0.5)
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
        draw_header(page, title, sheet_no, total_content_sheets)

        boxes = slots_for(len(group))
        for q, box in zip(group, boxes):
            page.draw_rect(box, color=(0, 0, 0), width=0.5)
            dest = fit_rect(box, q["width"], q["height"], pad=5)
            # JPEG keeps logos readable while shrinking the PDF significantly
            jpeg = q["pix"].tobytes("jpeg", jpg_quality=85)
            page.insert_image(dest, stream=jpeg, keep_proportion=True)

    out.save(OUT_PDF, deflate=True, garbage=4)
    out.close()
    ARTIFACT_PDF.parent.mkdir(parents=True, exist_ok=True)
    ARTIFACT_PDF.write_bytes(OUT_PDF.read_bytes())


def main() -> None:
    katalog = find_katalog()
    doc = fitz.open(katalog)
    questions = build_question_pixmaps(doc)
    doc.close()
    build_pdf(questions)
    print(f"Wrote {len(questions)} questions -> {OUT_PDF}")
    print(f"Artifact -> {ARTIFACT_PDF}")


if __name__ == "__main__":
    main()
