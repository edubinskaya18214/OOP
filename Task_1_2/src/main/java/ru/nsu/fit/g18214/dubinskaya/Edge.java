package ru.nsu.fit.g18214.dubinskaya;
// This class can create object Edge and return data about it
public class Edge {
  private int v1;
  private int v2;
  private int dist;

  public Edge(int nv1, int nv2, int nd) {
    v1 = nv1;
    v2 = nv2;
    dist = nd;
  }

  public int getVFrom() {
    return v1;
  }

  public int getVTo() {
    return v2;
  }

  public int getDist() {
    return dist;
  }
}
