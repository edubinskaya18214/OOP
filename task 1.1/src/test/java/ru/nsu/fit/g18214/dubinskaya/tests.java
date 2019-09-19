package ru.nsu.fit.g18214.dubinskaya;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.dubinskaya.HeapSort;

public class tests {
    private HeapSort gen = new HeapSort();
    @Test
    public void test1() {
        int[] test_arr = {-2, -5, -8, -255, -8, -78, 6, 24, -20};
        int[] check_arr = {-255, -78, -20, -8, -8, -5, -2, 6, 24};
        Assert.assertArrayEquals(check_arr, gen.heapSort(test_arr));
        System.out.println("test 1 is successful");
    }
    @Test
    public void test2(){
        int[] test_arr = {10,9,8,7,6,5,4,3,2,1};
        int[] check_arr = {1,2,3,4,5,6,7,8,9,10};
        Assert.assertArrayEquals(check_arr, gen.heapSort(test_arr));
        System.out.println("test 2 is successful");
    }
    @Test
    public void test3(){
        int[] test_arr = {0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] check_arr = {-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        Assert.assertArrayEquals(check_arr, gen.heapSort(test_arr));
        System.out.println("test 3 is successful");
    }
    @Test
    public void test4(){
        int[] test_arr = {100000000, -300000, -1000000000};
        int[] check_arr = {-1000000000, -300000, 100000000};
        Assert.assertArrayEquals(check_arr, gen.heapSort(test_arr));
        System.out.println("test 4 is successful");
    }
}