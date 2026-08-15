#!/usr/bin/env bash
# User-level Cursor hook: nudge todo-init-intent prompts toward lets-todo-init skill.
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
  *"let's todo init"*|*"lets todo init"*|*"lets-todo-init"*|*"todo backlog"*|*"epic'lere böl"*|*"epiclere bol"*|*"scrum todo"*|*"iskeleti commit"*|*"işleri parçala"*|*"isleri parcala"*)
    matched=1
    ;;
esac

if [ "$matched" -eq 0 ]; then
  printf '%s\n' '{"continue":true}'
  exit 0
fi

ctx='Follow the global lets-todo-init skill (~/.cursor/skills/lets-todo-init/SKILL.md). Read {SLUG}.md + BUILD + GDD. Write tasks/todo.md index + tasks/epics/*.md with fully specified Scrum stories. Do NOT write app code. Do not smash v1 into one commit. Ask before push.'

python3 -c '
import json,sys
ctx=sys.argv[1]
print(json.dumps({
  "continue": True,
  "user_message": "lets-todo-init: follow ~/.cursor/skills/lets-todo-init (backlog docs only; no app code).",
  "additional_context": ctx,
}, ensure_ascii=False))
' "$ctx"
exit 0
