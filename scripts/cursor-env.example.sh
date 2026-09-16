# Cursor + compliance environment for all ~/Repo projects.
# install-cursor-skills.sh copies this to ~/Repo/.cursor-env.sh if missing.
# Also sourced from ~/.zshrc after first skill install.

export APPSTORE_COMPLIANCE_HUB="${APPSTORE_COMPLIANCE_HUB:-$HOME/Repo/SmallSignalStudio}"
export REPO_ROOT="${REPO_ROOT:-$HOME/Repo}"

export PATH="$APPSTORE_COMPLIANCE_HUB/compliance/scripts:$PATH"

# ads-ideas skill — Meta/Instagram Ad Library via Apify REST (never commit the real token)
# https://console.apify.com/settings/integrations
# export APIFY_TOKEN="apify_api_xxxxxxxx"
