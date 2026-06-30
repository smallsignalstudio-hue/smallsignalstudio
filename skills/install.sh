#!/usr/bin/env bash
set -euo pipefail

FROM_HOOK=false
if [ "${1:-}" = "--from-hook" ]; then
  FROM_HOOK=true
fi

SRC="$(cd "$(dirname "$0")" && pwd)"
REPO_ROOT="$(cd "$SRC/.." && pwd)"
DEST="$HOME/.cursor/skills"
REPO_ENV="$HOME/Repo/.cursor-env.sh"
ZSHRC="$HOME/.zshrc"
MARKER="# Small Signal Studio — Cursor skills & compliance hub"

mkdir -p "$DEST"

clean_dest() {
  for item in "$DEST"/*; do
    [ -e "$item" ] || continue
    if [ -f "$item" ]; then
      rm -f "$item"
    elif [ -d "$item" ] && [ ! -f "$item/SKILL.md" ]; then
      rm -rf "$item"
    fi
  done
}

clean_dest

INSTALLED=()
for skill_dir in "$SRC"/*/; do
  [ -f "${skill_dir}SKILL.md" ] || continue
  name="$(basename "$skill_dir")"
  cp -R "$skill_dir" "$DEST/"
  INSTALLED+=("$name")
done

clean_dest

# Legacy skill removed from global install
rm -rf "$DEST/small-signal-ideas"

if [ "$FROM_HOOK" = false ]; then
  echo "Installed skills → $DEST/"
  for name in "${INSTALLED[@]}"; do
    echo "  - $name"
  done
fi

# Ensure ~/Repo/.cursor-env.sh is sourced from ~/.zshrc
if [ -f "$REPO_ENV" ] && [ -f "$ZSHRC" ]; then
  if ! grep -qF "$MARKER" "$ZSHRC" 2>/dev/null; then
    cat >>"$ZSHRC" <<EOF

$MARKER
[ -f "\$HOME/Repo/.cursor-env.sh" ] && source "\$HOME/Repo/.cursor-env.sh"
EOF
    if [ "$FROM_HOOK" = false ]; then
      echo "Added \$HOME/Repo/.cursor-env.sh to ~/.zshrc"
    fi
  fi
fi

# Register repo git hooks once so pull/commit keep ~/.cursor/skills/ in sync.
if git -C "$REPO_ROOT" rev-parse --git-dir >/dev/null 2>&1; then
  current_hooks="$(git -C "$REPO_ROOT" config core.hooksPath || true)"
  if [ "$current_hooks" != ".githooks" ]; then
    git -C "$REPO_ROOT" config core.hooksPath .githooks
    if [ "$FROM_HOOK" = false ]; then
      echo "Registered .githooks — skills sync automatically on git pull and commit"
    fi
  fi
fi

if [ "$FROM_HOOK" = true ]; then
  echo "Synced skills → $DEST/ (${INSTALLED[*]})"
fi
