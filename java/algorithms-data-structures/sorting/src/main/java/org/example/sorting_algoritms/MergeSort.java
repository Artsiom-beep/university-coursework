package org.example.sorting_algoritms;

public class MergeSort {

    public static void mergesort(int[] a){

        int lenght = a.length;
        if(lenght <= 1) return;

        int midd = lenght/2;
        int[] left = new int[midd];
        int[] right = new int[lenght - midd];

        int i = 0;
        int j = 0;
        for(; i < lenght; i++){
            if(i < midd){
                left[i] = a[i];
            }
            else{
                right[j] = a[i];
                j++;
            }
        }
        mergesort(left);
        mergesort(right);
        merge(left, right, a);

    }

    public static void merge(int[] left, int[] right, int[] a){

        int leftSize = a.length / 2;
        int rightSize = a.length - leftSize;
        int i = 0;
        int l = 0;
        int r = 0;


        while(l < leftSize && r < rightSize){

            if(left[l] < right[r]){
                a[i] = left[l];
                i++;
                l++;
            }
            else{
                a[i] = right[r];
                i++;
                r++;
            }
        }

        while(l < leftSize){
            a[i] = left[l];
            i++;
            l++;
        }

        while(r < rightSize){
            a[i] = right[r];
            r++;
            i++;
        }


    }

}
