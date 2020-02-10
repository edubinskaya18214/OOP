package ru.nsu.fit.g18214.dubinskaya;

public class HeapSort {

    private static void swap(int[] a, int i, int j) { // just fun to swap elements in arr
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private static void seive(int[] a, int i, int treeHigh) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;

        if (l < treeHigh && a[i] < a[l]) {
            largest = l;
        }
        if (r < treeHigh && a[largest] < a[r]) {
            largest = r;
        }
        if (i != largest) {
            swap(a, i, largest);
            seive(a, largest, treeHigh);
        }
    }
    public static int[] heapSort(int[] a) {
        if (a == null)
            return null;
        int treeHigh = a.length;
        for (int i = a.length / 2; i >= 0; i--) {
            HeapSort.seive(a, i, treeHigh);
        }
        while (treeHigh > 1) {
            swap(a, 0, treeHigh - 1);
            treeHigh--;
            seive(a, 0, treeHigh);
        }
        return a;
    }
}