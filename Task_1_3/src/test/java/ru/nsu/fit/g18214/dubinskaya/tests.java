package ru.nsu.fit.g18214.dubinskaya;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.dubinskaya.zFunction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class tests {

  @Test
  public void test1() {
    String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
    String sub = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
    int[] checkArr = {0};
    Assert.assertArrayEquals(checkArr, zFunction.returnNumSubs(s, sub));
    System.out.println("test 1 is successful");
  }

  @Test
  public void test2() {
    //0123456789012345678901234567890123456789012345678901234567
    String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
    String sub = "яблоки";
    int[] checkArr = {18};
    Assert.assertArrayEquals(checkArr, zFunction.returnNumSubs(s, sub));
    System.out.println("test 2 is successful");
  }

  @Test
  public void test3() {
    //0123456789012345678901234567890123456789012345678901234567
    String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
    String sub = "Карл у Клары украл кораллы, а Клара у Карла украла Кларнет, жалко мне клару, как она будет без кораллов, хоспаде, что я пишу";
    int[] checkArr = {};
    Assert.assertArrayEquals(checkArr, zFunction.returnNumSubs(s, sub));
    System.out.println("test 3 is successful");
  }

  @Test
  public void test4() {
    //0123456789012345678901234567890123456789012345678901234567
    String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
    String sub = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют. Хотя, наверное, неплохо иметь поклонников";
    int[] checkArr = {};
    Assert.assertArrayEquals(checkArr, zFunction.returnNumSubs(s, sub));
    System.out.println("test 4 is successful");
  }

  @Test
  public void test5() {
    //0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
    String s = "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
        "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
        "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
        "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
        "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
        "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа";
    String sub = "а";
    int[] checkArr = new int[600];
    for (int i = 0; i < 600; ++i)
      checkArr[i] = i;
    Assert.assertArrayEquals(checkArr, zFunction.returnNumSubs(s, sub));
    System.out.println("test 5 is successful");
  }

  @Test
  public void test6() {
    String s = "";
    String sub = "Карл у Клары украл кораллы";
    int[] checkArr = {};
    Assert.assertArrayEquals(checkArr, zFunction.returnNumSubs(s, sub));
    System.out.println("test 6 is successful");
  }

  @Test
  public void test7() throws FileNotFoundException {
    FileReader frStr = new FileReader("res/7_str.txt");
    FileReader frSub = new FileReader("res/7_sub.txt");
    Scanner scan1 = new Scanner(frStr);
    Scanner scan2 = new Scanner(frSub);
    String sub = scan2.nextLine();
    String str = scan1.nextLine();
    int delta = 0;
    int[] ans ={};
    while (1==1) {
      ans = Main.arrConcat(ans, zFunction.returnNumSubs(str,sub,delta));
      if (!scan1.hasNextLine()) break;
      delta += str.length();
      str = scan1.nextLine();
    }
    int[] checkArr = {0,1,2,3};
    Assert.assertArrayEquals(checkArr, ans);
    System.out.println("test 7 is successful");
  }
}