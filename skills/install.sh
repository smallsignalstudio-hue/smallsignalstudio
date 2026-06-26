#!/usr/bin/env bash
set -euo pipefail

FROM_HOOK=false
if [ "${1:-}" = "--from-hook" ]; then
  FROM_HOOK=true
fi

SRC="$(cd "$(dirname "$0")" && pwd)"
REPO_ROOT="$(cd "$SRC/.." && pwd)"
DEST="$HOME/.cursor/skills"

mkdir -p "$DEST"
cp -R "$SRC/genius-ideas" "$DEST/"
rm -rf "$DEST/small-signal-ideas"

if [ "$FROM_HOOK" = false ]; then
  echo "Installed genius-ideas → $DEST/genius-ideas"
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
  echo "Synced genius-ideas → $DEST/genius-ideas"
fi
