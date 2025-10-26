package org.example.sorting_algoritms;

import static org.example.swapTwoNums.swap;

public class selection_sort {

    public static void sortSel(int[] a, int n){
        for(int i = 0; i < n; i++){
            int minI = i;
            for(int j = i + 1; j < n; j++){
                if(a[j] < a[minI]){
                    minI = j;
                }
            }
            swap(a, i, minI);
        }
    }



}
