package main

import (
	"fmt"
	"time"

	"guengel.ch/array"
	"guengel.ch/logfile"
	"guengel.ch/statistics"
)

const (
	iterations = 100
)

func progress(i int) {
	if (i%1000 == 0) && (i > 0) {
		fmt.Printf("#")
	}
}

func main() {
	arr := array.New()
	start := time.Now()
	arr.FillStructArray()
	end := time.Now()

	logf := logfile.New("go-results.csv")
	defer logf.Close()

	logf.Write("fillStructArray", 0, start, end)
	stats := statistics.Start()
	stats.Record("fillStructArray", start, end)

	var sum int64
	for i := 0; i < iterations; i++ {
		start = time.Now()
		arr.FillSumArray()
		end = time.Now()

		logf.Write("fillSumArray", i, start, end)
		stats.Record("fillSumArray", start, end)

		start = time.Now()
		sum = arr.SumOfArray()
		end = time.Now()

		logf.Write("sumOfArray", i, start, end)
		stats.Record("sumOfArray", start, end)

		progress(i)
	}
	fmt.Printf("\nSum %d\n", sum)
	stats.DumpToFile("go-stats.csv")
}
