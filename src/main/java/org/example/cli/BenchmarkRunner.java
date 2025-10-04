package org.example.cli;

import org.example.algorithms.InsertionSort;
import org.example.metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.io.File;

public class BenchmarkRunner {
    public static void runCli(String[] args) {
        if (args.length == 0 || Arrays.asList(args).contains("--help")) {
            printCliUsage();
            return;
        }

        int size = -1;
        String csvFile = null;
        long seed = 3258;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--size":
                    if (i + 1 < args.length) size = Integer.parseInt(args[++i]);
                    break;
                case "--csv":
                    if (i + 1 < args.length) csvFile = args[++i];
                    break;
                case "--seed":
                    if (i + 1 < args.length) seed = Long.parseLong(args[++i]);
                    break;
            }
        }

        if (size <= 0 || csvFile == null) {
            System.err.println("Missing required arguments.");
            printCliUsage();
            return;
        }

        // Write CSV header if file does not exist
        File file = new File(csvFile);
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter(csvFile, false)) {
                fw.write("algorithm,input_size,comparisons,moves,array_accesses,time_ns\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Random random = new Random(seed);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = random.nextInt(1_000_001);
        PerformanceTracker tracker = new PerformanceTracker("InsertionSort");
        InsertionSort.sort(arr, tracker);
        tracker.writeCsv(csvFile, size);
        printCliSummary(size, tracker, csvFile);
    }

    private static void printCliUsage() {
        System.out.println("Usage: java -jar algorithms.jar --size <n> --csv <file> [--seed <seed>]");
        System.out.println("Example: java -jar algorithms.jar --size 10000 --csv results.csv");
    }

    private static void printCliSummary(int size, PerformanceTracker tracker, String csvFile) {
        System.out.printf("InsertionSort: n=%d, time(ns)=%d, comparisons=%d, moves=%d, array_accesses=%d, CSV=%s\n",
            size,
            tracker.getElapsedTimeNanos(),
            tracker.getComparisons(),
            tracker.getMoves(),
            tracker.getArrayAccesses(),
            csvFile);
    }

    public static void main(String[] args) {
        runCli(args);
    }
}
