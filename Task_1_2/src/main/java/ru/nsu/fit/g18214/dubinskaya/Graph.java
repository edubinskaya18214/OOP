package ru.nsu.fit.g18214.dubinskaya;

/* class Graph can create and change graph, this class input number of vertex */
public class Graph {
  private int numv;
  private NeighboringVertices[] vertices;
  private int[] pos;

  public Graph(int n, Edge[] arr, int reverse) {
    numv = n;
    vertices = new NeighboringVertices[n];
    pos = new int[n];
    for (int i = 0; i < numv; ++i) {
      pos[i] = 0;
      vertices[i] = new NeighboringVertices();
    }
    if (arr != null)
      for (int i = 0; i < arr.length; ++i) {
        if (arr[i] == null) {
          System.out.println("edge " + i + " wasn't initialised\n");
          continue;
        }
        int v1 = arr[i].getVFrom();
        int v2 = arr[i].getVTo();
        int dist = arr[i].getDist();
        addEdge(v1, v2, dist);
        if (reverse == 1) addEdge(v2, v1, dist);
      }
  }
  // return number of vertex in graph
  public int getNumv() {
    return numv;
  }
  // input vertex v ,return number of neighborhood vertex for v
  public int getNumNeighVertex(int v) {
    return pos[v];
  }
  // input vertex v and number of her neighbor, return arr, arr[0] == num v1, arr[1] == dist between
  // v and v1
  // if this neighbor doesn't exist return -1 in arr[0]
  int[] getNextVertex(int v, int p) {
    int[] r = new int[2];
    if (pos[v] > p) {
      r[0] = vertices[v].edges.get(p);
      r[1] = vertices[v].dist.get(p);
    } else r[0] = -1;
    return r;
  }
  // input v1 and v2 - vertexes and distant between them
  private void addEdge(int v1, int v2, int d) {
    if (v1 >= numv || v2 >= numv) {
      System.out.println("error: graph size exceeded");
    } else {
      vertices[v1].edges.add(v2);
      vertices[v1].dist.add(d);
      ++pos[v1];
    }
  }
}
