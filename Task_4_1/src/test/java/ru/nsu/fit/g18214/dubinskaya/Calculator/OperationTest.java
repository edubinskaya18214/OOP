package ru.nsu.fit.g18214.dubinskaya.Calculator;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.dubinskaya.Operations.*;

public class OperationTest {
  @Test
  public void CorrectAns(){
    Operation o = new Plus();
    double arg1 = 10125;
    double arg2 = (double)1.856;
    Assert.assertEquals(arg1 + arg2, o.calculateOperation(arg1, arg2), 0.001);
    o = new Minus();
    Assert.assertEquals(arg1 - arg2, o.calculateOperation(arg1, arg2), 0.001);
    o = new Exp();
    Assert.assertEquals(Math.pow(arg1,arg2), o.calculateOperation(arg1, arg2), 0.001);
    o = new Multiplication();
    Assert.assertEquals(arg1*arg2, o.calculateOperation(arg1, arg2), 0.001);
    o = new Division();
    Assert.assertEquals(arg1/arg2, o.calculateOperation(arg1, arg2), 0.001);
    o = new Sqrt();
    Assert.assertEquals(Math.sqrt(arg1), o.calculateOperation(arg1), 0.001);
    o = new Abs();
    Assert.assertEquals(Math.abs(arg1), o.calculateOperation(arg1), 0.001);
    o = new Sin();
    Assert.assertEquals(Math.sin(arg1), o.calculateOperation(arg1), 0.001);
    o = new Cos();
    Assert.assertEquals(Math.cos(arg1), o.calculateOperation(arg1), 0.001);
    o = new Tan();
    Assert.assertEquals(Math.tan(arg1), o.calculateOperation(arg1), 0.001);
  }
}
