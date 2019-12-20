package ru.nsu.fit.g18214.dubinskaya;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Calculator {

  private List<String> string;
  private boolean saveAns = false;
  private boolean useAns = false;
  private HashMap<String, Double> Answers = new HashMap<>();

  /**
   * @param expression - expression that calculator will calculate.
   *                     Should be correct formula in prefix form.
   * @return result of counting.
   * @throws IllegalArgumentException if expression incorrect.
   */
  public double calculate(String expression) {
    string = new ArrayList<>();
    if (useAns && Answers.containsKey(expression)) return Answers.get(expression);
    parseAndSave(expression);
    double ans = counting();
    if (saveAns) Answers.put(expression, ans);
    return ans;
  }

  /**
   * this method fix state of calculator.
   * @param flag - if flag if true, calculator will save answers, to use them next time
   *               if user will want to calculate the same expression.
   */
  public void setSavingAnswers(boolean flag) {
    saveAns = flag;
  }

  private void parseAndSave(String input) {
    char[] in = input.toCharArray();
    StringBuilder current = new StringBuilder();
    int i = 0;
    while (i < in.length && isDelimiter(in[i]))
      ++i;
    for (; i < in.length; ) {
      if (isDelimiter(in[i])) {
        string.add(current.toString());
        current.delete(0, current.length());
        while (i < in.length && isDelimiter(in[i]))
          ++i;
      } else {
        current.append(in[i]);
        ++i;
      }
    }
    if (!current.toString().equals(""))
      string.add(current.toString());
  }

  private double counting() {
    int pos = string.size() - 2;
    while (string.size() != 1) {
      if (string.size() == 2 && string.get(0).equals("-")) {
        double ans;
        try {
          ans = Double.parseDouble(string.get(1));
          return -ans;
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException();
        }
      }
      if (pos < 0)
        throw new IllegalArgumentException();
      if (isOperator(string.get(pos))) {
        Operation op = OperationFactory.getOperation(string.get(pos));
        if (op == null) throw new IllegalArgumentException();
        int numArg = op.getNumArg();
        if (numArg + pos >= string.size()) throw new IllegalArgumentException();
        double[] args = new double[numArg];
        for (int i = 0; i < numArg; ++i) {
          args[i] = Double.parseDouble(string.remove(pos + 1));
        }
        double result = op.calculateOperation(args);
        string.remove(pos);
        string.add(pos, String.valueOf(result));
      }
      pos--;
    }
    return Double.parseDouble(string.get(0));
  }

  static boolean isDelimiter(char c) {
    return (" ".indexOf(c) != -1);
  }

  static boolean isOperator(String str) {
    return (str.equals("sin") || str.equals("cos") || str.equals("-") || str.equals("/") || str.equals("*")
        || str.equals("abs") || str.equals("sqrt") || str.equals("tan") || str.equals("+") || str.equals("^"));
  }
}