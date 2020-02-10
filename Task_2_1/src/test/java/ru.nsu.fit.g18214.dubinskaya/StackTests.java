package ru.nsu.fit.g18214.dubinskaya;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

public class StackTests {

  @Test
  public void testInt() {
    Stack<Integer> s;
    s = new Stack<Integer>();
    ArrayList<Integer> a = new ArrayList<Integer>();
    for (int i = 1; i < Integer.MAX_VALUE; ++i) {
      if (i / 3 == 0) {
        s.add(i);
        a.add(i);
      } else {
        if (a.size() != 0) {
          int a1 = a.remove(a.size() - 1);
          int a2 = s.pop();
          Assert.assertEquals(a1, a2);
        }
      }
    }
    Assert.assertEquals(a.size(), s.count());
  }

  @Test
  public void floatTest() {
    Stack<Float> s;
    s = new Stack<Float>();
    ArrayList<Float> a = new ArrayList<Float>();

    for (int i = 0; i < Integer.MAX_VALUE; ++i) {
      if (i / 5 == 0) {
        float num = (float) Math.random();
        s.add(num);
        a.add(num);
      } else {
        if (a.size() != 0) {
          int size = a.size() - 1;
          float a1 = a.remove(size);
          float a2 = s.pop();
          Assert.assertEquals(a1, a2, 0.0001);
        }
      }
    }
    Assert.assertEquals(a.size(), s.count());
  }

  @Test
  public void stringTest() {
    Stack<String> s;
    s = new Stack<String>();
    ArrayList<String> a = new ArrayList<String>();
    for (int i = 1; i < 1001; ++i) {
      int number = i % 5;
      if (i % 5 != 0)
        switch (number) {
          case 1:
            s.add("aaa");
            a.add("aaa");
            break;
          case 2:
            s.add("lalalala");
            a.add("lalalala");
            break;
          case 3:
            s.add("Dog loves meat. Dog love me. Am I meat?");
            a.add("Dog loves meat. Dog love me. Am I meat?");
            break;
          case 4:
            s.add("O_O");
            a.add("O_O");
            break;
        }
      else {
        Assert.assertEquals(s.pop(), a.remove(a.size() - 1));
      }
    }
    Assert.assertEquals(a.size(), s.count());
  }

  @Test
  public void throwNullptrException() {
    Stack<String> s = new Stack<>();
    s.add(null);
    String testStr = s.pop();
    Assert.assertNull(testStr);
    Assert.assertTrue(s.empty());
  }

  @Test
  public void throwNoSuchElementException() {
    Stack<String> s = new Stack<String>();
    s.add("Laallala");
    s.add("uuuuuuuu");
    s.pop();
    s.pop();
    try {
      s.pop();
      Assert.fail("Expected NoSuchElementException");
    } catch (IndexOutOfBoundsException ignored) {
    }
  }

  @Test
  public void IteratorTest() {
    for (int j = 0; j < 100; ++j) {
      Stack<Integer> s = new Stack<Integer>();
      ArrayList<Integer> l = new ArrayList<Integer>();
      for (int i = 0; i < 110; ++i) {
        int number = j * i;
        s.add(number);
        l.add(number);
      }
      Iterator<Integer> iter = s.iterator();
      Iterator<Integer> iter2 = l.iterator();
      while (iter.hasNext()) {
        Assert.assertEquals(iter.next(), iter2.next());
      }
    }
  }

  @Test
  public void usingIteratorAfterDeleteFirstElem() {
    Stack<String> s = new Stack<String>();
    s.add("Laallala");
    s.add("uuuuuuuu");
    s.pop();
    s.pop();
    Iterator<String> iter = s.iterator();
    Assert.assertFalse(iter.hasNext());
    s.add(
        "Люцифер уже cмыл кондиционер\n"
            + "В зеркало посмотрел, видит - располнел\n"
            + "Полотенце снял c крючка на стене\n"
            + "Вытер голову и тело, халат надел\n"
            + "Сел за cтол, налил для аппетита cто\n"
            + "Из большой бутылки с перевёрнутым крестом");
    iter = s.iterator();
    Assert.assertEquals("Люцифер уже cмыл кондиционер\n"
        + "В зеркало посмотрел, видит - располнел\n"
        + "Полотенце снял c крючка на стене\n"
        + "Вытер голову и тело, халат надел\n"
        + "Сел за cтол, налил для аппетита cто\n"
        + "Из большой бутылки с перевёрнутым крестом" , iter.next());
    Assert.assertFalse(iter.hasNext());
  }
}
