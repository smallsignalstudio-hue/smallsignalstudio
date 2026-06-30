#!/usr/bin/env bash
# Validate a rejection registry YAML file.
set -euo pipefail

if [ $# -lt 1 ]; then
  echo "Usage: validate-rejection.sh <path-to-rejection.yaml>" >&2
  exit 1
fi

FILE="$1"
if [ ! -f "$FILE" ]; then
  echo "File not found: $FILE" >&2
  exit 1
fi

python3 - "$FILE" <<'PY'
import sys
from pathlib import Path

try:
    import yaml
except ImportError:
    print("PyYAML required: pip3 install pyyaml", file=sys.stderr)
    sys.exit(1)

path = Path(sys.argv[1])
data = yaml.safe_load(path.read_text(encoding="utf-8"))
if not isinstance(data, dict):
    print(f"{path}: root must be a mapping", file=sys.stderr)
    sys.exit(1)

required = ["id", "app", "date", "apple_guideline", "apple_message", "root_cause", "status"]
missing = [k for k in required if not data.get(k)]
if missing:
    print(f"{path}: missing required fields: {', '.join(missing)}", file=sys.stderr)
    sys.exit(1)

status = str(data["status"]).lower()
if status not in ("open", "resolved"):
    print(f"{path}: status must be 'open' or 'resolved', got {data['status']!r}", file=sys.stderr)
    sys.exit(1)

if not str(data["apple_message"]).strip():
    print(f"{path}: apple_message cannot be empty", file=sys.stderr)
    sys.exit(1)

print(f"OK: {path.name} ({data['id']})")
PY
