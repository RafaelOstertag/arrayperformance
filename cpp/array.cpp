#include <memory>

#include "array.hh"

void Array::fillStructArray() {
    for (auto i = 0; i < NUMBER_OF_ELEMENTS; i++) {
        StructA& ref = structArray[i];
        ref.valueA = i;
        ref.valueB = i + 1;
        ref.valueC = i + 2;
    }
}

void Array::fillSumArray() {
    StructA* ptr;
    for (auto i = 0; i < NUMBER_OF_ELEMENTS; i++) {
        ptr = structArray + i;
        sumArray[i] = ptr->valueA + ptr->valueB + ptr->valueC;
    }
}

long Array::sumOfArray() const {
    auto sum = 0l;
    for (auto i = 0; i < NUMBER_OF_ELEMENTS; i++) {
        sum += sumArray[i];
    }
    return sum;
}
