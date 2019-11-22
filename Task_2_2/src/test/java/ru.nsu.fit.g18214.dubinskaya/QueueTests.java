package ru.nsu.fit.g18214.dubinskaya;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

public class QueueTests {
  @Test
  public void intTest() {
    Queue<Integer, Integer> q = new Queue<>();
    for (int i = 0; i < 10; ++i)
      for (int j = 0; j < 10; ++j) {
        q.insert(i + (int) (j * (Math.sqrt(i))), j * i - i - j);
      }
    Assert.assertEquals(100, q.size());
    int max = q.extractMax();
    Assert.assertEquals(63, max);
    int min = q.extractMin();
    Assert.assertEquals(-9, min);
    Assert.assertEquals(98, q.size());
  }
  @Test
  public void charAndStringTest() {
    Queue<Character, String> q = new Queue<>();
    for (char i = 'a'; i <= 'z'; ++i) {
      q.insert(i, i + "  ! " + i + " , la-la-la-la");
    }
    Assert.assertEquals(q.size(), 'z' - 'a' + 1);
    Assert.assertEquals(q.extractMax(), "z  ! z , la-la-la-la");
    Assert.assertEquals(q.size(), 'z' - 'a');
  }

  @Test
  public void floatAndCharTest() {
    Queue<Float, Character> q = new Queue<>();
    for (float i = 0; i < 100; ++i) {
      q.insert((float) (i / 1000 + Math.sqrt(i)), (char) (10 + i + 'a'));
    }
    char max = q.extractMax();
    char min = q.extractMin();
    Assert.assertEquals('Ï' - 1, max);
    Assert.assertEquals('k', min);
    Assert.assertEquals(q.size(), 98);
  }
  @Test
  public void IteratorTest() {
    Queue<Integer, Integer> q = new Queue<>();
    for (int i = 0; i < 100; ++i) {
      q.insert(i, i - 100);
    }
    Iterator<Integer> iter = q.iterator();
    for (int i = 0; i < 100; ++i) {
      int next = iter.next();
      Assert.assertEquals(i-100, next);
    }
    Assert.assertFalse(iter.hasNext());

    for (int i = 0; i < 100; ++i){
      q.extractMax();
    }
    for(int i = 0; i < 5; ++i){
      q.insert(i, 10 - i);
    }
    iter = q.iterator();
    for (int i = 0; i < 5; ++i) {
      int next = iter.next();
      Assert.assertEquals(10-i, next);
    }
    Assert.assertFalse(iter.hasNext());
  }
  @Test
  public void OrderTest() {
    Queue<Integer, Integer> q = new Queue<>();
    for (int i = 0; i < 10; ++i) {
      q.insert(15, i);
    }
    for (int i = 0; i < 10; ++i) {
      int num = q.extractMax();
      Assert.assertEquals(num, i);
    }
    q.insert(15, 5);
    q.insert(15, 4);
    q.insert(16, 7);
    q.insert(16, 6);
    q.insert(17, 8);
    for (int i = 4; i <= 8; ++i ){
      int num = q.extractMin();
      Assert.assertEquals(num, i);
    }
  }
  @Test(expected = NoSuchElementException.class)
  public void EmptyTest() {
    Queue<Integer, Integer> q = new Queue<>();
    q.extractMax();
    q.extractMin();
  }
  @Test
  public void nullptrTest(){
    Queue<Integer, Integer> q = new Queue<>();
    q.insert(20, null);
    Assert.assertFalse(q.empty());
    Integer obj = q.extractMax();
    Assert.assertTrue(q.empty());
    try{
      q.insert(null, 20);
      Assert.fail();
    } catch (NullPointerException ignored){}
    Assert.assertTrue(q.empty());
  }
}
