package org.example.sorting_algoritms;

import java.util.Random;

import static org.example.swapTwoNums.swap;

public class quick_sort_rec {

    public static void sortQui(int[] a, int start, int finish){
        int i = start;
        int j = finish;
        if(start >= finish) return;
        int boundary = partition(a, i, j);
        sortQui(a, start, boundary);
        sortQui(a, boundary + 1, finish);

    }


    public static int partition(int[] a, int i, int j){
        Random random = new Random();
        int pivot = random.nextInt(i, j + 1);
        int pivotValue = a[pivot];
        while(i <= j){
            while(i <= j && a[i] < pivotValue){
                i++;
            }
            while(i <= j && a[j] > pivotValue){
                j--;
            }
            if(i <= j){
            swap(a, i, j);
            i++;
            j--;
            }

        }
        return j;
    }


}
