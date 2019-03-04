package ch.guengel.support;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Statistics {
    private final Map<String, StatisticsEntry> statisticsEntryMap = new LinkedHashMap<>();

    public void record(String label, long value) {
        var statisticsEntry = statisticsEntryMap.computeIfAbsent(label, x -> new StatisticsEntry());
        statisticsEntry.addDataPoint(value);
    }

    public void dumpToFile(String filename) {
        try (var fileWriter = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, StatisticsEntry> entry : statisticsEntryMap.entrySet()) {
                String key = entry.getKey();
                StatisticsEntry value = entry.getValue();
                var line = String.format("%s;%d;%d;%d\n", key, value.getMin(), value.getAverage(), value.getMax());
                fileWriter.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot write statistics file " + filename, e);
        }
    }
}

