#!/usr/bin/env python3.7

import time

ITERATIONS = 10
statistics = {}

class Array(object):
    number_of_elements = 1000000

    def __init__(self):
        self._struct_array = [{'valueA': 0, 'valueB': 0, 'valueC': 0} for _ in range(0,Array.number_of_elements)]
        self._sum_array = [0 for _ in range(0,Array.number_of_elements)]

    def fill_array(self):
        for i in range(0,Array.number_of_elements):
            struct = self._struct_array[i]
            struct['valueA'] = i
            struct['valueB'] = i + 1
            struct['valueC'] = i + 2

    def fill_sum_array(self):
        for i in range(0,Array.number_of_elements):
            struct = self._struct_array[i]
            self._sum_array[i] = struct['valueA'] + struct['valueB'] + struct['valueC']
    
    def sum_of_array(self):
        return sum(self._sum_array)

class StatisticsEntry(object):
    def __init__(self):
        self.min = None
        self.max = None
        self._data_points = []

    def add_data_point(self, val):
        if self.min is None:
            self.min = val
        else:
            self.min = min(self.min, val)

        if self.max is None:
            self.max = val
        else:
            self.max = max(self.max,val)

        self._data_points.append(val)

    def average(self):
        return round(
            float(sum(self._data_points)) / max(len(self._data_points),1)
            )
        

def progress(i):
    if (i%10 == 0) and (i>0):
        print('#')

def log(label, iteration, start, end):
    if label in statistics:
        statistics_entry = statistics[label]
    else:
        statistics_entry = StatisticsEntry()
        statistics[label] = statistics_entry

    statistics_entry.add_data_point(end-start)


def exercise(array):
    t0 = time.perf_counter_ns()
    array.fill_array()
    t1 = time.perf_counter_ns()
    log("fillStructArray",0, t0, t1)

    for i in range(0, ITERATIONS):
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

def dump_statistics(filename):
    for k,v in statistics.items():
        print("python-array;{};{};{};{}".format(k,v.min,v.average(),v.max))

if __name__ == "__main__":
    array = Array()
    exercise(array)
    dump_statistics("bla")