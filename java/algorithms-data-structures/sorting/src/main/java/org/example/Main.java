package org.example;

import org.example.sorting_algoritms.*;

import java.util.*;


public class Main {
    static void main() {
        Random random = new Random();
        int[] a = new int[10];
        for(int i = 0; i < a.length; i++){
            a[i] = random.nextInt(0, 100);
        }

        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }

        System.out.println();

        //selection_sort.sortSel(a, a.length);
        //insertion_sort.sortIn(a, a.length);
        //quick_sort_rec.quick_sort(a, 0, a.length - 1);
        //quick_sort_rec.sortQui(a, 0, a.length-1);
        //QuickSortIterative.sortQui(a);
        MergeSort.mergesort(a);
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
    }
}
