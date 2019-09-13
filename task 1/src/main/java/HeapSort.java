
class HeapSort {
    private static void swap(int[] a, int i, int j) { // just fun to swap elements in arr
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int treeHigh;

    private static void seive(int[] a, int i) { // find "better" pos for a[i]
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;

        if (l < treeHigh && a[i] < a[l]) {         ////
            largest = l;                             //
        }                                            // find largest el from i,l,r
        if (r < treeHigh && a[largest] < a[r]) {     //
            largest = r;                             //
        }                                          ////
        if (i != largest) {
            swap(a, i, largest);
            seive(a, largest);
        }
    }

    public static int[] heapSort(int[] a) {
        treeHigh = a.length;
        for (int i = a.length / 2; i >= 0; i--) { // starting with last el that have children find right pos for every el
            seive(a, i);
        }
        while (treeHigh > 1) {
            swap(a, 0, treeHigh - 1); // swap 1 and last el
            treeHigh--;
            seive(a, 0);                 // find pos of 1 el
        }
        return a;
    }
}