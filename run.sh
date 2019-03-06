#!/bin/bash
set -eu

source ./scriptlib/common.sh
EXCLUDED_TESTS="python"

function isExcluded() {
    local name="$1"

    { echo "${EXCLUDED_TESTS}" | grep "$name" >/dev/null ;} || return 1
}

for d in ${TEST_DIRECTORIES}
do
    if isExcluded "$d"
    then
        printAction "Exclude $d"
        continue
    fi

    pushd $d >/dev/null
    printAction "Start $d"
    ./run.sh
    popd >/dev/null
done

printAction "Results"

for d in ${TEST_DIRECTORIES}
do
    if isExcluded "$d"
    then
        continue
    fi
    cat "$d/$d-stats.csv"
done