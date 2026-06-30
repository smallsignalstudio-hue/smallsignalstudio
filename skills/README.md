# Cursor Skills

Version-controlled Cursor Agent Skills for Small Signal Studio.

## Skills in this repo

| Skill | Description |
|-------|-------------|
| [genius-ideas](genius-ideas/) | JARVIS for researching monetizable solo-buildable mobile app ideas |
| [app-store-preflight](app-store-preflight/) | App Store pre-submission compliance (Greenlight + learned rejection rules) |

## Install (global use in any project)

From the repo root:

```bash
chmod +x skills/install.sh compliance/scripts/*.sh   # first time only
./skills/install.sh
brew install revylai/tap/greenlight jq               # first time only
pip3 install pyyaml                                  # for custom rules scanner
```

Optional — add to `~/.zshrc`:

```bash
export APPSTORE_COMPLIANCE_HUB="$HOME/Repo/SmallSignalStudio"
```

This copies skills into `~/.cursor/skills/` so Cursor loads them in **every project**.

It also registers `.githooks` in this repo (one-time) so you never need to run install manually again.

## Automatic sync (no manual steps)

After the first `./skills/install.sh`, git hooks keep your global install in sync:

| Event | What happens |
|-------|----------------|
| `git pull` | Skills re-copy to `~/.cursor/skills/`; greenlight version check |
| `git commit` (when `skills/` changed) | Same — sync runs automatically |

Just `git pull` or commit skill edits as usual. No need to remember `./skills/install.sh`.

**New clone?** Run `./skills/install.sh` once to install skills and register hooks.

## How to invoke in Cursor

**App Store preflight:**

```
Use the app-store-preflight skill to run preflight and fix all CRITICAL issues.
```

**After an App Store rejection:**

```
Use app-store-preflight skill — Apple rejected with this message: <paste>
```

**Genius ideas:**

```
Use the genius-ideas skill to find something related to astrology
```

Skills do not auto-invoke on unrelated chats (`disable-model-invocation: true`).

## Compliance hub

Scripts and rejection registry live in [`compliance/`](../compliance/README.md):

```bash
"$APPSTORE_COMPLIANCE_HUB/compliance/scripts/run-preflight.sh" /path/to/ios-project
```

## Per-iOS-project setup

Copy once into each app repo:

- `skills/app-store-preflight/templates/.greenlight.yml` → project root
- `skills/app-store-preflight/templates/github-workflows/app-store-compliance.yml` → `.github/workflows/`

Set GitHub secret `COMPLIANCE_HUB_REPO` for CI.

## Editing skills

1. Edit files under `skills/<skill-name>/`
2. Commit and push — global install updates on commit automatically
3. On other machines: `git pull` syncs automatically (after one-time `./skills/install.sh`)
