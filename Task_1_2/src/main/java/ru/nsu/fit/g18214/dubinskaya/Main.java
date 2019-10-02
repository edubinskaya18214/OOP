package ru.nsu.fit.g18214.dubinskaya;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("number of vertex: ");
    int n = scanner.nextInt();
    Graph g = new Graph();
    g.setNumv(n);
    System.out.print("v1, v2: \n");
    int v1 = scanner.nextInt();
    int v2 = scanner.nextInt();
    System.out.print("num edges: \n");
    int numEdges = scanner.nextInt();
    int[][] list = new int[numEdges][3];
    for (int i = 0; i < numEdges; ++i) {
      System.out.print("edge " + i + ":\n");
      list[i][0] = scanner.nextInt() - 1;
      list[i][1] = scanner.nextInt() - 1;
      list[i][2] = scanner.nextInt();
    }
    if (g.addList(list) == -1)
      return;
    System.out.println("\n\n");
    int dist = GetDist.get(g, v1 - 1, v2 - 1);
    System.out.println(dist);
  }
}
