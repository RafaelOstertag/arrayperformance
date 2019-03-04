#!/bin/bash
set -eu

TEST_DIRECTORIES="java cpp go"

for d in ${TEST_DIRECTORIES}
do
    pushd $d >/dev/null
    echo ""
    echo "*** Start $d ***"
    ./run.sh
    popd >/dev/null
done