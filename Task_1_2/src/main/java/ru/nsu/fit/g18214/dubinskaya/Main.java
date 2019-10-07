package ru.nsu.fit.g18214.dubinskaya;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("number of vertex: ");
    int n = scanner.nextInt();
    Graph g = new Graph(n);
    System.out.print("v1, v2: \n");
    int v1 = scanner.nextInt();
    int v2 = scanner.nextInt();
    System.out.print("num edges: \n");
    int numEdges = scanner.nextInt();
    int[][] list = new int[numEdges][3];
    for (int i = 0; i < numEdges; ++i) {
      System.out.print("edge " + i + ":\n");
      g.addEdge( scanner.nextInt() - 1, scanner.nextInt() - 1,scanner.nextInt());
    }
    System.out.println("\n\n");
    FindShortestDist d = new FindShortestDist(v1-1, g);
    int dist = d.getDist(v2-1);
    System.out.println(dist);
  }
}
