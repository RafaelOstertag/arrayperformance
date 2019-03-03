package ch.guengel;

public class Main {
    private static final int ITERATIONS = 10_000;
    public static void main(String[] args) {
        try (var logFile = new LogFile("java-results.csv")) {

            var array = new Array();

            var t0 = System.nanoTime();
            array.fillArray();
            var t1 = System.nanoTime();
            logFile.write("fillArray", 0, t1 - t0);

            long sum = 0;
            for (var i = 0; i < ITERATIONS; i++) {
                t0 = System.nanoTime();
                array.fillSumArray();
                t1 = System.nanoTime();
                logFile.write("fillSumArray", i, t1 - t0);

                t0 = System.nanoTime();
                sum = array.sumOfArray();
                t1 = System.nanoTime();
                logFile.write("sumOfArray", i, t1-t0);
                printProgress(i);
            }
            System.out.println();
            System.out.println("SumOfArray:" + sum);
        }
    }

    private static void printProgress(int i) {
        if ((i%1000 == 0) && (i>0)) {
            System.out.print('#');
            System.out.flush();
        }
    }
}
