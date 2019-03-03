package main

import (
	"fmt"
	"time"

	"guengel.ch/array"
	"guengel.ch/logfile"
	"guengel.ch/statistics"
)

const (
	iterations           = 10000
	resultsFile          = "go-results.csv"
	statisticsFile       = "go-stats.csv"
	fillStructArrayLabel = "fillStructArray"
	fillSumArrayLabel    = "fillSumArray"
	sumOfArrayLabel      = "sumOfArray"
)

var (
	logf  = logfile.New(resultsFile)
	stats = statistics.Start()
)

func progress(i int) {
	if (i%1000 == 0) && (i > 0) {
		fmt.Printf("#")
	}
}

func record(label string, iteration int, start, end time.Time) {
	logf.Write(label, iteration, start, end)
	stats.Record(label, start, end)

}

func main() {
	defer logf.Close()

	arr := array.New()

	start := time.Now()
	arr.FillStructArray()
	end := time.Now()
	record(fillStructArrayLabel, 0, start, end)

	var sum int64
	for i := 0; i < iterations; i++ {
		start = time.Now()
		arr.FillSumArray()
		end = time.Now()
		record(fillSumArrayLabel, i, start, end)

		start = time.Now()
		sum = arr.SumOfArray()
		end = time.Now()
		record(sumOfArrayLabel, i, start, end)

		progress(i)
	}
	fmt.Printf("\nSum %d\n", sum)
	stats.DumpToFile(statisticsFile)
}
