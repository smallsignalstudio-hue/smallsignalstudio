# Cursor + compliance environment for all ~/Repo projects.
# install-cursor-skills.sh copies this to ~/Repo/.cursor-env.sh if missing.
# Also sourced from ~/.zshrc after first skill install.

export APPSTORE_COMPLIANCE_HUB="${APPSTORE_COMPLIANCE_HUB:-$HOME/Repo/SmallSignalStudio}"
export REPO_ROOT="${REPO_ROOT:-$HOME/Repo}"

export PATH="$APPSTORE_COMPLIANCE_HUB/compliance/scripts:$PATH"
