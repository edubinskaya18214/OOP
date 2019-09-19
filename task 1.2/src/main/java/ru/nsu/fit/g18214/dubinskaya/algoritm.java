package ru.nsu.fit.g18214.dubinskaya;

public class algoritm {
    private int[][] g;
    private int v;

    public void floydWarshall(int[][] d, int n) {
        v = n;
        setGraph(d);
        for (int k = 0; k < v; ++k)
            for (int i = 0; i < v; ++i)
                for (int j = 0; j < v; ++j)
                    if ( g[i][k] != -1 && g[k][j] != -1 && (g[i][j] > g[i][k] + g[k][j] || g[i][j] == -1))
                        g[i][j] = g[i][k] + g[k][j];
    }

    private void setGraph(int[][] d) {
      g = new int[v][v];
      for (int i = 0; i < v; ++i)
        for (int j = 0; j < v; ++j)
          g[i][j] = d[i][j];
    }

    public int takeDist(int v1, int v2){
      return g[v1-1][v2-1];
    }
    public void printMatrix(int n){
      for(int i = 0; i < n; ++i) {
          for (int j = 0; j < n; ++j)
              System.out.print(g[i][j] + " ");
          System.out.println();
      }
    }
}
