package ch.guengel;

public class Main {
    private static void printWithNanos(String label, long t0, long t1) {
        System.out.printf("%s took %dµs\n", label, t1 - t0);
    }

    public static void main(String[] args) {
        Array array = new Array();

        long t0 = System.nanoTime();
        array.fillArray();
        long t1 = System.nanoTime();
        printWithNanos("Fill array", t0, t1);

        t0 = System.nanoTime();
        long result = array.sumUpForward();
        t1 = System.nanoTime();
        printWithNanos("Summing up forward", t0, t1);

        System.out.println("Result " + result);
    }
}
