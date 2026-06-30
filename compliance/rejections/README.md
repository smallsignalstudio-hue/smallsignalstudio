# Rejection registry

One YAML file per App Store rejection incident. Used to document what happened and link to automated checks in `custom-rules/learned-rules.yaml`.

## Naming

`YYYY-MM-<app-slug>-<short-description>.yaml`

Example: `2026-06-sippin-subscription-terms.yaml`

## Workflow

1. Copy `_template.yaml`
2. Fill all required fields
3. Run `../scripts/validate-rejection.sh rejections/your-file.yaml`
4. Add matching rule to `../custom-rules/learned-rules.yaml` if automatable
5. Commit
