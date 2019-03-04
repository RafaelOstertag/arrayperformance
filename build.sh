#!/bin/bash
set -eu

TEST_DIRECTORIES="java cpp go python"

function err() {
    echo "$@" 1>&2
}

function commandNotFound() {
    local command="$1"
    err "$command not found in \$PATH=$PATH"
}

function testCommandPresence() {
    local command="$1"
    which "$command" >/dev/null 2>&1
}

function commandPresentOrDie() {
    local command="$1"
    if ! testCommandPresence "${command}"
    then
        err "${command} not found in \$PATH=$PATH"
        exit 1
    fi

    echo "${command} found"
}

function preflight() {
    commandPresentOrDie java
    commandPresentOrDie go
    commandPresentOrDie clang++
    commandPresentOrDie python3.7

    export GO=go
    export GOROOT=$(goRoot `which go`)
    export CXX=clang++
    export PYTHON=python3.7
}

function goRoot() {
    local goBin="$1"
    dirname `dirname "${goBin}"`
}

preflight

for d in ${TEST_DIRECTORIES}
do
    pushd $d >/dev/null
    echo ""
    echo "*** Build $d ***"
    ./build.sh
    popd >/dev/null
done