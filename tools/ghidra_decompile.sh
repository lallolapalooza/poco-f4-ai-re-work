#!/usr/bin/env bash
# ghidra_decompile.sh - bounded, headless Ghidra decompilation for .so files.
#
# Usage:
#   ghidra_decompile.sh <so> <out.c> [regex] [options]
#
# Options:
#   --timeout SEC        wall-clock budget for the whole run (default 600)
#   --max-functions N    decompile at most N matching functions (default 64)
#   --func-timeout SEC   per-function decompiler timeout (default 60)
#   --heap SIZE          Java heap for headless Ghidra (default 3G, max 3G policy)
#   --noanalysis         import without auto-analysis (rarely useful)
#   --keep-project       do not delete the temporary Ghidra project
#
# Exit codes: 0 ok, 2 usage/input error, 3 no functions decompiled,
#             124 wrapper timeout, other = Ghidra/Java failure.
#
# Environment: GHIDRA_HOME, GHIDRA_JAVA_HOME (preferred JDK), GHIDRA_HEADLESS.
set -uo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
GHIDRA_HOME="${GHIDRA_HOME:-/opt/ghidra}"
ANALYZE="${GHIDRA_HEADLESS:-$GHIDRA_HOME/support/analyzeHeadless}"
SCRIPT_PATH="$SCRIPT_DIR/ghidra_scripts"

DEFAULT_REGEX='Java_.*|JNI_OnLoad|.*[Mm]odel.*|.*[Ii]nfer.*'
TIMEOUT=600
MAX_FUNCTIONS=64
FUNC_TIMEOUT=60
HEAP="${GHIDRA_HEAP:-3G}"
NOANALYSIS=0
KEEP_PROJECT=0

usage() {
    sed -n '2,20p' "${BASH_SOURCE[0]}" | sed 's/^# \{0,1\}//'
    exit 2
}

positional=()
while [ $# -gt 0 ]; do
    case "$1" in
        --timeout)       TIMEOUT="${2:?}"; shift 2 ;;
        --max-functions) MAX_FUNCTIONS="${2:?}"; shift 2 ;;
        --func-timeout)  FUNC_TIMEOUT="${2:?}"; shift 2 ;;
        --heap)          HEAP="${2:?}"; shift 2 ;;
        --noanalysis)    NOANALYSIS=1; shift ;;
        --keep-project)  KEEP_PROJECT=1; shift ;;
        -h|--help)       usage ;;
        --)              shift; positional+=("$@"); break ;;
        -*)              echo "ghidra_decompile.sh: unknown option $1" >&2; usage ;;
        *)               positional+=("$1"); shift ;;
    esac
done

