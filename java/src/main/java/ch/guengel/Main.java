package ch.guengel;

import ch.guengel.support.LogFile;
import ch.guengel.support.Statistics;

public class Main {
    private static final Statistics STATISTICS = new Statistics();
    private static final String RESULTS_FILENAME = "java-results.csv";
    private static final LogFile LOG_FILE = new LogFile(RESULTS_FILENAME);
    private static final int ITERATIONS = 10_000;
    private static final String FILL_ARRAY_LABEL = "fillArray";
    private static final String FILL_SUM_ARRAY_LABEL = "fillSumArray";
    private static final String SUM_OF_ARRAY_LABEL = "sumOfArray";
    private static final String STATISTICS_FILENAME = "java-stats.csv";

    public static void main(String[] args) {
        try (LOG_FILE) {
            var array = new Array();

            var t0 = System.nanoTime();
            array.fillArray();
            var t1 = System.nanoTime();
            log(FILL_ARRAY_LABEL, 0, t0, t1);

            long sum = 0;
            for (var i = 0; i < ITERATIONS; i++) {
                t0 = System.nanoTime();
                array.fillSumArray();
                t1 = System.nanoTime();
                log(FILL_SUM_ARRAY_LABEL, i, t0, t1);

                t0 = System.nanoTime();
                sum = array.sumOfArray();
                t1 = System.nanoTime();
                log(SUM_OF_ARRAY_LABEL, i, t0, t1);
                printProgress(i);
            }
            System.out.println();
            System.out.println("Sum " + sum);
        }
        STATISTICS.dumpToFile(STATISTICS_FILENAME);
    }

    private static void log(String label, int iteration, long start, long end) {
        long duration = end - start;
        LOG_FILE.write(label, iteration, duration);
        STATISTICS.record(label, duration);
    }

    private static void printProgress(int i) {
        if ((i % 1000 == 0) && (i > 0)) {
            System.out.print('#');
            System.out.flush();
        }
    }
}
