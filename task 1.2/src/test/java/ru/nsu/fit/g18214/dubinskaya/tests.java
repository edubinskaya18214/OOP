package ru.nsu.fit.g18214.dubinskaya;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.dubinskaya.algoritm;

public class tests {
    private algoritm alg = new algoritm();

    @Test
    public void test1() {
        int[][] matrix = {
                {0, 1, -1, -1, -1},         // 1 <-> 2 <-> 3 <-> 4 <-> 5
                {1, 0, 1, -1, -1},
                {-1, 1, 0, 1, -1},
                {-1, -1, 1, 0, 1},
                {-1, -1, -1, 1, 0},
        };
        alg.floydWarshall(matrix, 5);
        //alg.printMatrix(5);
        Assert.assertEquals(4, alg.takeDist(1, 5));
        System.out.println("test 1 is successful");
    }
    @Test
    public void test2() {
        int[][] matrix = {
                {0, 1, -1, -1, -1},    //    1 -> 2 -> 3
                {-1, 0, 1, -1, -1},    //    4 -> 5
                {-1, -1, 0, -1, -1},   //
                {-1, -1, -1, 0, 1},    //
                {-1, -1, -1, -1, 0},   //
        };
        alg.floydWarshall(matrix, 5);
        //alg.printMatrix(5);
        Assert.assertEquals(-1, alg.takeDist(1, 5));
        System.out.println("test 2 is successful");
    }
    @Test
    public void test3() {
        int[][] matrix = {
                {0, 1, -1, -1, -1},    //    1 -> 2 -> 3 -> 4 -> 5
                {-1, 0, 1, -1, -1},    //
                {-1, -1, 0, 1, -1},    //
                {-1, -1, -1, 0, 1},    //
                {-1, -1, -1, -1, 0},   //
        };
        alg.floydWarshall(matrix, 5);
        //alg.printMatrix(5);
        Assert.assertEquals(4, alg.takeDist(1, 5));
        System.out.println("test 3 is successful");
    }
    @Test
    public void test4() {
        int[][] matrix = {
                {0, 1, -1, -1, -1},    //    1 -> 2 -> 3 -> 4 -> 5
                {-1, 0, 1, -1, -1},    //
                {-1, -1, 0, 1, -1},    //
                {-1, -1, -1, 0, 1},    //
                {-1, -1, -1, -1, 0},   //
        };
        alg.floydWarshall(matrix, 5);
        //alg.printMatrix(5);
        Assert.assertEquals(-1, alg.takeDist(5, 1));
        System.out.println("test 4 is successful");
    }

    @Test
    public void test5() {
        int[][] matrix = {
                {0, 1, -1, -1, -1},    //            <- 1 <-
                {-1, 0, 1, -1, -1},    //          2        5
                {-1, -1, 0, 1, -1},    //          \>      />
                {-1, -1, -1, 0, 1},    //           3 ->   4
                {1, -1, -1, -1, 0},   //
        };
        alg.floydWarshall(matrix, 5);
        alg.printMatrix(5);
        Assert.assertEquals(4, alg.takeDist(5, 4));
        System.out.println("test 5 is successful");
    }
    @Test
    public void test6() {
        int[][] matrix = {
                {0, 1, -1, -1, -1},    //            <- 1 <-
                {-1, 0, 1, -1, -1},    //          2        5
                {-1, -1, 0, 1, -1},    //          \>      />
                {-1, -1, -1, 0, 1},    //           3 ->   4
                {1, -1, -1, -1, 0},   //
        };
        alg.floydWarshall(matrix, 5);
        alg.printMatrix(5);
        Assert.assertEquals(4, alg.takeDist(3, 2));
        System.out.println("test 6 is successful");
    }
}