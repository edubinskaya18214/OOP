package ru.nsu.fit.g18214.dubinskaya;

public class GetDist {
  public static int get(Graph g, int v1, int v2) {
    if (g == null || g.getNumv() <= v1 || g.getNumv() <= v2)
      return -1;
    char[] checked = new char[g.getNumv()];
    for (int i = 0; i < g.getNumv(); ++i)
      checked[i] = 0;
    int r = rec(g, v1, v2, 0, checked);
    if (r == 1000000000) return -1;
    return r;
  }

  private static int rec(Graph g, int v1, int v2, int dist, char[] checked) {
    if (v1 == v2) return 0;
    checked[v1] = 1;
    int delta = 1000000000;
    int[] next = new int[2];
    for (int i = 0; ; ++i) {
      next = g.getNextVertex(v1, i);
      if (next[0] == -1)
        break;
      if (checked[next[0]] == 1)
        continue;
      int newDelta = next[1] + rec(g, next[0], v2, dist, checked);
      if (newDelta < delta)
        delta = newDelta;
    }
    return delta + dist;
  }
}