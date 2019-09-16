import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("size of arr: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.print("arr: ");
        for (int i = 0; i < n; ++i) {
            arr[i] = scanner.nextInt();
        }
        HeapSort sorted_arr = new HeapSort();
        for (int i = 0; i < n; ++i) {
            System.out.print(sorted_arr.heapSort(arr)[i] + " ");
        }
    }
}
