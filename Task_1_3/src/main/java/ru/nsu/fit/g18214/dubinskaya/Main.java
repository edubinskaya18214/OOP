package ru.nsu.fit.g18214.dubinskaya;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    try {
      FileReader frStr = new FileReader("res/Main_str.txt");
      FileReader frSub = new FileReader("res/Main_sub.txt");
      Scanner scan1 = new Scanner(frStr);
      Scanner scan2 = new Scanner(frSub);
      String sub = scan2.nextLine();
      String str = scan1.nextLine();
      int delta = 0;
      int[] ans = {};
      while (1 == 1) {
        ans = arrConcat(ans, zFunction.returnNumSubs(str, sub, delta));
        if (!scan1.hasNextLine()) break;
        delta += str.length();
        str = scan1.nextLine();
      }
      for (int v : ans)
        System.out.println(v);
    } catch(Exception e){
      System.out.println("File not found");
    }
  }

  public static int[] arrConcat(int[] array1, int[] array2){
    int[] array1and2 = new int[array1.length + array2.length];
    System.arraycopy(array1, 0, array1and2, 0, array1.length);
    System.arraycopy(array2, 0, array1and2, array1.length, array2.length);
    return array1and2;
  }
}
