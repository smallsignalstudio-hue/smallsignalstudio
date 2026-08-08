# Let's Release — reference

## Field limits (Apple)

| Field | Limit |
|---|---|
| App name | ≤30 |
| Subtitle | ≤30 |
| Keywords | ≤100 (comma-separated, no spaces after commas) |
| Promotional text | ≤170 |
| Description | ≤4000 |
| What’s New | ≤4000 |

## Screenshot policy

- Automated release flows **must not** create, edit, replace, or upload screenshots.
- If UI changed since last screenshots, end the operator reply with one short paragraph: why an update is recommended (which screens / locales). Nothing else.

## ASO honesty checklist

- [ ] Title/subtitle pack local head terms for the storefront language
- [ ] Keywords avoid stems already fully covered by title/subtitle when budget is tight
- [ ] No competitor brand names
- [ ] No charity / donation / sadaka framing for IAP
- [ ] Shipped features appear in description when user-facing
- [ ] Unshipped features: keyword foresight only, or omit; never claim in What’s New as shipped
- [ ] Anti-features never indexed (e.g. mosque finder if not built)

## Discovery paths (per repo)

| Signal | Typical path |
|---|---|
| App config | `app.json` / `mobile/app.json` |
| Store drafts | `docs/store/`, `store.config.json` |
| Roadmap | `tasks/todo.md` |
| Preflight | `npm run preflight`, `.cursor/skills/app-store-preflight`, hub Greenlight |
| ASC env | `ASC_KEY_ID`, `ASC_ISSUER_ID`, `ASC_PRIVATE_KEY_PATH`, `ASC_APP_ID` |

## ASC auth troubleshooting

1. Create key: App Store Connect → Users and Access → Integrations → App Store Connect API
2. Role: Admin or App Manager
3. Place `.p8` outside git; set path in `.env`
4. 401 usually means revoked key, wrong Key ID, or clock skew — regenerate key

## Trigger phrases (operator)

- English: `Let's release`, `Let's release a new load to TestFlight`, `prepare ASC for release`
- Turkish: `yeni yük`, `TestFlight'a yük çık`, `markete gönder`
