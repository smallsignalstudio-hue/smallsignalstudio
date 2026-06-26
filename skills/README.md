# Cursor Skills

Version-controlled Cursor Agent Skills for Small Signal Studio.

## Skills in this repo

| Skill | Description |
|-------|-------------|
| [small-signal-ideas](small-signal-ideas/) | JARVIS for researching monetizable solo-buildable mobile app ideas |

## Install (global use in any project)

From the repo root:

```bash
chmod +x skills/install.sh   # first time only
./skills/install.sh
```

This copies skills into `~/.cursor/skills/` so Cursor loads them in **every project**.

It also registers `.githooks` in this repo (one-time) so you never need to run install manually again.

## Automatic sync (no manual steps)

After the first `./skills/install.sh`, git hooks keep your global install in sync:

| Event | What happens |
|-------|----------------|
| `git pull` | Skills re-copy to `~/.cursor/skills/` |
| `git commit` (when `skills/` changed) | Same — sync runs automatically |

Just `git pull` or commit skill edits as usual. No need to remember `./skills/install.sh`.

**New clone?** Run `./skills/install.sh` once to install skills and register hooks.

## How to invoke in Cursor

Start an Agent chat and say one of:

- "Use the small-signal-ideas skill"
- "JARVIS, find app ideas"
- "Run small-signal-ideas — vertical: astrology"

Optional parameters:

| Parameter | Example |
|-----------|---------|
| Vertical | `vertical: open` or `vertical: ADHD` |
| Full auto | Skip Phase 4 questions — agent picks #1 and continues |
| Save report | `save report to file` → writes to `~/Documents/small-signal-ideas/` |

The skill does not auto-invoke on unrelated chats (`disable-model-invocation: true`).

## Editing skills

1. Edit files under `skills/<skill-name>/`
2. Commit and push — global install updates on commit automatically
3. On other machines: `git pull` syncs automatically (after one-time `./skills/install.sh`)
