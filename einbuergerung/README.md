# Einbürgerungstest – Fragenkatalog (Berlin)

Landscape A4 PDF with **310 questions only** (no answer options):

- 300 general questions
- 10 Berlin state questions

Source: BAMF Gesamtfragenkatalog „Leben in Deutschland“ / Einbürgerungstest (Stand 07.05.2025).

## Files

- `einbuergerungstest-fragen-berlin.pdf` – printable landscape sheets (~4 questions per page)
- `fragen-berlin.json` – structured question list (stems only)
- `build_fragen_pdf.py` – rebuild script

## Rebuild

```bash
python3 einbuergerung/build_fragen_pdf.py
```

Rebuilds the PDF from `fragen-berlin.json`. Requires `reportlab`.
