# Einbürgerungstest – Fragenkatalog (Berlin)

Landscape A4 PDF with **310 questions including answer options**:

- 300 general questions
- 10 Berlin state questions

Each question is clipped from the official BAMF catalog (text, checkboxes, coats of arms / images) and placed in a thin black box. No extra colors or styling.

Source: BAMF Gesamtfragenkatalog „Leben in Deutschland“ / Einbürgerungstest (Stand 07.05.2025).

## Files

- `einbuergerungstest-fragen-berlin.pdf` – printable landscape sheets
- `build_fragen_pdf.py` – rebuild script (needs local BAMF `katalog.pdf`)

## Rebuild

Place the BAMF PDF at `/tmp/einbuergerung/katalog.pdf` or `einbuergerung/katalog.pdf`, then:

```bash
python3 einbuergerung/build_fragen_pdf.py
```

Requires `pymupdf`.
