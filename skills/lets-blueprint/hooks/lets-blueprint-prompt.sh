#!/usr/bin/env bash
# User-level Cursor hook: nudge blueprint-intent prompts toward lets-blueprint skill.
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
  *"let's blueprint"*|*"lets blueprint"*|*"lets-blueprint"*|*"bu fikir için blueprint"*|*"bu fikir icin blueprint"*|*"stitch + build"*|*"stitch ve build"*|*"proje md paketini"*|*"stitch prompt"*|*"agents.md hazırla"*|*"agents.md hazirla"*)
    matched=1
    ;;
esac

if [ "$matched" -eq 0 ]; then
  printf '%s\n' '{"continue":true}'
  exit 0
fi

ctx='Follow the global lets-blueprint skill (~/.cursor/skills/lets-blueprint/SKILL.md). Interview until no unknowns remain (never assume). Write APP.md + STITCH + BUILD + AGENTS (+ tasks/docs stubs) into the already-created ~/Repo/<project>/ folder. git init if needed. Walk Week 0 third-party provisioning. Do NOT scaffold app code.'

python3 -c '
import json,sys
ctx=sys.argv[1]
print(json.dumps({
  "continue": True,
  "user_message": "lets-blueprint: follow ~/.cursor/skills/lets-blueprint (docs + Week 0 only; no app code).",
  "additional_context": ctx,
}, ensure_ascii=False))
' "$ctx"
exit 0
