import support.Log
import support.Statistics

const val ITERATIONS = 10_000

val log = Log("kotlin-results.csv")
val statistics = Statistics()

fun record(label: String, iteration: Int, start: Long, end: Long) {
    log.write(label, iteration, start, end)
    statistics.addData(label, start, end)
}

fun progress(i: Int) {
    if ((i % 1000 == 0) && (i > 0)) {
        print('#')
    }
}

fun main() {
    val arrays = Arrays()

    log.use {

        var t0 = System.nanoTime()
        arrays.fillStructArray()
        var t1 = System.nanoTime()
        record("fillStructArray", 0, t0, t1)

        var sum: Long = -1
        for (i in 0..(ITERATIONS - 1)) {
            t0 = System.nanoTime()
            arrays.fillSumArray()
            t1 = System.nanoTime()
            record("fillSumArray", i, t0, t1)

            t0 = System.nanoTime()
            sum = arrays.sumOfArray()
            t1 = System.nanoTime()
            record("sumOfArray", i, t0, t1)
            progress(i)
        }

        println()
        println("Sum: $sum")
    }

    statistics.dumpStatisticsToFile("kotlin-stats.csv")

}