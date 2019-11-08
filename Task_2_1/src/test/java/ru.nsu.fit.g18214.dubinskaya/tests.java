package ru.nsu.fit.g18214.dubinskaya;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class tests {

  @Test
  public void testInt() {
    Stack<Integer> s;
    s = new Stack<Integer>();
    ArrayList<Integer> a = new ArrayList<Integer>();
    for (int i = 1; i < Integer.MAX_VALUE; ++i) {
      if (i / 3 == 0) {
        s.push(i);
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
    System.out.println("int test is successful");
  }

  @Test
  public void floatTest() {
    Stack<Float> s;
    s = new Stack<Float>();
    ArrayList<Float> a = new ArrayList<Float>();

    for (int i = 0; i < Integer.MAX_VALUE; ++i) {
      if (i / 5 == 0) {
        float num = (float) Math.random();
        s.push(num);
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
    System.out.println("float test is successful");
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
            s.push("aaa");
            a.add("aaa");
            break;
          case 2:
            s.push("lalalala");
            a.add("lalalala");
            break;
          case 3:
            s.push("Dog love meat. Dog love me. Am I meat?");
            a.add("Dog love meat. Dog love me. Am I meat?");
            break;
          case 4:
            s.push("O_O");
            a.add("O_O");
            break;
        }
      else {
        if (!s.pop().equals(a.remove(a.size() - 1))) Assert.fail();
      }
    }
    Assert.assertEquals(a.size(), s.count());
    System.out.println("string test is successful");
  }

  @Test
  public void tryToKillTest() {
    Stack<Float> stack = new Stack<Float>();
    stack.push(null);
    stack.pop();
    Assert.assertEquals(0, stack.count());
    for (int i = 0; i < 10; ++i) stack.pop();
    Assert.assertEquals(0, stack.count());
    Assert.assertEquals(0, stack.count());
    Stack<Stack<Float>> newStack = new Stack<Stack<Float>>();
    newStack.push(stack);
    Assert.assertEquals(1, newStack.count());
    stack = newStack.pop();
    Assert.assertEquals(0, stack.count());
  }

  @Test
  public void IteratorTest() {
    for (int j = 0; j < 100; ++j) {
      Stack<Integer> s = new Stack<Integer>();
      ArrayList<Integer> l = new ArrayList<Integer>();
      for (int i = 0; i < 110; ++i) {
        int number = j*i;
        s.push(number);
        l.add(number);
      }
      Iterator<Integer> iter = s.iterator();
      Iterator<Integer> iter2 = l.iterator();
      while (iter.hasNext()) {
        Assert.assertEquals(iter.next(), iter2.next());
      }
    }
    System.out.print("Iterator test successful\n");
  }
}
