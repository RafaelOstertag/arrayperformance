#include <algorithm>
#include <cmath>
#include <fstream>
#include <limits>
#include <numeric>

#include "stat.hh"

StatisticsEntry::StatisticsEntry(const std::string& label)
    : dataPoints{},
      _label{label},
      _min{std::numeric_limits<long>::max()},
      _max{std::numeric_limits<long>::min()},
      _averageComputed{false},
      _average{0} {}

StatisticsEntry::StatisticsEntry(const StatisticsEntry& s)
    : dataPoints{s.dataPoints},
      _label{s._label},
      _min{s._min},
      _max{s._max},
      _averageComputed{s._averageComputed},
      _average{s._average} {}

StatisticsEntry::StatisticsEntry(StatisticsEntry&& s)
    : dataPoints{std::move(s.dataPoints)},
      _label{std::move(s._label)},
      _min{s._min},
      _max{s._max},
      _averageComputed{s._averageComputed},
      _average{s._average} {}

StatisticsEntry& StatisticsEntry::operator=(const StatisticsEntry& s) {
    if (this == &s) return *this;

    dataPoints = s.dataPoints;
    _label = s._label;
    _min = s._min;
    _max = s._max;
    _averageComputed = s._averageComputed;
    _average = s._average;

    return *this;
}

StatisticsEntry& StatisticsEntry::operator=(StatisticsEntry&& s) {
    if (this == &s) return *this;

    dataPoints = std::move(s.dataPoints);
    _label = s._label;
    _min = s._min;
    _max = s._max;
    _averageComputed = s._averageComputed;
    _average = s._average;

    return *this;
}

void StatisticsEntry::addDataPoint(long data) {
    dataPoints.push_back(data);
    _averageComputed = false;

    _min = std::min(_min, data);
    _max = std::max(_max, data);
}

long StatisticsEntry::average() const {
    if (!_averageComputed) {
        long sum = std::accumulate(dataPoints.begin(), dataPoints.end(), 0);
        _average = std::lround(sum / dataPoints.size());
        _averageComputed = true;
    }

    return _average;
}

Statistics::Statistics() : statisticsMap{} {}

void Statistics::record(const std::string& label, long value) {
    auto pair = statisticsMap.emplace(label, StatisticsEntry{label});
    auto iter = pair.first;
    iter->second.addDataPoint(value);
}

void Statistics::dumpToFile(const std::string& filename) const {
    auto file{std::ofstream{}};
    file.open(filename, std::ios::trunc);

    std::for_each(statisticsMap.begin(), statisticsMap.end(), [&file](auto x) {
        auto stats = x.second;
        file << stats.label() << ";" << stats.min() << ";" << stats.average()
             << ";" << stats.max() << std::endl;
    });
    file.close();
}
