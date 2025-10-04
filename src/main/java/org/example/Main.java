package org.example;

import org.example.algorithms.InsertionSort;
import org.example.metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;
import java.io.File;

public class Main {
    private static void initializeCsv(String csvFile) {
        try (FileWriter fw = new FileWriter(csvFile, false)) {
            fw.write("algorithm,input_size,comparisons,moves,array_accesses,time_ns\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] generateRandomArray(int size) {
        final int maxValue = 1_000_000;
        Random rand = new Random(3258);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(maxValue + 1); // 0..=maxValue
        }
        return arr;
    }

    public static void main(String[] args) {
        String csvFile = "insertion_sort.csv";
        initializeCsv(csvFile);

        int[] sizes = {100, 1000, 10000, 100000};

        System.out.println("Running predefined benchmarks...");
        System.out.println("Tip: Command-line interface is available. Refer to documentation for usage instructions.");
        System.out.println("\n--- Optimized InsertionSort ---");
        for (int size : sizes) {
            int[] arr = generateRandomArray(size);
            PerformanceTracker tracker = new PerformanceTracker("InsertionSort");
            InsertionSort.sort(arr, tracker);
            System.out.println("Size: " + size);
            System.out.println("  Comparisons: " + tracker.getComparisons());
            System.out.println("  Moves: " + tracker.getMoves());
            System.out.println("  Array accesses: " + tracker.getArrayAccesses());
            System.out.println("  Elapsed time: " + tracker.getElapsedTimeNanos());
            tracker.writeCsv(csvFile, arr.length);
        }
    }
}