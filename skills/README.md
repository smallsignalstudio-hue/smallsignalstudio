# Cursor Skills

Version-controlled Cursor Agent Skills for Small Signal Studio.

**Türkçe kullanım rehberi:** [docs/CURSOR-SKILLS.md](../docs/CURSOR-SKILLS.md)  
**Global kurulum (tüm ~/Repo projeleri):** `./scripts/install-cursor-skills.sh`

## Skills in this repo

| Skill | Description |
|-------|-------------|
| [lets-blueprint](lets-blueprint/) | **Let's Blueprint** — idea → full md pack (overview, Stitch, Build, AGENTS) + Week 0 third-party gates; no app code |
| [lets-todo-init](lets-todo-init/) | **Let's Todo Init** — after Blueprint, emit Scrum epic/story backlog (`tasks/epics/`); no app code |
| [lets-release](lets-release/) | **Let's Release** — pre-upload gate: preflight + ASO/keywords + ASC metadata/pricing (no screenshots; no binary unless asked) |
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

**Let's Blueprint (yeni proje md paketi):**

```
Let's Blueprint — işte fikir / agent dump: …
```

Önce `~/Repo/<proje-adi>/` klasörünü oluşturup Cursor'da o klasörü aç. Skill Stitch + Build + AGENTS yazar; uygulama kodu scaffold etmez. Backlog için ardından **Let's Todo Init**.

**Let's Todo Init (Scrum backlog):**

```
Let's Todo Init — GDD ve BUILD'den epic/story çıkar
```

`tasks/todo.md` + `tasks/epics/*.md` yazar; uygulama kodu yazmaz.

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
