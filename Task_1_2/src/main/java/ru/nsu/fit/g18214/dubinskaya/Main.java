package ru.nsu.fit.g18214.dubinskaya;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    Graph g = new Graph();
    g.setNumv(n);
    int v1 = scanner.nextInt();
    int v2 = scanner.nextInt();
    int numEdges = scanner.nextInt();
    for (int i = 0; i < numEdges; ++i) {
      g.addEdge(scanner.nextInt() - 1, scanner.nextInt() - 1, scanner.nextInt());
    }
    int dist = GetDist.get(g, v1 - 1, v2 - 1);
    System.out.println(dist);
  }
}
