# App Store Preflight — Reference

## Hub layout

```
SmallSignalStudio/
  compliance/
    scripts/run-preflight.sh      # Main entry point
    scripts/run-custom-checks.py
    scripts/check-upstream.sh
    scripts/validate-rejection.sh
    rejections/                   # Incident log
    custom-rules/learned-rules.yaml
  skills/app-store-preflight/     # This skill (synced to ~/.cursor/skills/)
```

## Environment variables

| Variable | Default | Purpose |
|----------|---------|---------|
| `APPSTORE_COMPLIANCE_HUB` | Auto from script location | Hub root path |
| `MIN_GREENLIGHT_VERSION` | `0.1.0` | Minimum CLI version |
| `COMPLIANCE_HUB_REPO` | (CI only) | Git clone URL for GitHub Actions |

## First-time machine setup

```bash
cd ~/Repo/SmallSignalStudio
chmod +x skills/install.sh compliance/scripts/*.sh
./skills/install.sh
brew install revylai/tap/greenlight jq
pip3 install pyyaml
```

Add to `~/.zshrc` (optional):

```bash
export APPSTORE_COMPLIANCE_HUB="$HOME/Repo/SmallSignalStudio"
```

## Invoking in Cursor

```
Use the app-store-preflight skill to run preflight on this project and fix all CRITICAL issues.
```

After rejection:

```
Use app-store-preflight skill — Apple rejected this app. Here's the message: <paste>. Record it in the hub and add a custom rule.
```

## Rejection playbook

### 1. Document the incident

```bash
cp compliance/rejections/_template.yaml \
   compliance/rejections/2026-06-sippin-subscription-terms.yaml
```

Required fields: `id`, `app`, `date`, `apple_guideline`, `apple_message`, `root_cause`, `status`

Optional: `fix_applied`, `custom_rule_id`

Validate:

```bash
compliance/scripts/validate-rejection.sh compliance/rejections/your-file.yaml
```

### 2. Add automatable rule (if applicable)

Edit `compliance/custom-rules/learned-rules.yaml`.

**Rule type: `project_match`** (default) — trigger patterns found anywhere, but required safeguards missing project-wide:

```yaml
rules:
  - id: subscription-terms-visible
    type: project_match
    title: Subscription paywall must reference Terms of Use
    guideline: "3.1.2"
    severity: warn  # start warn; promote to critical after validation
    source_rejection: sippin-2026-06-subscription-terms
    patterns:
      - '(?i)(subscribe|subscription|premium)'
    require_any:
      - '(?i)terms of (use|service)'
      - '(?i)EULA'
    detail: Paywall references subscriptions but no Terms of Use link found.
    fix: Add Terms of Use and Privacy Policy links on the paywall screen.
```

**Rule type: `file_match`** — pattern in a specific file:

```yaml
  - id: example-file-pattern
    type: file_match
    title: Example file-level check
    severity: warn
    languages: [swift, typescript, javascript]
    patterns:
      - 'TODO: remove before release'
    detail: Release blocker comment found.
    fix: Remove or resolve the TODO before submission.
```

### 3. Test the new rule

```bash
compliance/scripts/run-custom-checks.py /path/to/ios-project
compliance/scripts/run-preflight.sh /path/to/ios-project
```

### 4. Commit to hub

```bash
git add compliance/rejections/ compliance/custom-rules/
git commit -m "docs(compliance): record <app> rejection — <short reason>"
```

Git hooks sync the skill to `~/.cursor/skills/`.

## Upstream Greenlight sync

| When | Action |
|------|--------|
| Every preflight | `check-upstream.sh` warns if outdated |
| After `git pull` hub | `post-merge` hook runs quiet check |
| Monthly / before major submit | `brew upgrade revylai/tap/greenlight` |

Watch releases: https://github.com/RevylAI/greenlight/releases

Do **not** fork Greenlight for custom checks. Contribute generic rules upstream via PR if appropriate.

## Per-project `.greenlight.yml`

Project-specific overrides for Greenlight codescan:

```yaml
rules:
  platform-reference:
    enabled: false
ignore:
  - vendor
  - "**/*.generated.ts"
```

## CI integration

Copy `templates/github-workflows/app-store-compliance.yml` to each iOS repo.

Required GitHub secret:

- `COMPLIANCE_HUB_REPO` — private clone URL for SmallSignalStudio (deploy key or PAT)

Workflow runs on `release/**` and `main`, plus `workflow_dispatch`.

## Severity promotion policy

1. New learned rules start as `warn`
2. After catching a real issue or second occurrence → `critical`
3. False positives → disable rule or add `ignore` in project `.greenlight.yml`

## Limitations

- Custom rules use regex heuristics — not as deep as Greenlight's Go codescan
- `project_match` scans text only; cannot verify UI layout
- Runtime flows need `greenlight verify` + Revyl (optional paid tier)
- CI uses `macos-latest` — keep triggers on release branches only
