package org.example.sorting_algoritms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class selection_sortTest {

    @Test
    void arrayShouldBeSorted(){
        int[] a = {4, 5, 2, 8, 1, 0};
        int[] aPraw = {0, 1, 2, 4, 5, 8};
        selection_sort.sortSel(a, a.length);
        assertArrayEquals(a, aPraw);
    }

    boolean checkOutput(int[] a, int n){

        boolean checking = true;

        for(int i = 1; i < n; i++){
            if(a[i] < a[i-1]){
                checking = false;
            }
        }
        return checking;

    }

    @Test
    void arrayShouldBeSortedVersionTwo(){
        int[] a = {4, 5, 2, 8, 1, 0};
        insertion_sort.sortIn(a, a.length);
        assertTrue(checkOutput(a, a.length));
    }
}
