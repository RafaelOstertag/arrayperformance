#include <memory>
#include <chrono>
#include <iostream>

struct StructA {
    int valueA;
    int valueB;
    long valueC;
};

class Array {
    public:
    Array() : structArray{new StructA[NUMBER_OF_ELEMENTS]} {}
    Array(const Array&) = delete;
    Array(Array&&) = delete;

    void fillArray();
    long sumUpForward() const;
    long sumUpBackward() const;

    private:
    static constexpr int NUMBER_OF_ELEMENTS{1000000};
    std::unique_ptr<StructA[]> structArray;
};

void Array::fillArray() {
    for(auto i=0; i<NUMBER_OF_ELEMENTS; i++) {
        structArray[i].valueA=i;
        structArray[i].valueB=i+1;
        structArray[i].valueC=i+2;
    }
}

long Array::sumUpForward() const {
    auto sum = 0l;
    for(auto i=0; i<NUMBER_OF_ELEMENTS; i++) {
        sum += structArray[i].valueA + 
                structArray[i].valueB +
                structArray[i].valueC;
    }
    return sum;
}

long Array::sumUpBackward() const {
    auto sum = 0l;
    for(auto i=NUMBER_OF_ELEMENTS-1; i>=0; i--) {
        sum += structArray[i].valueA + 
                structArray[i].valueB +
                structArray[i].valueC;
    }
    return sum;
}

void printWithNanos(const std::string& label, 
                    const std::chrono::high_resolution_clock::time_point& t0, 
                    const std::chrono::high_resolution_clock::time_point& t1) {
    std::cout << 
        label << 
        " took " <<
        std::chrono::duration_cast<std::chrono::microseconds>(t1 - t0).count()
        << "µs"
        << std::endl;
}

inline std::chrono::high_resolution_clock::time_point takeTime() {
    return std::chrono::high_resolution_clock::now();
}


int main(int, char**) {
    Array array;

    auto t0 = takeTime();
    array.fillArray();
    auto t1 = takeTime();
    printWithNanos("Filling array", t0, t1);

    t0 = takeTime();
    auto result = array.sumUpForward();
    t1 = takeTime();
    printWithNanos("Summing up forward", t0, t1);
    std::cout << "Result forward " << result << std::endl;
}