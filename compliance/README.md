# App Store Compliance Hub

Living compliance layer for Small Signal Studio iOS apps. Wraps [RevylAI/greenlight](https://github.com/RevylAI/greenlight) with learned rejection rules and a unified preflight runner.

## Quick start

```bash
# One-time: install greenlight CLI
brew install revylai/tap/greenlight

# From any iOS project root
export APPSTORE_COMPLIANCE_HUB="$HOME/Repo/SmallSignalStudio"
"$APPSTORE_COMPLIANCE_HUB/compliance/scripts/run-preflight.sh" .
```

## Layout

| Path | Purpose |
|------|---------|
| `scripts/run-preflight.sh` | Greenlight + custom rules, unified report |
| `scripts/run-custom-checks.py` | Scans `custom-rules/learned-rules.yaml` |
| `scripts/check-upstream.sh` | Compare local greenlight vs GitHub latest |
| `scripts/validate-rejection.sh` | Validate new rejection YAML files |
| `rejections/` | Apple rejection incident log |
| `custom-rules/learned-rules.yaml` | Auto-scanned rules from past rejections |

## After an App Store rejection

1. Copy `rejections/_template.yaml` → `rejections/YYYY-MM-<app>-<slug>.yaml`
2. Fill in Apple's message, root cause, and fix
3. Add or update a rule in `custom-rules/learned-rules.yaml`
4. Run `validate-rejection.sh` on the new file
5. Commit to this repo — skills sync automatically via git hooks

See [skills/app-store-preflight/reference.md](../skills/app-store-preflight/reference.md) for full workflow.
