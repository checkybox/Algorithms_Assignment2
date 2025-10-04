package org.example.algorithms;

import org.example.metrics.PerformanceTracker;

public class InsertionSort {
    public static void sort(int[] arr, PerformanceTracker tracker) {
        tracker.reset();
        tracker.startTimer();
        for (int i = 1; i < arr.length; i++) {
            tracker.incrementArrayAccesses(); // arr[i] read
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0) {
                tracker.incrementArrayAccesses(); // arr[j] read for comparison
                tracker.incrementComparisons();
                if (arr[j] > temp) {
                    tracker.incrementArrayAccesses(); // arr[j] read for assignment
                    tracker.incrementArrayAccesses(); // arr[j + 1] write
                    arr[j + 1] = arr[j];
                    tracker.incrementMoves();
                    j--;
                } else {
                    break;
                }
            }
            tracker.incrementArrayAccesses(); // arr[j + 1] write
            arr[j + 1] = temp;
            tracker.incrementMoves();
        }
        tracker.stopTimer();
    }
}
