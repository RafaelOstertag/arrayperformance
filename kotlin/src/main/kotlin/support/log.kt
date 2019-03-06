package support

import java.io.BufferedWriter
import java.io.FileWriter

class Log(filename: String) : AutoCloseable {
    private val writer = BufferedWriter(FileWriter(filename))

    fun write(label: String, iteration: Int, start: Long, end: Long) {
        val duration = end - start
        writer.write("${PREFIX};$label;$iteration;$duration\n")
    }

    override fun close() = writer.close()
}