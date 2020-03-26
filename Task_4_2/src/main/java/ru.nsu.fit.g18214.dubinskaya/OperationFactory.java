package ru.nsu.fit.g18214.dubinskaya;

public class OperationFactory {
  /**
   * @param operationName - method will return function with thus name
   * @return operation with name Name
   */
  public static Operation getOperation(String operationName) {
    switch (operationName) {
      case "+":
        return new Plus();
      case "-":
        return new Minus();
      case "/":
        return new Division();
      case "*":
        return new Multiplication();
      case "^":
        return new Exp();
      case "abs":
        return new Abs();
      case "sin":
        return new Sin();
      case "cos":
        return new Cos();
      case "tan":
        return new Tan();
      case "sqrt":
        return new Sqrt();
      default: return null;
    }
  }
}
