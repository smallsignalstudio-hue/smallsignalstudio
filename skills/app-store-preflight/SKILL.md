---
name: app-store-preflight
description: >-
  App Store pre-submission compliance for iOS, macOS, tvOS, watchOS, and visionOS
  apps (Swift, Objective-C, React Native, Expo). Runs Greenlight plus learned
  rejection rules from the Small Signal compliance hub. Use when the user says
  app store preflight, greenlight, submission readiness, App Store rejection,
  or asks to check before submitting to Apple.
disable-model-invocation: true
---
# App Store Preflight

Pre-submission compliance for Small Signal Studio apps. Combines [Greenlight](https://github.com/RevylAI/greenlight) with a **living rejection registry** in the compliance hub.

**Greenlight passing is not a guarantee of approval** — Apple still does manual review. This skill maximizes odds and prevents repeat mistakes.

## Hub path

Scripts live in the SmallSignalStudio repo, not in `~/.cursor/skills/`. Resolve the hub before running:

```bash
export APPSTORE_COMPLIANCE_HUB="${APPSTORE_COMPLIANCE_HUB:-$HOME/Repo/SmallSignalStudio}"
```

If the path differs on this machine, ask the user once and use their path.

## Step 0: Prerequisites

```bash
greenlight version || brew install revylai/tap/greenlight
command -v jq >/dev/null || brew install jq
python3 -c "import yaml" 2>/dev/null || pip3 install pyyaml
```

Check for upstream updates (warn only, do not block):

```bash
"$APPSTORE_COMPLIANCE_HUB/compliance/scripts/check-upstream.sh"
```

## Step 1: Run unified preflight

From the **iOS project root** (where `app.json` or `ios/` lives):

```bash
"$APPSTORE_COMPLIANCE_HUB/compliance/scripts/run-preflight.sh" .
```

With a built IPA:

```bash
"$APPSTORE_COMPLIANCE_HUB/compliance/scripts/run-preflight.sh" . --ipa /path/to/build.ipa
```

Save JSON reports:

```bash
"$APPSTORE_COMPLIANCE_HUB/compliance/scripts/run-preflight.sh" . --output ./compliance-reports
```

## Step 2: Fix findings (priority order)

1. **CRITICAL** — must fix before submission
2. **WARN** — high rejection risk
3. **INFO** — best practice

Common Greenlight fixes:

| Finding | Fix |
|---------|-----|
| Hardcoded secrets | Environment variables / keychain |
| External payment for digital goods | StoreKit / IAP |
| Social login without Sign in with Apple | Add `expo-apple-authentication` or native SIWA |
| Account creation without deletion | Add working Delete Account in settings |
| Platform references | Remove Android / Google Play from user-facing strings |
| Placeholder content | Replace lorem ipsum, TBD, coming soon |
| Vague purpose strings | Specific why + how data is used |
| HTTP URLs | HTTPS |
| Missing privacy manifest | Add `PrivacyInfo.xcprivacy` |
| Missing ATT | `requestTrackingAuthorization` before tracking SDKs |

Custom rule findings come from `compliance/custom-rules/learned-rules.yaml` — fix per the rule's `fix` field.

## Step 3: Re-run until GREENLIT

```bash
"$APPSTORE_COMPLIANCE_HUB/compliance/scripts/run-preflight.sh" .
```

**Goal: zero CRITICAL findings** (output shows `GREENLIT`).

## Step 4 (optional): Runtime flow validation

If the app has account creation, IAP, or social login:

```bash
greenlight verify . --dry-run
```

For on-device validation (needs Revyl CLI + account):

```bash
greenlight verify . --build-name "<Revyl build>" \
  --var email=<test@email.com> --var password=<password>
```

Treat FAILED flows like CRITICAL.

## Step 5: After an App Store rejection

When the user reports a rejection, **always record it in the hub** so future projects are checked.

1. Create `compliance/rejections/YYYY-MM-<app>-<slug>.yaml` from `_template.yaml`
2. Fill Apple's message, root cause, fix applied
3. If automatable, add a rule to `compliance/custom-rules/learned-rules.yaml`
4. Validate: `compliance/scripts/validate-rejection.sh <file>`
5. Commit to SmallSignalStudio — skills sync via git hooks

See [reference.md](reference.md) for rejection playbook and rule authoring.

## Other commands

```bash
greenlight codescan .
greenlight privacy .
greenlight ipa /path/to/build.ipa
greenlight scan --app-id <ID>          # needs: greenlight auth setup
greenlight guidelines search "privacy"
```

## Per-project setup

Copy templates from the hub into each iOS repo (once):

- `skills/app-store-preflight/templates/.greenlight.yml` → project root
- `skills/app-store-preflight/templates/github-workflows/app-store-compliance.yml` → `.github/workflows/`

Set `COMPLIANCE_HUB_REPO` secret in GitHub for CI.

## Do not

- Skip recording rejections in the hub
- Treat GREENLIT as guaranteed approval
- Fork Greenlight for custom rules — use `learned-rules.yaml` instead
