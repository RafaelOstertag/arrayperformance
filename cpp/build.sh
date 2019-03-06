#!/bin/bash

set -eu

make clean
make all CXX=${CXX:-c++}