package ru.nsu.fit.g18214.dubinskaya;

import java.util.ArrayList;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class tests {

  @Test
  public void testInt() {
    Stack<Integer> s;
    s = new Stack<Integer>();
    ArrayList<Integer> a = new ArrayList<Integer>();
    int n = (int) (Math.random() * 1000);
    for (int i = 0; i < n; ++i){
      int count = (int) (Math.random() * 100);
      if (count < Math.random() * 300){
        int num = (int) (Math.random() * 1000000);
        s.push(num);
        a.add(num);
      }
      else{
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
  public void floatTest(){
    Stack<Float> s;
    s = new Stack<Float>();
    ArrayList<Float> a = new ArrayList<Float>();
    int n = (int) (Math.random() * 1000);
    for (int i = 0; i < n; ++i){
      int count = (int) (Math.random() * 100);
      if (count < Math.random() * 300){
        float num = (float)Math.random();
        s.push(num);
        a.add(num);
      }
      else{
        if (a.size() != 0) {
          int size = a.size() - 1;
          float a1 = a.remove(size);
          float a2 = s.pop();
          Assert.assertEquals(a1, a2, 0.00001);
        }
      }
    }
    Assert.assertEquals(a.size(), s.count());
    System.out.println("float test is successful");
  }
  @Test
  public void stringTest(){
    Random rnd = new Random(System.currentTimeMillis());
    int min = 1;
    int max = 4;
    Stack<String> s;
    s = new Stack<String>();
    ArrayList<String> a = new ArrayList<String>();
    int n = (int) (Math.random() * 1000);
    for (int i = 0; i < n; ++i) {
      int count = (int) (Math.random() * 100);
      if (count < Math.random() * 300) {
        int number = min + rnd.nextInt(max - min + 1);
        switch(number) {
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
      } else {
        int size = a.size() - 1;
        if (size != 0) {
          String a1 = a.remove(size);
          String a2 = s.pop();
          boolean cmp = a1.equals(a2);
          Assert.assertTrue(cmp);
        }
      }
    }
    Assert.assertEquals(a.size(), s.count());
    System.out.println("string test is successful");
  }

  @Test
  public void tryToKillTest(){
    Stack<Float> stack = new Stack<Float>();
    stack.push(null);
    stack.pop();
    Assert.assertEquals(0,stack.count());
    for (int i = 0; i < 10; ++i)
      stack.pop();Assert.assertEquals(0,stack.count());
    Assert.assertEquals(0,stack.count());


    Stack<Stack<Float>> newStack = new Stack<Stack<Float>>();
    newStack.push(stack);
    Assert.assertEquals(1,newStack.count());
    stack = newStack.pop();
    Assert.assertEquals(0,stack.count());
  }
}