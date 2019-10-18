package ru.nsu.fit.g18214.dubinskaya;

import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    FileReader f1 = new FileReader("res/Main_str.txt");
    String i = "MERCUTIO";
    SubstringsFInder f = new SubstringsFInder(f1,i);
    int[] ans = f.returnSubsID();
    for( int v = 0; v < ans.length; ++v)
      System.out.print(ans[v] + " ");
  }
}
