package ru.nsu.fit.g18214.dubinskaya;

import org.junit.Assert;
import org.junit.Test;

public class tests {
  @Test
  public void test1() {
    Graph g = new Graph(5);
    for (int i = 0; i < 4; ++i) g.addEdge(i, i + 1, 1);
    FindShortestDist d = new FindShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(4, dist);
    System.out.println("test 1 is successful");
  }

  @Test
  public void test2() {
    Graph g = new Graph(5);
    g.addEdge(0, 2, 3);
    g.addEdge(4, 2, 3);
    FindShortestDist d = new FindShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(-1, dist);
    System.out.println("test 2 is successful");
  }

  @Test
  public void test3() {
    Graph g = new Graph(0);
    g.addEdge(0, 2, 3);
    g.addEdge(4, 2, 3);
    FindShortestDist d = new FindShortestDist(0, g);
    int dist = d.getDist(0);
    Assert.assertEquals(-1, dist);
    System.out.println("test 3 is successful");
  }

  @Test
  public void test4() {
    Graph g = new Graph(0);
    g.addEdge(0, 2, 3);
    g.addEdge(4, 2, 3);
    FindShortestDist d = new FindShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(-1, dist);
    System.out.println("test 4 is successful");
  }

  @Test
  public void test5() {
    Graph g = new Graph(5);
    g.addEdge(0, 1, 3);
    g.addEdge(1, 2, 3);
    g.addEdge(2, 3, 3);
    g.addEdge(3, 4, 3);
    g.addEdge(4, 0, 3);
    FindShortestDist d = new FindShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(12, dist);
    System.out.println("test 5 is successful");
  }

  @Test
  public void test6() {
    Graph g = new Graph(5);
    g.addEdge(0, 1, 3);
    g.addEdge(1, 2, 3);
    g.addEdge(2, 0, 3);
    g.addEdge(3, 4, 3);
    g.addEdge(4, 3, 3);
    FindShortestDist d = new FindShortestDist(0,g);
    int dist = d.getDist( 4);
    Assert.assertEquals(-1, dist);
    System.out.println("test 6 is successful");
  }

  @Test
  public void test7() {
    Graph g = new Graph(8);
    g.addEdge(1, 2, 3);
    g.addEdge(1, 3, 5);
    g.addEdge(2, 3, 1);
    g.addEdge(2, 7, 11);
    g.addEdge(5, 1, 6);
    g.addEdge(4, 5, 5);
    g.addEdge(3, 4, 0);
    g.addEdge(3, 6, 4);
    g.addEdge(6, 7, 2);
    g.addEdge(7, 6, 15);
    g.addEdge(2, 7, 11);
    FindShortestDist d = new FindShortestDist(1,g);
    int dist = d.getDist(7);
    Assert.assertEquals(10, dist);
    System.out.println("test 7 is successful");
  }

  @Test
  public void test8() {
    Graph g = new Graph(5);
    for (int i = 0; i < 4; ++i) g.addEdge(i, i + 1, 3);
    FindShortestDist d = new FindShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(12, dist);
    System.out.println("test 8 is successful");
  }

  @Test
  public void test9() {
    Graph t9 = null;
    FindShortestDist d = new FindShortestDist(0, t9);
    int dist = d.getDist(4);
    Assert.assertEquals(-1, dist);
    System.out.println("test 9 is successful");
  }
}
