package ru.nsu.fit.g18214.dubinskaya;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class CalculatorTest {
  @Test
  public void CountingTest(){
    Calculator c = new Calculator();
    c.setSavingAnswers(true);
    float ans = 7 + 8;
    Assert.assertEquals(ans, c.calculate("+ 7 8"), 0.0001);
    ans = (float)Math.sin(1 + 1 - 2);
    Assert.assertEquals(ans, c.calculate("sin + - 1 2 1"), 0.0001);
    ans = (float)Math.sqrt(1500 * 15) + (7 - 900) + ((float)2005 / 400) + 7;
    Assert.assertEquals(ans, c.calculate("+ sqrt * 1500 15 + - 7 900 + / 2005 400 7"), 0.0001);
    ans = (float)Math.sin(Math.cos(5));
    Assert.assertEquals(ans, c.calculate("sin cos 5"), 0.0001);
    ans = 5;
    Assert.assertEquals(ans, c.calculate("        5       "), 0.0001);
    ans = (float)-Math.sqrt(5);
    Assert.assertEquals(ans, c.calculate("- sqrt 5"), 0.0001);
  }

  @Test
  public void SavingTest(){
    Calculator c = new Calculator();
    c.setSavingAnswers(true);
    Date date1 = new Date();
    double ans1 = c.calculate("+ + + + + + + + + + + sqrt * 1500 15 + - 7 900 + / 2005 400 7 1 1 1 1 1 1 1 1 1 1");
    Date date2 = new Date();
    double ans2 = c.calculate("+ + + + + + + + + + + sqrt * 1500 15 + - 7 900 + / 2005 400 7 1 1 1 1 1 1 1 1 1 1");
    Date date3 = new Date();
    Assert.assertTrue(date2.getTime() - date1.getTime() > date3.getTime() - date2.getTime());
  }

  @Test
  public void Correctness(){
    Calculator c = new Calculator();
    c.setSavingAnswers(true);
    try{
      c.calculate("5 + 2");
      Assert.fail();
    }catch (IllegalArgumentException ignored){};
    try{
      c.calculate("+ + 2 2 7 7");
      Assert.fail();
    }catch (IllegalArgumentException ignored){};
    try{
      c.calculate("sqrt");
      Assert.fail();
    }catch (IllegalArgumentException ignored){};
    try{
      c.calculate("sin - 5 2 3");
      Assert.fail();
    }catch (IllegalArgumentException ignored){};
  }
}
