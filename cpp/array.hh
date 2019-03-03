#ifndef _ARRAY_HH
#define _ARRAY_HH

struct StructA {
    int valueA;
    int valueB;
    long valueC;
};

class Array {
   public:
    Array()
        : structArray{new StructA[NUMBER_OF_ELEMENTS]},
          sumArray{new long[NUMBER_OF_ELEMENTS]} {}
    ~Array() {
        delete[] structArray;
        delete[] sumArray;
    }
    Array(const Array&) = delete;
    Array(Array&&) = delete;

    void fillStructArray();
    void fillSumArray();
    long sumOfArray() const;

   private:
    static constexpr int NUMBER_OF_ELEMENTS{1000000};
    StructA* structArray;
    long* sumArray;
};

#endif