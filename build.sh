#!/bin/bash

TEST_DIRECTORIES="java cpp go"

for d in ${TEST_DIRECTORIES}
do
    pushd $d
    ./build.sh
    popd
done