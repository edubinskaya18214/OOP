package ru.nsu.fit.g18214.dubinskaya.Operations;

public interface Operation {
  /**
   * @return number of arguments in function.
   */
  int getNumArg();

  /**
   * @param args - arguments for function
   * @return result of function
   * @throw IllegalArgumentsException if number of arguments incorrect
   */
  double calculateOperation(double ... args);
}