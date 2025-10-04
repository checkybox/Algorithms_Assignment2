package algorithms;

import org.example.algorithms.InsertionSort;
import org.example.metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InsertionSortTest {
    @Test
    void testEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        PerformanceTracker tracker = new PerformanceTracker("InsertionSort");
        InsertionSort.sort(arr, tracker);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        int[] expected = {42};
        PerformanceTracker tracker = new PerformanceTracker("InsertionSort");
        InsertionSort.sort(arr, tracker);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testDuplicates() {
        int[] arr = {5, 1, 3, 3, 2, 5, 1};
        int[] expected = {1, 1, 2, 3, 3, 5, 5};
        PerformanceTracker tracker = new PerformanceTracker("InsertionSort");
        InsertionSort.sort(arr, tracker);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        PerformanceTracker tracker = new PerformanceTracker("InsertionSort");
        InsertionSort.sort(arr, tracker);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        PerformanceTracker tracker = new PerformanceTracker("InsertionSort");
        InsertionSort.sort(arr, tracker);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArray() {
        Random rand = new Random(3258);
        for (int t = 0; t < 20; t++) {
            int size = rand.nextInt(100) + 1; // 1 to 100
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) arr[i] = rand.nextInt(1000);
            int[] expected = Arrays.copyOf(arr, arr.length);
            Arrays.sort(expected);
            PerformanceTracker tracker = new PerformanceTracker("InsertionSort");
            InsertionSort.sort(arr, tracker);
            assertArrayEquals(expected, arr);
        }
    }

    @Test
    void testCrossValidation() {
        int[] arr = {9, 2, 7, 4, 1, 5, 3, 8, 6};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);
        PerformanceTracker tracker = new PerformanceTracker("InsertionSort");
        InsertionSort.sort(arr, tracker);
        assertArrayEquals(expected, arr);
    }
}
