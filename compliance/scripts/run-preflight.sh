#!/usr/bin/env bash
# Unified App Store preflight: greenlight + learned custom rules.
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
HUB_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
export APPSTORE_COMPLIANCE_HUB="${APPSTORE_COMPLIANCE_HUB:-$HUB_ROOT}"

MIN_GREENLIGHT_VERSION="${MIN_GREENLIGHT_VERSION:-0.1.0}"
CI_MODE=false
IPA_PATH=""
PROJECT=""
OUTPUT_DIR=""

usage() {
  cat <<EOF
Usage: run-preflight.sh <project-path> [options]

Options:
  --ipa <path>     Include IPA binary inspection
  --ci             CI mode: JSON artifacts, exit 1 on CRITICAL
  --output <dir>   Write greenlight-report.json and custom-report.json
  -h, --help       Show this help

Environment:
  APPSTORE_COMPLIANCE_HUB  Path to SmallSignalStudio hub (auto-detected if unset)
EOF
}

while [ $# -gt 0 ]; do
  case "$1" in
    --ci) CI_MODE=true; shift ;;
    --ipa)
      IPA_PATH="${2:-}"
      shift 2
      ;;
    --output)
      OUTPUT_DIR="${2:-}"
      shift 2
      ;;
    -h|--help)
      usage
      exit 0
      ;;
    -*)
      echo "Unknown option: $1" >&2
      usage >&2
      exit 1
      ;;
    *)
      if [ -z "$PROJECT" ]; then
        PROJECT="$1"
        shift
      else
        echo "Unexpected argument: $1" >&2
        exit 1
      fi
      ;;
  esac
done

if [ -z "$PROJECT" ]; then
  usage >&2
  exit 1
fi

PROJECT="$(cd "$PROJECT" && pwd)"

if ! command -v greenlight >/dev/null 2>&1; then
  echo "greenlight not found. Install: brew install revylai/tap/greenlight" >&2
  exit 1
fi

if ! command -v jq >/dev/null 2>&1; then
  echo "jq not found. Install: brew install jq" >&2
  exit 1
fi

# Upstream version check (warn only)
MIN_GREENLIGHT_VERSION="$MIN_GREENLIGHT_VERSION" "$SCRIPT_DIR/check-upstream.sh" || true

GL_ARGS=(preflight "$PROJECT" --format json)
if [ -n "$IPA_PATH" ]; then
  GL_ARGS+=(--ipa "$IPA_PATH")
fi

TMP_GL="$(mktemp)"
TMP_GL_RAW="$(mktemp)"
TMP_CUSTOM="$(mktemp)"
trap 'rm -f "$TMP_GL" "$TMP_GL_RAW" "$TMP_CUSTOM"' EXIT

set +e
greenlight "${GL_ARGS[@]}" >"$TMP_GL_RAW" 2>/dev/null
GL_EXIT=$?
set -e

# greenlight may print a banner before JSON — extract the JSON object
python3 - "$TMP_GL_RAW" "$TMP_GL" <<'PY'
import json, sys
from pathlib import Path
raw = Path(sys.argv[1]).read_text(encoding="utf-8", errors="replace")
out = Path(sys.argv[2])
start = raw.find("{")
if start < 0:
    out.write_text(raw)
    sys.exit(1)
try:
    obj = json.loads(raw[start:])
    out.write_text(json.dumps(obj))
except json.JSONDecodeError:
    out.write_text(raw[start:])
    sys.exit(1)
PY
GL_PARSE=$?

if [ ! -s "$TMP_GL" ] || [ "$GL_PARSE" -ne 0 ]; then
  echo "greenlight preflight failed (exit $GL_EXIT). Run manually for details:" >&2
  echo "  greenlight preflight $PROJECT --format json" >&2
  exit 1
fi

