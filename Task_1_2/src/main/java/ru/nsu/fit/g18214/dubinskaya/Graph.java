package ru.nsu.fit.g18214.dubinskaya;

import java.util.Arrays;

public class Graph {
  private int numv;
  private int[][] edges;
  private int[][] dist;
  private int[] size;
  private int[] pos;

  public void setNumv(int n) {
    numv = n;
    int x = (int) Math.sqrt(n);
    edges = new int[n][x];
    size = new int[numv];
    dist = new int[n][x];
    pos = new int[n];
    for (int i = 0; i < numv; ++i) {
      size[i] = x;
      pos[i] = 0;
    }
  }
  public int addList(int[][] list){
    if (list == null)
      return -1;
    for(int i = 0; i < list.length; ++i) {
      if (list[i].length != 3){
        System.out.println("Error: incorrect list!\n");
        return -1;
      }
      addEdge(list[i][0], list[i][1], list[i][2]);
    }
    return 0;
  }

  public int getNumv() {
    return numv;
  }

  public int[] getNextVertex(int v, int p) {
    int[] r = new int[2];
    if (pos[v] > p) {
      r[0] = edges[v][p];
      r[1] = dist[v][p];
    } else r[0] = -1;
    return r;
  }

  private void extendSize(int v) {
    int[] newArr = new int[size[v] * 2];
    newArr = Arrays.copyOf(edges[v], size[v]);
    edges[v] = Arrays.copyOf(newArr, size[v] * 2);
    newArr = Arrays.copyOf(dist[v], size[v]);
    dist[v] = Arrays.copyOf(newArr, size[v] * 2);
    size[v] *= 2;
  }

  public void addEdge(int v1, int v2, int d) {
    if (v1 >= numv || v2 >= numv) {
      System.out.println("error: graph size exceeded");
    } else {
      if (size[v1] - 1 == pos[v1]) extendSize(v1);
      edges[v1][pos[v1]] = v2;
      dist[v1][pos[v1]++] = d;
    }
  }
}
