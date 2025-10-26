package org.example.sorting_algoritms;

import java.util.Stack;
import java.util.Random;

import static org.example.swapTwoNums.swap;

public class QuickSortIterative {

    public static void sortQui(int[] a) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, a.length - 1});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int start = range[0];
            int end = range[1];

            if (start >= end) continue;

            int pivotIndex = partition(a, start, end);


            if (pivotIndex + 1 < end) {
                stack.push(new int[]{pivotIndex + 1, end});
            }
            if (start < pivotIndex) {
                stack.push(new int[]{start, pivotIndex});
            }
        }
    }

    private static int partition(int[] a, int i, int j) {
        Random random = new Random();
        int pivotIndex = random.nextInt(i, j + 1);
        int pivotValue = a[pivotIndex];

        while (i <= j) {
            while (a[i] < pivotValue) i++;
            while (a[j] > pivotValue) j--;
            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }
        return j;
    }

}