python3 "$SCRIPT_DIR/run-custom-checks.py" "$PROJECT" --format json >"$TMP_CUSTOM" 2>/dev/null || true
if [ ! -s "$TMP_CUSTOM" ]; then
  echo '{"findings":[],"summary":{"critical":0,"warn":0,"info":0,"passed":true}}' >"$TMP_CUSTOM"
fi

if [ -n "$OUTPUT_DIR" ]; then
  mkdir -p "$OUTPUT_DIR"
  cp "$TMP_GL" "$OUTPUT_DIR/greenlight-report.json"
  cp "$TMP_CUSTOM" "$OUTPUT_DIR/custom-report.json"
fi

GL_CRITICAL="$(jq -r '.summary.critical // 0' "$TMP_GL")"
GL_WARN="$(jq -r '.summary.warns // .summary.warn // 0' "$TMP_GL")"
GL_INFO="$(jq -r '.summary.info // 0' "$TMP_GL")"
GL_PASSED="$(jq -r '.summary.passed // false' "$TMP_GL")"

CUSTOM_CRITICAL="$(jq -r '.summary.critical // 0' "$TMP_CUSTOM")"
CUSTOM_WARN="$(jq -r '.summary.warn // 0' "$TMP_CUSTOM")"
CUSTOM_INFO="$(jq -r '.summary.info // 0' "$TMP_CUSTOM")"

TOTAL_CRITICAL=$((GL_CRITICAL + CUSTOM_CRITICAL))
TOTAL_WARN=$((GL_WARN + CUSTOM_WARN))
TOTAL_INFO=$((GL_INFO + CUSTOM_INFO))

MERGED="$(mktemp)"
trap 'rm -f "$TMP_GL" "$TMP_GL_RAW" "$TMP_CUSTOM" "$MERGED"' EXIT

jq -n \
  --arg project "$PROJECT" \
  --arg hub "$APPSTORE_COMPLIANCE_HUB" \
  --slurpfile gl "$TMP_GL" \
  --slurpfile custom "$TMP_CUSTOM" \
  --argjson critical "$TOTAL_CRITICAL" \
  --argjson warn "$TOTAL_WARN" \
  --argjson info "$TOTAL_INFO" \
  '{
    project: $project,
    hub: $hub,
    greenlight: $gl[0],
    custom_rules: $custom[0],
    findings: (($gl[0].findings // []) + ($custom[0].findings // [])),
    summary: {
      critical: $critical,
      warn: $warn,
      info: $info,
      passed: ($critical == 0),
      greenlit: ($critical == 0)
    }
  }' >"$MERGED"

if [ -n "$OUTPUT_DIR" ]; then
  cp "$MERGED" "$OUTPUT_DIR/preflight-report.json"
fi

if [ "$CI_MODE" = true ]; then
  cat "$MERGED"
else
  echo ""
  echo "  App Store Preflight — $PROJECT"
  echo "  Hub: $APPSTORE_COMPLIANCE_HUB"
  echo ""
  echo "  Greenlight:  critical=$GL_CRITICAL  warn=$GL_WARN  info=$GL_INFO"
  echo "  Custom:      critical=$CUSTOM_CRITICAL  warn=$CUSTOM_WARN  info=$CUSTOM_INFO"
  echo "  ─────────────────────────────────────"
  if [ "$TOTAL_CRITICAL" -eq 0 ]; then
    echo "  GREENLIT — ready for submission (zero CRITICAL)"
  else
    echo "  NOT READY — $TOTAL_CRITICAL CRITICAL issue(s)"
  fi
  echo ""

  if [ "$TOTAL_CRITICAL" -gt 0 ] || [ "$TOTAL_WARN" -gt 0 ]; then
    echo "  Findings:"
    jq -r '.findings[] | select(.severity == "CRITICAL" or .severity == "WARN") |
      "  [\(.severity)] \(.title)\(if .file then "  \(.file)" + (if .line then ":\(.line)" else "" end) else "" end)"' "$MERGED"
    echo ""
  fi
fi

if [ "$TOTAL_CRITICAL" -gt 0 ]; then
  exit 1
fi
exit 0
