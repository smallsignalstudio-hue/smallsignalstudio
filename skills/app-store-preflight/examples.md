# App Store Preflight — Examples

## Example 1: Standard preflight session

**User:** "App Store'a göndermeden önce kontrol et"

**Agent:**

```bash
export APPSTORE_COMPLIANCE_HUB="$HOME/Repo/SmallSignalStudio"
cd /path/to/sippin
"$APPSTORE_COMPLIANCE_HUB/compliance/scripts/run-preflight.sh" .
```

Output shows 2 CRITICAL (missing privacy manifest, hardcoded API key). Agent fixes both, re-runs until GREENLIT.

---

## Example 2: Rejection → learned rule

**Apple message:**

> Guideline 3.1.2 — Business — Payments — Subscriptions  
> Apps offering auto-renewable subscriptions must include a functional link to Terms of Use.

**Rejection file:** `compliance/rejections/2026-06-sippin-subscription-terms.yaml`

```yaml
id: sippin-2026-06-subscription-terms
app: Sippin
date: 2026-06-15
apple_guideline: "3.1.2"
apple_message: |
  Apps offering auto-renewable subscriptions must include a functional link to Terms of Use.
root_cause: Paywall screen had Subscribe button but no Terms of Use link
fix_applied: Added Terms of Use and Privacy Policy links below the subscribe CTA
status: resolved
custom_rule_id: subscription-terms-visible
```

**Rule added to `learned-rules.yaml`:**

```yaml
rules:
  - id: subscription-terms-visible
    type: project_match
    title: Subscription paywall must reference Terms of Use
    guideline: "3.1.2"
    severity: critical
    source_rejection: sippin-2026-06-subscription-terms
    patterns:
      - '(?i)(auto.?renew|subscription|subscribe.*premium)'
    require_any:
      - '(?i)terms of (use|service)'
      - '(?i)/terms'
      - '(?i)EULA'
    detail: Subscription UI detected without Terms of Use reference in source.
    fix: Add visible, tappable Terms of Use link on the paywall (and Privacy Policy).
```

Next Sippin (or Glossio) preflight catches this before submit.

---

## Example 3: Account deletion false negative

Greenlight codescan sees `deleteAccount` string → suppresses §5.1.1 warning → GREENLIT.

But the button does nothing at runtime → Apple rejects.

**Action:**

1. Record rejection in `compliance/rejections/`
2. Fix the actual flow in code
3. Run `greenlight verify . --dry-run` then on-device verify
4. Optional custom rule (hard to automate wiring):

```yaml
  - id: delete-account-not-placeholder
    type: file_match
    title: Delete account handler must not be empty stub
    severity: warn
    languages: [typescript, swift]
    patterns:
      - '(?i)deleteAccount.*\{\s*\}'
      - '(?i)deleteAccount.*=>\s*\{\s*\}'
    detail: Delete account function appears to be an empty stub.
    fix: Implement actual account deletion API call and post-delete navigation.
```

---

## Example 4: CI failure on release branch

Push to `release/1.2` triggers GitHub Actions. `run-preflight.sh . --ci` exits 1 due to CRITICAL placeholder text.

Developer fixes "Coming soon" string, pushes again, CI passes.

---

## Example 5: Upstream update notice

```bash
$ compliance/scripts/check-upstream.sh
greenlight update available: 0.2.1 → 0.3.0 (brew upgrade revylai/tap/greenlight)
```

Agent informs user but continues preflight with installed version.
