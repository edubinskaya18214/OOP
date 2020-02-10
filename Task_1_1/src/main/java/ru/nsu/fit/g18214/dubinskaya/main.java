package ru.nsu.fit.g18214.dubinskaya;
import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; ++i) {
            System.out.print(HeapSort.heapSort(arr)[i] + " ");
        }
    }
}
