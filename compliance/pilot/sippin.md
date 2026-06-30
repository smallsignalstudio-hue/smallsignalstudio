# Pilot: Sippin

First iOS project integrated with the App Store compliance hub.

## Files added to Sippin repo

| File | Purpose |
|------|---------|
| `.greenlight.yml` | Project-specific Greenlight overrides |
| `.github/workflows/app-store-compliance.yml` | Release branch CI gate |

## GitHub setup (Sippin repo)

Add secret:

- `COMPLIANCE_HUB_REPO` — private clone URL for SmallSignalStudio

## Local preflight

```bash
export APPSTORE_COMPLIANCE_HUB="$HOME/Repo/SmallSignalStudio"
cd ~/Repo/sippin
"$APPSTORE_COMPLIANCE_HUB/compliance/scripts/run-preflight.sh" .
```

## Notes

- Sippin uses Expo (`app.json`) with IAP via RevenueCat patterns — verify Restore Purchases before submit
- `lib/releaseBlockers.ts` tracks manual release blockers separate from Greenlight
- Record any future App Store rejections in `compliance/rejections/` in this hub

## Pilot run

Run date: 2026-06-30

```bash
brew install revylai/tap/greenlight jq
pip3 install pyyaml
"$APPSTORE_COMPLIANCE_HUB/compliance/scripts/run-preflight.sh" ~/Repo/sippin
```

**Result:** GREENLIT (0 CRITICAL, 5 WARN)

Notable WARN findings to review before submit:

- No privacy policy URL in project config (set in App Store Connect)
- Platform reference in `__mocks__/expo-constants.js` (test mock — may ignore via `.greenlight.yml`)
- Placeholder content in onboarding test file
- False positive: account deletion in `coverage/lcov-report/` (add `coverage` to ignore)
- HTTP URL in `lib/splashWordmarkFontNode.ts`

Recommended `.greenlight.yml` additions for Sippin after pilot:

```yaml
ignore:
  - coverage
  - __mocks__
  - __tests__
```
