package ru.nsu.fit.g18214.dubinskaya;

import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

public class tests {

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
    System.out.println("    Int test is successful.");
  }

  @Test
  public void charAndStringTest() {
    Queue<Character, String> q = new Queue<>();
    for (char i = 'a'; i <= 'z'; ++i) {
      q.insert(i, i + "  ! " + i + " , farewell");
    }
    Assert.assertEquals(q.size(), 'z' - 'a' + 1);
    Assert.assertEquals(q.extractMax(), "z  ! z , farewell");
    Assert.assertEquals(q.size(), 'z' - 'a');
    System.out.println("    Char test is successful.");
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
    System.out.println("    Float test is successful.");
  }

  @Test
  public void IteratorTest(){
    Queue<Integer, Integer> q = new Queue<>();
    for (int i = 0; i < 100; ++i) {
        q.insert(i, i-100);
      }
    Iterator<Integer> iter = q.iterator();
    for (int i = 0; i < 100; ++i) {
      int next = iter.next();
      Assert.assertEquals(i-100, next);
    }
    Assert.assertFalse(iter.hasNext());
    System.out.println("    Iterator test is successful.");
  }
}
