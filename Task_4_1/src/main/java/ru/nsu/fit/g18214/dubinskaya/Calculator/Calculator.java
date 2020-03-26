package ru.nsu.fit.g18214.dubinskaya.Calculator;

import ru.nsu.fit.g18214.dubinskaya.Operations.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Calculator {

  private List<String> expressions;
  private boolean saveAns = false;
  private HashMap<String, Double> savedExpressions = new HashMap<>();
  private OperationFactory factory = new DefaultFactory();
  /**
   * @param expression - expression that calculator will calculate.
   *                     Should be correct formula in prefix form:
   *                     Arguments for operation follow after operation, number of arguments is right,
   *                     operations follow before arguments: between operation and it's arguments,
   *                     delimiters.
   * @return result of counting.
   * @throws IllegalArgumentException if expression incorrect.
   */

  public double calculate(String expression) {
    expressions = new LinkedList<>();
    if (savedExpressions.containsKey(expression)) return savedExpressions.get(expression);
    expressions.addAll(Arrays.asList( expression.split(" ")));
    double ans = calculateExpression();
    if (saveAns) savedExpressions.put(expression, ans);
    return ans;
  }
  /**
   * this method set state of calculator.
   * @param flag - if flag if true, calculator will save answers, to use them next time
   *               if user will want to calculate the same expression.
   */
  public void setSavingAnswers(boolean flag) {
    saveAns = flag;
  }

  /**
   * this method set Operation factory.
   * @param factory - new Operation factory that will used for calculation.
   */
  public void setFactory(OperationFactory factory){
    if (factory == null)
      throw new NullPointerException();
    this.factory = factory;
  }

  private double calculateExpression() {
    int tokenNumber = expressions.size() - 1;
    while (expressions.size() != 1) {
      if (tokenNumber < 0)
        throw new IllegalArgumentException("Incorrect number of operations/numbers");
      if (expressions.get(tokenNumber).equals("")) {
        expressions.remove(tokenNumber--);
        continue;
      }
      if (factory.isSupported(expressions.get(tokenNumber))){
        Operation op = factory.getOperation(expressions.get(tokenNumber));
        if (op == null) throw new IllegalArgumentException();
        int numArg = op.getNumArg();
        if (numArg + tokenNumber >= expressions.size())
          throw new IllegalArgumentException("Not enough arguments for operation");
        double[] args = new double[numArg];
        for (int i = 0; i < numArg; ++i) {
          args[i] = Double.parseDouble(expressions.remove(tokenNumber + 1));
        }
        double result = op.calculateOperation(args);
        expressions.remove(tokenNumber);
        expressions.add(tokenNumber, String.valueOf(result));
      }
      else if (!isDouble(expressions.get(tokenNumber)))
        throw new IllegalArgumentException("Incorrect operation: " + expressions.get(tokenNumber));
      tokenNumber--;
    }
    return Double.parseDouble(expressions.get(0));
  }

  private boolean isDouble(String expr){
    boolean flag = false;
    char[] str = expr.toCharArray();
    for (char c : str) {
      if (c == '.') {
        if (flag) return false;
        flag = true;
        continue;
      }
      if ("0123456789".indexOf(c) != -1)
        continue;
      return false;
    }
    return true;
  }
}