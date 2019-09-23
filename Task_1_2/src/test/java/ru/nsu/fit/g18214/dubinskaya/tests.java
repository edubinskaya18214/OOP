package ru.nsu.fit.g18214.dubinskaya;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.dubinskaya.*;

public class tests {
    graph g = new graph();

    @Test
    public void test1() {
        g.setNumv(5);
        for (int i = 0; i < 4; ++i)
            g.addEdge(i, i + 1, 1);
        Assert.assertEquals(4, getDist.get(g, 0, 4));
        System.out.println("test 1 is successful");
    }

    @Test
    public void test2() {
        g.setNumv(5);
        g.addEdge(0, 2, 3);
        g.addEdge(4, 2, 3);
        int dist = getDist.get(g, 0, 4);
        Assert.assertEquals(-1, dist);
        System.out.println("test 2 is successful");
    }

    @Test
    public void test3() {
        g.setNumv(0);
        g.addEdge(0, 2, 3);
        g.addEdge(4, 2, 3);
        int dist = getDist.get(g, 0, 0);
        Assert.assertEquals(-1, dist);
        System.out.println("test 3 is successful");
    }

    @Test
    public void test4() {
        g.setNumv(0);
        g.addEdge(0, 2, 3);
        g.addEdge(4, 2, 3);
        int dist = getDist.get(g, 0, 4);
        Assert.assertEquals(-1, dist);
        System.out.println("test 4 is successful");
    }

    @Test
    public void test5() {
        g.setNumv(5);
        g.addEdge(0, 1, 3);
        g.addEdge(1, 2, 3);
        g.addEdge(2, 3, 3);
        g.addEdge(3, 4, 3);
        g.addEdge(4, 0, 3);
        int dist = getDist.get(g, 0, 4);
        Assert.assertEquals(12, dist);
        System.out.println("test 5 is successful");
    }

    @Test
    public void test6() {
        g.setNumv(5);
        g.addEdge(0, 1, 3);
        g.addEdge(1, 2, 3);
        g.addEdge(2, 0, 3);
        g.addEdge(3, 4, 3);
        g.addEdge(4, 3, 3);
        int dist = getDist.get(g, 0, 4);
        Assert.assertEquals(-1, dist);
        System.out.println("test 6 is successful");
    }

    @Test
    public void test7() {
        g.setNumv(8);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 3, 1);
        g.addEdge(2, 7, 11);
        g.addEdge(5, 1, 6);
        g.addEdge(4, 5, 5);
        g.addEdge(3,4,0);
        g.addEdge(3,6,4);
        g.addEdge(6,7,2);
        g.addEdge(7,6,15);
        g.addEdge(2,7,11);
        int dist = getDist.get(g, 1, 7);
        Assert.assertEquals(10, dist);
        System.out.println("test 6 is successful");
    }
}