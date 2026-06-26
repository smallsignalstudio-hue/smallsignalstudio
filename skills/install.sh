#!/usr/bin/env bash
set -euo pipefail

SRC="$(cd "$(dirname "$0")" && pwd)"
DEST="$HOME/.cursor/skills"

mkdir -p "$DEST"
cp -R "$SRC/small-signal-ideas" "$DEST/"

echo "Installed small-signal-ideas → $DEST/small-signal-ideas"
