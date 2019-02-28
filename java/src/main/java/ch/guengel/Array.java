package ch.guengel;

public class Array {
    private static final int NUMBER_OF_ELEMENTS = 1_000_000;
    private Struct[] structArray = new Struct[NUMBER_OF_ELEMENTS];

    public void fillArray() {
        for (var i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            structArray[i] = new Struct(i, i + 1, i + 2);
        }
    }

    public long sumUpForward() {
        var sum = 0L;
        for (Struct struct : structArray) {
            sum += struct.valueA +
                    struct.valueB +
                    struct.valueC;
        }
        return sum;
    }

}
