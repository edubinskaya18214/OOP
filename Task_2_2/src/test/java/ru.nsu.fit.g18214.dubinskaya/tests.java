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
    System.out.println("    Iterator test is successful.");
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
    System.out.println("    Order test is successful.");
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void EmptyTest() {
    Queue<Integer, Integer> q = new Queue<>();
    q.extractMax();
    q.extractMin();
    System.out.println("    Empty test is successful.");
  }
  @Test
  public void nullptrTest(){
    Queue<Integer, Integer> q = new Queue<>();
    q.insert(20, null);
    q.insert(null, 20);
    q.insert(null,null);
    Assert.assertTrue(q.empty());
    System.out.println("    \"Null pointer\" test is successful.");
  }
}
