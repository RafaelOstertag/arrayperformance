#ifndef _STAT_HH
#define _STAT_HH

#include <map>
#include <string>
#include <vector>

class StatisticsEntry {
   public:
    StatisticsEntry(const std::string& label);
    StatisticsEntry(const StatisticsEntry& s);
    StatisticsEntry(StatisticsEntry&& s);
    StatisticsEntry& operator=(const StatisticsEntry& s);
    StatisticsEntry& operator=(StatisticsEntry&& s);

    void addDataPoint(long data);

    const std::string& label() const { return _label; }
    long average() const;
    long max() const { return _max; }
    long min() const { return _min; }

   private:
    std::vector<long> dataPoints;
    std::string _label;
    long _min;
    long _max;
    mutable bool _averageComputed;
    mutable long _average;
};

class Statistics {
   public:
    Statistics();
    Statistics(const Statistics&) = delete;
    Statistics(Statistics&&) = delete;
    Statistics& operator=(const Statistics&) = delete;
    Statistics& operator=(Statistics&&) = delete;

    void record(const std::string& label, long value);
    void dumpToFile(const std::string& filename) const;

   private:
    std::map<std::string, StatisticsEntry> statisticsMap;
};

#endif