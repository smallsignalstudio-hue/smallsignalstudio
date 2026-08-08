#!/usr/bin/env bash
# User-level Cursor hook: nudge release-intent prompts toward lets-release skill.
# beforeSubmitPrompt cannot reliably inject model context; we surface a user_message
# and still emit additional_context for runtimes that accept it.
set -euo pipefail

input="$(cat || true)"
prompt="$(printf '%s' "$input" | python3 -c '
import json,sys
try:
  d=json.load(sys.stdin)
except Exception:
  d={}
print(d.get("prompt") or d.get("content") or "")
' 2>/dev/null || true)"

lower="$(printf '%s' "$prompt" | tr '[:upper:]' '[:lower:]')"

matched=0
case "$lower" in
  *"let's release"*|*"lets release"*|*"yeni yük"*|*"yeni yuk"*|*"testflight"*|*"markete gönder"*|*"markete gonder"*|*"eas submit"*|*"app store connect"*|*"asc metadata"*|*"aso "*|*"aso,"*|*"preflight"*)
    matched=1
    ;;
esac

if [ "$matched" -eq 0 ]; then
  printf '%s\n' '{"continue":true}'
  exit 0
fi

ctx='Follow the global lets-release skill (~/.cursor/skills/lets-release/SKILL.md). Run preflight + ASO/keyword refresh for all storefront locales (shipped and upcoming features) + ASC metadata/pricing when credentials exist. Do NOT build/submit a binary unless explicitly asked. Never touch screenshots; if stale, mention only at the end of the final reply.'

# Escape for JSON
python3 -c '
import json,sys
ctx=sys.argv[1]
print(json.dumps({
  "continue": True,
  "user_message": "lets-release: follow ~/.cursor/skills/lets-release (no binary / no screenshots unless asked).",
  "additional_context": ctx,
}, ensure_ascii=False))
' "$ctx"
exit 0
