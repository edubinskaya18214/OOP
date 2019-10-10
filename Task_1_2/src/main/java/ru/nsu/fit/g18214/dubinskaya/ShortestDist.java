package ru.nsu.fit.g18214.dubinskaya;

/* This class find and save the shortest distance from
 * vertex v1 to each vertex in graph 1 using Dijkstra's algorithm
 * object FindShortestDist input graph where find shortest distant
 * and vertex where the algorithm will start finding
 */
class ShortestDist {
  private int v1;
  private int n;
  private int[] distFromV1ToVi;
  private int[]
      mark; // 0==doesn't checked, 1==find dist (not finish version), 2==checked and find dist

  /* here we initialise object */
  ShortestDist(int from, Graph g) {
    if (g == null || g.getNumv() <= 0 ) {
      n = -1;
      v1 = -1;
    } else {
      n = g.getNumv();
      v1 = from;
      distFromV1ToVi = new int[n];
      mark = new int[n];
      for (int i = 0; i < n; ++i) {
        mark[i] = 0;
        distFromV1ToVi[i] = Integer.MAX_VALUE;
      }
      distFromV1ToVi[v1] = 0;
      mark[v1] = 1;
    }
    DijkstraAlg(g);
  }

  /* FindMinEdge - find vertex with the shortest distance from v1,
   * finding vertex - never been checked before
   */
  private int FindMinEdge() {
    int min = Integer.MAX_VALUE;
    int minInd = -1;
    for (int i = 0; i < n; ++i)
      if (mark[i] == 1 && distFromV1ToVi[i] < min) {
        min = distFromV1ToVi[i];
        minInd = i;
      }
    if (min != Integer.MAX_VALUE) return minInd;
    return -1;
  }

  private void DijkstraAlg(Graph g) {
    int from;
    while ((from = FindMinEdge()) != -1) {
      mark[from] = 2;
      for (int i = 0; i < g.getNumNeighVertex(from); ++i) {
        int vTo = g.getNextVertex(from, i)[0];
        if (mark[vTo] == 2) continue;
        int distTo = g.getNextVertex(from, i)[1];
        mark[vTo] = 1;
        if (distFromV1ToVi[vTo] > distTo + distFromV1ToVi[from])
          distFromV1ToVi[vTo] = distTo + distFromV1ToVi[from];
      }
    }
  }

  // This method accept vertex and return the shortest distance from v1 to this vertex if it exists
  // else method return -1
  public int getDist(int v2) {
    if (v2 < n && mark[v2] == 2) return distFromV1ToVi[v2];
    else return -1;
  }
}
