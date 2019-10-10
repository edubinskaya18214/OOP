package ru.nsu.fit.g18214.dubinskaya;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("number of vertex: ");
    int n = scanner.nextInt();
    System.out.print("v1, v2: \n");
    int v1 = scanner.nextInt();
    int v2 = scanner.nextInt();
    System.out.print("num edges: \n");
    int numEdges = scanner.nextInt();
    Edge[] arr = new Edge[numEdges];
    for (int i = 0; i < numEdges; ++i) {
      System.out.print("edge " + (i+1) + ":\n");
      arr[i] = new Edge(scanner.nextInt() - 1, scanner.nextInt() - 1, scanner.nextInt());
    }
    Graph g = new Graph(n,arr, 0);
    System.out.println("\n\n");
    ShortestDist d = new ShortestDist(v1 - 1, g);
    int dist = d.getDist(v2 - 1);
    System.out.println(dist);
  }
}
