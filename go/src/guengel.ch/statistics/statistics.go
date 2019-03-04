package statistics

import (
	"fmt"
	"math"
	"os"
	"time"
)

type statisticsEntry struct {
	min        int64
	max        int64
	dataPoints []int64
}

type Statistics struct {
	statistics map[string]statisticsEntry
}

func minInt64(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}

func maxInt64(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}

func newStatisticsEntry(label string) *statisticsEntry {
	return &statisticsEntry{
		math.MaxInt64,
		math.MinInt64,
		make([]int64, 0),
	}
}

func (se *statisticsEntry) addDataPoint(value int64) {
	se.dataPoints = append(se.dataPoints, value)
	se.max = maxInt64(se.max, value)
	se.min = minInt64(se.min, value)
}

func (se *statisticsEntry) average() int64 {
	var sum int64
	for _, v := range se.dataPoints {
		sum += v
	}

	return int64(math.Round(float64(sum) / float64(len(se.dataPoints))))
}

func Start() *Statistics {
	return &Statistics{make(map[string]statisticsEntry)}
}

func (s *Statistics) Record(label string, start, end time.Time) {
	value := end.Sub(start).Nanoseconds()

	entry, exists := s.statistics[label]
	if !exists {
		stats := *newStatisticsEntry(label)
		stats.addDataPoint(value)
		s.statistics[label] = stats
	} else {
		entry.addDataPoint(value)
		s.statistics[label] = entry
	}
}

func (s *Statistics) DumpToFile(filename string) {
	file, err := os.Create(filename)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	for k, v := range s.statistics {
		stat := fmt.Sprintf("go-array;%s;%v;%v;%v\n", k, v.min, v.average(), v.max)
		file.WriteString(stat)
	}
}
