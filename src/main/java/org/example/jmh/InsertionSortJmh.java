package org.example.jmh;

import org.example.algorithms.InsertionSort;
import org.example.metrics.PerformanceTracker;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class InsertionSortJmh {
    @Param({"100", "1000", "10000", "100000"})
    public int size;

    public int[] randomArray;
    public int[] sortedArray;
    public int[] reverseArray;
    public int[] nearlySortedArray;

    public PerformanceTracker performanceTracker = new PerformanceTracker("InsertionSortJmh");

    @Setup(Level.Trial)
    public void setUp() {
        Random rand = new Random(3258);
        randomArray = new int[size];
        for (int i = 0; i < size; i++) randomArray[i] = rand.nextInt(1_000_001);

        sortedArray = new int[size];
        for (int i = 0; i < size; i++) sortedArray[i] = i + 1;

        reverseArray = new int[size];
        for (int i = 0; i < size; i++) reverseArray[i] = size - i;

        // Nearly sorted: start with sorted, swap 1% of elements
        nearlySortedArray = Arrays.copyOf(sortedArray, size);
        int swaps = Math.max(1, size / 100);
        for (int i = 0; i < swaps; i++) {
            int a = rand.nextInt(size);
            int b = rand.nextInt(size);
            int tmp = nearlySortedArray[a];
            nearlySortedArray[a] = nearlySortedArray[b];
            nearlySortedArray[b] = tmp;
        }
    }

    @Benchmark
    public void insertionSortRandom() {
        int[] arr = Arrays.copyOf(randomArray, size);
        InsertionSort.sort(arr, performanceTracker);
    }

    @Benchmark
    public void insertionSortSorted() {
        int[] arr = Arrays.copyOf(sortedArray, size);
        InsertionSort.sort(arr, performanceTracker);
    }

    @Benchmark
    public void insertionSortReverse() {
        int[] arr = Arrays.copyOf(reverseArray, size);
        InsertionSort.sort(arr, performanceTracker);
    }

    @Benchmark
    public void insertionSortNearlySorted() {
        int[] arr = Arrays.copyOf(nearlySortedArray, size);
        InsertionSort.sort(arr, performanceTracker);
    }
}
