package ch.guengel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile implements AutoCloseable{
    private final BufferedWriter logWriter;

    LogFile(String logfile) {
        try {
            logWriter = new BufferedWriter(new FileWriter(logfile));
        } catch (IOException e) {
            throw new RuntimeException("Cannot open file " + logfile, e);
        }
    }

    void write(String label, int iteration, long durationNs) {
        try {
            logWriter.write("java-array;" + label + ";" + iteration + ";" + durationNs);
            logWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to log file", e);
        }
    }

    @Override
    public void close()  {
        try {
            logWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot close file", e);
        }
    }
}
