#!/usr/bin/env bash
# Install all Cursor skills globally (~/.cursor/skills/) for use in any project.
#
# From SmallSignalStudio:
#   ./scripts/install-cursor-skills.sh
#
# From ~/Repo (if you copied or symlinked this script there):
#   ~/Repo/install-cursor-skills.sh
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

# Resolve hub: this script lives in SmallSignalStudio/scripts/ OR ~/Repo/
if [ -f "$SCRIPT_DIR/../skills/install.sh" ]; then
  HUB="$(cd "$SCRIPT_DIR/.." && pwd)"
  REPO_ROOT="$(cd "$HUB/.." && pwd)"
elif [ -f "$SCRIPT_DIR/SmallSignalStudio/skills/install.sh" ]; then
  REPO_ROOT="$SCRIPT_DIR"
  HUB="$REPO_ROOT/SmallSignalStudio"
else
  echo "Could not find SmallSignalStudio/skills/install.sh" >&2
  exit 1
fi

"$HUB/skills/install.sh"

ENV_FILE="$REPO_ROOT/.cursor-env.sh"
if [ ! -f "$ENV_FILE" ] && [ -f "$HUB/scripts/cursor-env.example.sh" ]; then
  cp "$HUB/scripts/cursor-env.example.sh" "$ENV_FILE"
  echo "Created $ENV_FILE from example"
fi
if [ -f "$ENV_FILE" ]; then
  # shellcheck source=/dev/null
  source "$ENV_FILE"
fi

echo ""
echo "Skills installed to ~/.cursor/skills/"
echo "Turkish guide: $HUB/docs/CURSOR-SKILLS.md"
echo "Open ANY project under ~/Repo in Cursor → Agent chat."
echo ""
ls -1 "$HOME/.cursor/skills/" 2>/dev/null | sed 's/^/  - /' || true

if ! command -v greenlight >/dev/null 2>&1; then
  echo ""
  echo "Tip: App Store preflight also needs: brew install revylai/tap/greenlight jq && pip3 install pyyaml"
fi
