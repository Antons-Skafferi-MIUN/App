#!/bin/sh
HOOK_DIR=$(git rev-parse --show-toplevel)/.git/hooks

echo "Installing pre-push git hook to your .git/hooks directory"
cp .git-hooks/pre-push .git/hooks