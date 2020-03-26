package ru.nsu.fit.g18214.dubinskaya.Operations;

public class DefaultFactory implements OperationFactory {
  /**
   * @param operationName - method will return function with thus name
   * @return operation with name Name
   */
  public boolean isSupported(String operationName){
    return (operationName.equals("sin") || operationName.equals("cos") ||
        operationName.equals("-") || operationName.equals("/") || operationName.equals("*")
        || operationName.equals("abs") || operationName.equals("sqrt") ||
        operationName.equals("tan") || operationName.equals("+") || operationName.equals("^"));
  }
  public Operation getOperation(String operationName) {
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
