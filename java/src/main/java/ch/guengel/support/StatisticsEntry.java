package ch.guengel.support;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

class StatisticsEntry {
    private final List<Long> dataPoints;
    private long min;
    private long max;

    public StatisticsEntry() {
        dataPoints = new ArrayList<>();
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;
    }

    public long getMin() {
        return min;
    }

    private void setMin(long min) {
        this.min = Long.min(this.min, min);
    }

    public long getMax() {
        return max;
    }

    private void setMax(long max) {
        this.max = Long.max(this.max, max);
    }

    public long getAverage() {
        OptionalDouble average = dataPoints.stream()
                .mapToLong(x -> x)
                .average();

        return Math.round(average.orElseThrow());
    }

    public void addDataPoint(long value) {
        dataPoints.add(value);
        setMax(value);
        setMin(value);
    }
}
