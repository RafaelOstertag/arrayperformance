#include <chrono>
#include <iostream>

#include "array.hh"
#include "logfile.hh"

namespace {
LogFile logfile("cpp-results.csv");
constexpr int ITERATIONS{10000};
}  // namespace

void log(const std::string& label, int iteration,
         const std::chrono::high_resolution_clock::time_point& t0,
         const std::chrono::high_resolution_clock::time_point& t1) {
    auto duration =
        std::chrono::duration_cast<std::chrono::nanoseconds>(t1 - t0).count();
    logfile.write(label, iteration, duration);
}

inline std::chrono::high_resolution_clock::time_point takeTime() {
    return std::chrono::high_resolution_clock::now();
}

void printProgress(int i) {
    if ((i % 1000 == 0) && i > 0) {
        std::cout << '#';
        std::cout.flush();
    }
}

int main(int, char**) {
    Array array;

    auto t0 = takeTime();
    array.fillStructArray();
    auto t1 = takeTime();
    log("fillStructArray", 0, t0, t1);

    long result = 0;
    for (auto i = 0; i < ITERATIONS; i++) {
        t0 = takeTime();
        array.fillSumArray();
        t1 = takeTime();
        log("fillSumArray", i, t0, t1);

        t0 = takeTime();
        result = array.sumOfArray();
        t1 = takeTime();
        log("sumOfArray", i, t0, t1);
        printProgress(i);
    }
    std::cout << std::endl << "SumOfArray: " << result << std::endl;
}