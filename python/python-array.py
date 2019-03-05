#!/usr/bin/env python3.7

import time
import support

iterations = 10000
statistics = support.Statistics()
logfile = support.Log("python-results.csv")


class Array(object):
    number_of_elements = 1000000

    def __init__(self):
        self._struct_array = [{'valueA': 0, 'valueB': 0, 'valueC': 0}
                              for _ in range(0, Array.number_of_elements)]
        self._sum_array = [0 for _ in range(0, Array.number_of_elements)]

    def fill_array(self):
        self._struct_array = list(map(lambda i: {'valueA': i, 'valueB': i+1, 'valueC': i+2},
                                      range(0, Array.number_of_elements)))

    def fill_sum_array(self):
        self._sum_array = list(map(lambda struct: sum(struct.values()), self._struct_array))

    def sum_of_array(self):
        return sum(self._sum_array)


def progress(i):
    if (i % 1000 == 0) and (i > 0):
        print('#', end="")


def log(label, iteration, start, end):
    logfile.write(label, iteration, start, end)
    statistics.record(label, start, end)


def exercise(array):
    t0 = time.perf_counter_ns()
    array.fill_array()
    t1 = time.perf_counter_ns()
    log("fillStructArray", 0, t0, t1)

    for i in range(0, iterations):
        t0 = time.perf_counter_ns()
        array.fill_sum_array()
        t1 = time.perf_counter_ns()
        log("fillSumArray", i, t0, t1)

        t0 = time.perf_counter_ns()
        sum = array.sum_of_array()
        t1 = time.perf_counter_ns()
        log("sumOfArray", i, t0, t1)

        progress(i)

    print("Sum {}".format(sum))


if __name__ == "__main__":
    array = Array()
    exercise(array)
    logfile.close()
    statistics.dump_to_file("python-stats.csv")
