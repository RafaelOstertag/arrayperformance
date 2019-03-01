package ch.guengel;

public class Main {
    private static final int ITERATIONS = 10_000;
    public static void main(String[] args) {
        try (var logFile = new LogFile("results-java.csv")) {

            var array = new Array();

            var t0 = System.nanoTime();
            array.fillArray();
            var t1 = System.nanoTime();
            logFile.write("fillArray", 0, t1 - t0);

            long sum = 0;
            for (var i = 0; i < ITERATIONS; i++) {
                t0 = System.nanoTime();
                sum = array.sumUpForward();
                t1 = System.nanoTime();
                logFile.write("sumUpForward", i, t1 - t0);
            }
            System.out.println(sum);
        }
    }
}
