#!/usr/bin/env python3
"""Scan an iOS project against compliance/custom-rules/learned-rules.yaml."""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

try:
    import yaml
except ImportError:
    print(
        json.dumps(
            {
                "error": "PyYAML required: pip3 install pyyaml",
                "findings": [],
                "summary": {"critical": 0, "warn": 0, "info": 0, "passed": True},
            }
        )
    )
    sys.exit(1)

SKIP_DIRS = {
    "node_modules",
    ".git",
    "Pods",
    "build",
    "dist",
    ".expo",
    "DerivedData",
    "vendor",
    ".greenlight-inspect",
}

LANG_EXT = {
    ".swift": "swift",
    ".m": "objc",
    ".h": "objc",
    ".ts": "typescript",
    ".tsx": "typescript",
    ".js": "javascript",
    ".jsx": "javascript",
}


def hub_root() -> Path:
    env = __import__("os").environ.get("APPSTORE_COMPLIANCE_HUB")
    if env:
        return Path(env).resolve()
    return Path(__file__).resolve().parents[1]


def collect_files(project: Path) -> list[tuple[Path, str, str]]:
    files: list[tuple[Path, str, str]] = []
    for path in project.rglob("*"):
        if not path.is_file():
            continue
        parts = set(path.parts)
        if parts & SKIP_DIRS:
            continue
        if any(part in SKIP_DIRS for part in path.parts):
            continue
        lang = LANG_EXT.get(path.suffix.lower())
        if not lang:
            continue
        try:
            content = path.read_text(encoding="utf-8", errors="replace")
        except OSError:
            continue
        rel = str(path.relative_to(project))
        files.append((path, rel, content))
    return files


def lang_ok(rule_langs: list[str] | None, lang: str) -> bool:
    if not rule_langs:
        return True
    return lang in rule_langs


def severity_upper(s: str) -> str:
    return s.strip().upper()


def run_project_match(rule: dict, files: list[tuple[Path, str, str]]) -> list[dict]:
    patterns = [re.compile(p) for p in rule.get("patterns", [])]
    require_any = [re.compile(p) for p in rule.get("require_any", [])]
    if not patterns or not require_any:
        return []

    triggered = False
    for _, _, content in files:
        if any(p.search(content) for p in patterns):
            triggered = True
            break
    if not triggered:
        return []

    project_text = "\n".join(c for _, _, c in files)
    if any(p.search(project_text) for p in require_any):
        return []

    return [
        {
            "source": "custom",
            "severity": severity_upper(rule.get("severity", "warn")),
            "guideline": rule.get("guideline", ""),
            "title": rule.get("title", rule.get("id", "custom rule")),
            "detail": rule.get(
                "detail",
                "Project matches trigger patterns but is missing required safeguards.",
            ),
            "fix": rule.get("fix", ""),
            "rule_id": rule.get("id", ""),
        }
    ]


def run_file_match(rule: dict, files: list[tuple[Path, str, str]]) -> list[dict]:
    patterns = [re.compile(p) for p in rule.get("patterns", [])]
    if not patterns:
        return []

    rule_langs = rule.get("languages")
    findings: list[dict] = []
    for path, rel, content in files:
        ext_lang = LANG_EXT.get(path.suffix.lower(), "")
        if not lang_ok(rule_langs, ext_lang):
            continue
        for pattern in patterns:
            match = pattern.search(content)
            if not match:
                continue
            line = content[: match.start()].count("\n") + 1
            findings.append(
                {
                    "source": "custom",
                    "severity": severity_upper(rule.get("severity", "warn")),
                    "guideline": rule.get("guideline", ""),
                    "title": rule.get("title", rule.get("id", "custom rule")),
                    "detail": rule.get("detail", "Pattern matched in source."),
                    "fix": rule.get("fix", ""),
                    "file": rel,
                    "line": line,
                    "rule_id": rule.get("id", ""),
                }
            )
            break
    return findings


def main() -> int:
    parser = argparse.ArgumentParser(description="Run learned custom compliance rules")
    parser.add_argument("project", type=Path, help="Project root to scan")
    parser.add_argument(
        "--rules",
        type=Path,
        default=None,
        help="Path to learned-rules.yaml (default: hub custom-rules)",
    )
    parser.add_argument("--format", choices=["json", "terminal"], default="terminal")
    args = parser.parse_args()

    project = args.project.resolve()
    rules_path = args.rules or (hub_root() / "custom-rules" / "learned-rules.yaml")

    if not project.is_dir():
        print(f"Project not found: {project}", file=sys.stderr)
        return 1

    if not rules_path.is_file():
        empty = {
            "source": "custom-rules",
            "project": str(project),
            "rules_file": str(rules_path),
            "findings": [],
            "summary": {"critical": 0, "warn": 0, "info": 0, "passed": True},
        }
        if args.format == "json":
            print(json.dumps(empty, indent=2))
        return 0

    data = yaml.safe_load(rules_path.read_text(encoding="utf-8")) or {}
    rules = data.get("rules") or []
    files = collect_files(project)

    findings: list[dict] = []
    for rule in rules:
        if not rule or not rule.get("id"):
            continue
        kind = rule.get("type", "project_match")
        if kind == "file_match":
            findings.extend(run_file_match(rule, files))
        else:
            findings.extend(run_project_match(rule, files))

    critical = sum(1 for f in findings if f["severity"] == "CRITICAL")
    warn = sum(1 for f in findings if f["severity"] == "WARN")
    info = sum(1 for f in findings if f["severity"] == "INFO")

    result = {
        "source": "custom-rules",
        "project": str(project),
        "rules_file": str(rules_path),
        "findings": findings,
        "summary": {
            "critical": critical,
            "warn": warn,
            "info": info,
            "passed": critical == 0,
        },
    }

    if args.format == "json":
        print(json.dumps(result, indent=2))
        return 1 if critical > 0 else 0

    print(f"\n  Custom rules ({rules_path.name})")
    if not findings:
        print("  No custom rule findings.\n")
        return 0

    for f in findings:
        loc = ""
        if f.get("file"):
            loc = f"  {f['file']}"
            if f.get("line"):
                loc += f":{f['line']}"
        print(f"  [{f['severity']}] {f['title']}{loc}")
        if f.get("fix"):
            print(f"    Fix: {f['fix']}")
    print()
    return 1 if critical > 0 else 0


if __name__ == "__main__":
    sys.exit(main())
