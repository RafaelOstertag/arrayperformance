#include <chrono>
#include <iostream>
#include <memory>

#include "logfile.hh"

namespace {
LogFile logfile("cpp-results.csv");
constexpr int ITERATIONS{10000};
}  // namespace

struct StructA {
    int valueA;
    int valueB;
    long valueC;
};

class Array {
   public:
    Array() : structArray{new StructA[NUMBER_OF_ELEMENTS]} {}
    ~Array() { delete[] structArray; }
    Array(const Array&) = delete;
    Array(Array&&) = delete;

    void fillArray();
    long sumUpForward() const;

   private:
    static constexpr int NUMBER_OF_ELEMENTS{1000000};
    StructA* structArray;
};

void Array::fillArray() {
    for (auto i = 0; i < NUMBER_OF_ELEMENTS; i++) {
        StructA& ref = structArray[i];
        ref.valueA = i;
        ref.valueB = i + 1;
        ref.valueC = i + 2;
    }
}

long Array::sumUpForward() const {
    auto sum = 0l;
    for (auto i = 0; i < NUMBER_OF_ELEMENTS; i++) {
        StructA& ref = structArray[i];
        sum += ref.valueA + ref.valueB + ref.valueC;
    }
    return sum;
}

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

int main(int, char**) {
    Array array;

    auto t0 = takeTime();
    array.fillArray();
    auto t1 = takeTime();
    log("fillArray", 0, t0, t1);

    long result = -1;
    for (auto i = 0; i < ITERATIONS; i++) {
        t0 = takeTime();
        result = array.sumUpForward();
        t1 = takeTime();
        log("sumUpForward", i, t0, t1);
    }
    std::cout << "Result " << result << std::endl;
}