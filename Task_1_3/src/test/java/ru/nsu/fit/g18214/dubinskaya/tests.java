package ru.nsu.fit.g18214.dubinskaya;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileReader;
import java.util.Scanner;

public class tests {

  @Test
  public void test1() {
    String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
    String sub = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
    SubstringsFInder f = new SubstringsFInder(s,sub);
    int[] checkArr = {0};
    Assert.assertArrayEquals(checkArr, f.returnSubsID());
    System.out.println("test 1 is successful");
  }

  @Test
  public void test2() {
    //0123456789012345678901234567890123456789012345678901234567
    String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
    String sub = "яблоки";
    SubstringsFInder f = new SubstringsFInder(s,sub);
    int[] checkArr = {18};
    Assert.assertArrayEquals(checkArr, f.returnSubsID());
    System.out.println("test 2 is successful");
  }

  @Test
  public void test3() {
    //0123456789012345678901234567890123456789012345678901234567
    String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
    String sub = "Карл у Клары украл кораллы, а Клара у Карла украла Кларнет, жалко мне клару, как она будет без кораллов, хоспаде, что я пишу";
    int[] checkArr = {};
    SubstringsFInder f = new SubstringsFInder(s,sub);
    Assert.assertArrayEquals(checkArr, f.returnSubsID());
    System.out.println("test 3 is successful");
  }

  @Test
  public void test4() {
    //0123456789012345678901234567890123456789012345678901234567
    String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
    String sub = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют. Хотя, наверное, неплохо иметь поклонников";
    int[] checkArr = {};
    SubstringsFInder f = new SubstringsFInder(s,sub);
    Assert.assertArrayEquals(checkArr, f.returnSubsID());
    System.out.println("test 4 is successful");
  }

  @Test
  public void test5() {
    String s = "";
    String sub = "Карл у Клары украл кораллы";
    int[] checkArr = {};
    SubstringsFInder f = new SubstringsFInder(s,sub);
    Assert.assertArrayEquals(checkArr, f.returnSubsID());
    System.out.println("test 5 is successful");
  }
  @Test
  public void test6() throws IOException {
    FileReader r1 = new FileReader("res/Main_str.txt");
    String i =
        "ROMEO \n"
            + "And stay, good nurse, behind the abbey wall.\n"
            + "Within this hour my man shall be with thee\n"
            + "And bring thee cords made like a tackled stair,";
    FileReader r2 = new FileReader("res/Main_str.txt");
    String AllText = "";
    Scanner s = new Scanner(r2);
    while(s.hasNextLine())
      AllText +=  s.nextLine() + "\n";
    SubstringsFInder f1 = new SubstringsFInder(AllText,i);
    SubstringsFInder f = new SubstringsFInder(r1,i);
    int[] ans = f.returnSubsID();
    Assert.assertArrayEquals(f1.returnSubsID(), f.returnSubsID());
    System.out.println("test 6 is successful");
  }

  @Test
  public void test7() throws IOException {
    FileReader r1 = null;
    String i = null;
    SubstringsFInder f = new SubstringsFInder(r1, i);
    int[] ans = new int[0];
    Assert.assertArrayEquals(ans, f.returnSubsID());
    System.out.println("test 7 is successful");
  }

}