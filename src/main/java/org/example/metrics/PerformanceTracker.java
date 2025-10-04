package org.example.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class PerformanceTracker {
    private final String algorithmName;
    private long comparisons;
    private long moves;
    private long arrayAccesses;
    private long startTime;
    private long endTime;

    public PerformanceTracker(String algorithmName) {
        this.algorithmName = algorithmName;
        this.comparisons = 0;
        this.moves = 0;
        this.arrayAccesses = 0;
        this.startTime = 0;
        this.endTime = 0;
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementMoves() {
        moves++;
    }

    public void incrementArrayAccesses() {
        arrayAccesses++;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getMoves() {
        return moves;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    public long getElapsedTimeNanos() {
        return (endTime - startTime);
    }

    public long getElapsedTimeMillis() {
        return (endTime - startTime) / 1_000_000;
    }

    public void reset() {
        comparisons = 0;
        moves = 0;
        arrayAccesses = 0;
        startTime = 0;
        endTime = 0;
    }

    public void writeCsv(String filename, int inputSize) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(algorithmName + "," +
                    inputSize + "," +
                    getComparisons() + "," +
                    getMoves() + "," +
                    getArrayAccesses() + "," +
                    getElapsedTimeNanos() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
