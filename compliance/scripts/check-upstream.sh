#!/usr/bin/env bash
# Compare installed greenlight version against latest GitHub release.
set -euo pipefail

QUIET=false
if [ "${1:-}" = "--quiet" ]; then
  QUIET=true
fi

MIN_GREENLIGHT_VERSION="${MIN_GREENLIGHT_VERSION:-0.1.0}"

version_ge() {
  # Returns 0 if $1 >= $2 (semver-ish, numeric segments only)
  local a="$1" b="$2"
  if [ "$a" = "$b" ]; then return 0; fi
  local winner
  winner="$(printf '%s\n%s\n' "$a" "$b" | sort -t. -k1,1n -k2,2n -k3,3n | tail -1)"
  [ "$winner" = "$a" ]
}

local_version() {
  if command -v greenlight >/dev/null 2>&1; then
    greenlight version 2>/dev/null | awk '{print $NF}' | tr -d 'v' || true
    return
  fi
  if command -v brew >/dev/null 2>&1; then
    brew list --versions greenlight 2>/dev/null | awk '{print $2}' || true
    return
  fi
  echo ""
}

latest_version() {
  if ! command -v curl >/dev/null 2>&1; then
    echo ""
    return
  fi
  curl -fsSL \
    -H "Accept: application/vnd.github+json" \
    -H "User-Agent: small-signal-compliance-hub" \
    "https://api.github.com/repos/RevylAI/greenlight/releases/latest" \
    2>/dev/null | python3 -c "import json,sys; d=json.load(sys.stdin); print(d.get('tag_name','').lstrip('v'))" \
    || echo ""
}

LOCAL="$(local_version)"
LATEST="$(latest_version)"

if [ -z "$LOCAL" ]; then
  if [ "$QUIET" = false ]; then
    echo "greenlight: not installed — run: brew install revylai/tap/greenlight"
  fi
  exit 0
fi

if ! version_ge "$LOCAL" "$MIN_GREENLIGHT_VERSION"; then
  echo "greenlight $LOCAL is below minimum $MIN_GREENLIGHT_VERSION — run: brew upgrade revylai/tap/greenlight"
  exit 0
fi

if [ -n "$LATEST" ] && ! version_ge "$LOCAL" "$LATEST"; then
  if [ "$QUIET" = false ]; then
    echo "greenlight update available: $LOCAL → $LATEST (brew upgrade revylai/tap/greenlight)"
  else
    echo "greenlight update available: $LOCAL → $LATEST" >&2
  fi
  exit 0
fi

if [ "$QUIET" = false ]; then
  echo "greenlight $LOCAL is up to date."
fi
