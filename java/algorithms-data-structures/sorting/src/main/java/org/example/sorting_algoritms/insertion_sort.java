package org.example.sorting_algoritms;

public class insertion_sort {

    public static void sortIn(int[] a, int n){
        for(int i = 1; i < n; i++){
            int j = i;
            int currNum = a[i];
            while(j > 0 && currNum < a[j - 1]){
                a[j] = a[j - 1];
                j--;
            }
            a[j] = currNum;
        }
    }

}
