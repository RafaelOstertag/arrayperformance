package support

import java.io.BufferedWriter
import java.io.FileWriter
import kotlin.math.round

private class StatisticsEntry {
    private val dataPoints = ArrayList<Long>()
    var max = Long.MIN_VALUE
        private set(value) {
            field = kotlin.math.max(field, value)
        }
    var min = Long.MAX_VALUE
        private set (value) {
            field = kotlin.math.min(field, value)
        }

    fun addData(value: Long) {
        max = value
        min = value
        dataPoints.add(value)
    }

    fun average(): Long = round(dataPoints.average()).toLong()
}

class Statistics {
    private val statisticsMap = HashMap<String, StatisticsEntry>()

    fun addData(label: String, start: Long, end: Long) {
        val entry = statisticsMap.getOrPut(label) { StatisticsEntry() }
        entry.addData(end - start)
    }

    fun dumpStatisticsToFile(filename: String) {
        BufferedWriter(FileWriter(filename)).use {
            for ((k, v) in statisticsMap) {
                it.write("${PREFIX};$k;${v.min};${v.average()};${v.max}\n")
            }
        }
    }
}