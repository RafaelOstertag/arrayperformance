package ch.guengel;

public class Array {
    private static final int NUMBER_OF_ELEMENTS = 1_000_000;
    private Struct[] structArray = new Struct[NUMBER_OF_ELEMENTS];
    private long[] sumArray = new long[NUMBER_OF_ELEMENTS];

    public void fillArray() {
        for (var i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            structArray[i] = new Struct(i, i + 1, i + 2);
        }
    }

    public void fillSumArray() {
        Struct struct;
        for (var i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            struct = structArray[i];
            sumArray[i] = struct.valueA +
                    struct.valueB +
                    struct.valueC;
        }
    }

    long sumOfArray() {
        var sum = 0L;
        for (var subSum: sumArray) {
            sum += subSum;
        }
        return sum;
    }

}