[ ${#positional[@]} -ge 2 ] || usage
SO="${positional[0]}"
OUT="${positional[1]}"
REGEX="${positional[2]:-$DEFAULT_REGEX}"
[ ${#positional[@]} -le 3 ] || { echo "ghidra_decompile.sh: too many arguments" >&2; usage; }

[ -r "$SO" ] || { echo "ghidra_decompile.sh: cannot read $SO" >&2; exit 2; }
[ -x "$ANALYZE" ] || { echo "ghidra_decompile.sh: analyzeHeadless not found at $ANALYZE" >&2; exit 2; }
[ -f "$SCRIPT_PATH/DecompileSelected.java" ] || {
    echo "ghidra_decompile.sh: missing $SCRIPT_PATH/DecompileSelected.java" >&2; exit 2; }

# ---- JDK selection -------------------------------------------------------
# Ghidra 12.1.2 requires JDK 21+.  support/launch.sh tries PATH first, so we
# must also prepend the chosen JDK to PATH.  Prefer a local JDK 21 if present
# (matches Ghidra's compiled-for level); otherwise accept any JDK >= 21.
java_major() { "$1/bin/java" -version 2>&1 | awk -F'"' '/version/ {print $2}' \
    | awk -F. '{print ($1 == 1 ? $2 : $1)}'; }

pick_jdk() {
    if [ -n "${GHIDRA_JAVA_HOME:-}" ]; then
        if [ -x "${GHIDRA_JAVA_HOME}/bin/java" ]; then
            echo "$GHIDRA_JAVA_HOME"; return
        fi
        echo "ghidra_decompile.sh: GHIDRA_JAVA_HOME is not a JDK: ${GHIDRA_JAVA_HOME}" >&2
        return 1
    fi
    local candidates=(
        /usr/lib/jvm/java-21-openjdk
        "$HOME/.jdks/jbr-21.0.11"
        /usr/lib/jvm/java-21-openjdk-amd64
    )
    local cand major
    for cand in "${candidates[@]}"; do
        if [ -x "$cand/bin/java" ]; then
            major="$(java_major "$cand")"
            if [ -n "$major" ] && [ "$major" -ge 21 ] 2>/dev/null; then
                echo "$cand"; return
            fi
        fi
    done
    if [ -n "${JAVA_HOME:-}" ] && [ -x "$JAVA_HOME/bin/java" ]; then
        major="$(java_major "$JAVA_HOME")"
        if [ -n "$major" ] && [ "$major" -ge 21 ] 2>/dev/null; then
            echo "$JAVA_HOME"; return
        fi
    fi
    cand="$(command -v java || true)"
    if [ -n "$cand" ]; then
        cand="$(dirname "$(dirname "$(readlink -f "$cand")")")"
        major="$(java_major "$cand")"
        if [ -n "$major" ] && [ "$major" -ge 21 ] 2>/dev/null; then
            echo "$cand"; return
        fi
    fi
    return 1
}

JDK="$(pick_jdk)" || {
    echo "ghidra_decompile.sh: no JDK >= 21 found. Set GHIDRA_JAVA_HOME to a JDK 21+." >&2
    echo "  (a local JDK 21 is sufficient; no system packages are required)" >&2
    exit 2
}
export JAVA_HOME="$JDK"
export PATH="$JDK/bin:$PATH"
echo "[ghidra_decompile] jdk: $JDK ($("$JDK/bin/java" -version 2>&1 | head -1))"

# ---- project / cleanup ---------------------------------------------------
PROJECT_DIR="$(mktemp -d "${TMPDIR:-/tmp}/ghidra_decomp.XXXXXX")" || exit 2
STOP_FILE="${TMPDIR:-/tmp}/ghidra_decompile_stop.$$"
CHILD=0
WATCH=""
cleanup() {
    if [ "$CHILD" -gt 0 ]; then
        kill -TERM -- -"$CHILD" 2>/dev/null
        sleep 1
        kill -KILL -- -"$CHILD" 2>/dev/null
    fi
    if [ -n "$WATCH" ]; then
        : > "$STOP_FILE" 2>/dev/null
        wait "$WATCH" 2>/dev/null
    fi
    rm -f "$STOP_FILE"
    if [ "$KEEP_PROJECT" = "1" ]; then
        echo "[ghidra_decompile] project kept at $PROJECT_DIR"
    else
        rm -rf "$PROJECT_DIR"
    fi
}
on_signal() {
    [ "$CHILD" -gt 0 ] && kill -TERM -- -"$CHILD" 2>/dev/null
    exit 130
}
trap cleanup EXIT
trap on_signal INT TERM HUP

mkdir -p "$(dirname "$OUT")" 2>/dev/null || true

ARGS=(
    "$PROJECT_DIR" "proj"
    -import "$SO"
    -overwrite
    -scriptPath "$SCRIPT_PATH"
    -postScript DecompileSelected.java "$OUT" "$REGEX" "$MAX_FUNCTIONS" "$FUNC_TIMEOUT"
    -max-cpu 4
)
if [ "$NOANALYSIS" = "1" ]; then
    ARGS+=(-noanalysis)
else
    ANALYSIS_TIMEOUT=$((TIMEOUT - 45))
    [ "$ANALYSIS_TIMEOUT" -lt 60 ] && ANALYSIS_TIMEOUT=60
    ARGS+=(-analysisTimeoutPerFile "$ANALYSIS_TIMEOUT")
fi

echo "[ghidra_decompile] input : $SO ($(stat -c%s "$SO") bytes)"
echo "[ghidra_decompile] output: $OUT"
echo "[ghidra_decompile] regex : $REGEX"
echo "[ghidra_decompile] heap  : $HEAP, timeout: ${TIMEOUT}s, max funcs: $MAX_FUNCTIONS"

START=$(date +%s)
# Ghidra spawns a session of its own (launch.sh -> java -> decompile).
# Run it in a new session/process group so a timeout can kill the whole tree;
# GNU timeout alone only signals its direct child and can leave java running.
command -v setsid >/dev/null || {
    echo "ghidra_decompile.sh: setsid is required to enforce timeouts" >&2
    exit 2
}
GHIDRA_HEADLESS_MAXMEM="$HEAP" GHIDRA_MAXMEM="$HEAP" \
    setsid "$ANALYZE" "${ARGS[@]}" &
CHILD=$!
if [ "$TIMEOUT" -gt 0 ]; then
    (
        elapsed=0
        while [ "$elapsed" -lt "$TIMEOUT" ]; do
            sleep 1
            [ -e "$STOP_FILE" ] && exit 0
            elapsed=$((elapsed + 1))
        done
        if kill -0 "$CHILD" 2>/dev/null; then
            : > "$PROJECT_DIR/timed_out"
            kill -TERM -- -"$CHILD" 2>/dev/null
            sleep 10
            kill -KILL -- -"$CHILD" 2>/dev/null
        fi
    ) &
    WATCH=$!
fi
wait "$CHILD"
RC=$?
CHILD=0
if [ -n "$WATCH" ]; then
    : > "$STOP_FILE"
    wait "$WATCH" 2>/dev/null
fi
rm -f "$STOP_FILE"
ELAPSED=$(( $(date +%s) - START ))
if [ -e "$PROJECT_DIR/timed_out" ]; then
    RC=124
fi

if [ "$RC" -eq 124 ] || [ "$RC" -eq 137 ] || [ "$RC" -eq 143 ]; then
    echo "[ghidra_decompile] TIMEOUT after ${ELAPSED}s (limit ${TIMEOUT}s);" \
         "partial output may exist at $OUT" >&2
    exit 124
fi
if [ "$RC" -ne 0 ]; then
    echo "[ghidra_decompile] Ghidra failed with exit $RC after ${ELAPSED}s" >&2
    exit "$RC"
fi
if [ ! -s "$OUT" ]; then
    echo "[ghidra_decompile] ERROR: no output written to $OUT after ${ELAPSED}s" >&2
    exit 3
fi

FUNCS=$(grep -c '^ \* Function:' "$OUT" 2>/dev/null || true)
FUNCS=${FUNCS:-0}
echo "[ghidra_decompile] functions in output: $FUNCS (${ELAPSED}s, $(stat -c%s "$OUT") bytes)"

if [ "$FUNCS" -eq 0 ]; then
    echo "[ghidra_decompile] WARNING: regex matched no functions; try a broader regex" >&2
    exit 3
fi
exit 0
