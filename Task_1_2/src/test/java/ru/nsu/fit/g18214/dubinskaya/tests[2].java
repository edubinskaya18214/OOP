package ru.nsu.fit.g18214.dubinskaya;

import org.junit.Assert;
import org.junit.Test;

public class tests {
  @Test
  public void test1() {
    Edge[] arr  = new Edge[4];
    for (int i = 0; i < 4; ++i){
      arr[i] = new Edge(i, i+1,1);
    }
    Graph g = new Graph(5, arr, 0);
    ShortestDist d = new ShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(4, dist);
    System.out.println("test 1 is successful");
  }

  @Test
  public void test2() {
    Edge[] arr = new Edge[2];
    arr[0] = new Edge(0, 2, 3);
    arr[1] = new Edge(4, 2, 3);
    Graph g = new Graph(5, arr, 0);
    ShortestDist d = new ShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(-1, dist);
    System.out.println("test 2 is successful");
  }

  @Test
  public void test3() {
    Edge[] arr = new Edge[5];
    arr[0] = new Edge(0, 1, 3);
    arr[1] = new Edge(1, 2, 3);
    arr[2] = new Edge(2, 3, 3);
    arr[3] = new Edge(3, 4, 3);
    arr[4] = new Edge(4, 0, 3);
    Graph g = new Graph(5,arr,0);
    ShortestDist d = new ShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(12, dist);
    System.out.println("test 3 is successful");
  }

  @Test
  public void test4() {
    Edge[] arr = new Edge[5];
    arr[0] = new Edge(0, 1, 3);
    arr[1] = new Edge(1, 2, 3);
    arr[2] = new Edge(2, 0, 3);
    arr[3] = new Edge(3, 4, 3);
    arr[4] = new Edge(4, 3, 3);
    Graph g = new Graph(5, arr,0);
    ShortestDist d = new ShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(-1, dist);
    System.out.println("test 4 is successful");
  }

  @Test
  public void test5() {
    Edge[] arr = new Edge[11];
    arr[0] = new Edge(1, 2, 3);
    arr[1] = new Edge(1, 3, 5);
    arr[2] = new Edge(2, 3, 1);
    arr[3] = new Edge(2, 7, 11);
    arr[4] = new Edge(5, 1, 6);
    arr[5] = new Edge(4, 5, 5);
    arr[6] = new Edge(3, 4, 0);
    arr[7] = new Edge(3, 6, 4);
    arr[8] = new Edge(6, 7, 2);
    arr[9] = new Edge(7, 6, 15);
    arr[10] = new Edge(2, 7, 11);
    Graph g = new Graph(8, arr, 0);
    ShortestDist d = new ShortestDist(1, g);
    int dist = d.getDist(7);
    Assert.assertEquals(10, dist);
    System.out.println("test 5 is successful");
  }

  @Test
  public void test6() {
    Edge[] arr = new Edge[5];
    for (int i = 0; i < 4; ++i) arr[i] = new Edge(i, i + 1, 3);
    Graph g = new Graph(5, arr, 0);
    ShortestDist d = new ShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(12, dist);
    System.out.println("test 6 is successful");
  }

  @Test
  public void test7() {
    Graph t9 = null;
    ShortestDist d = new ShortestDist(0, t9);
    int dist = d.getDist(4);
    Assert.assertEquals(-1, dist);
    System.out.println("test 7 is successful");
  }

  @Test
  public void test8(){
    Graph g = new Graph(5, null, 0);
    ShortestDist d = new ShortestDist(0, g);
    int dist = d.getDist(4);
    Assert.assertEquals(-1, dist);
    System.out.println("test 8 is successful");
  }
}
