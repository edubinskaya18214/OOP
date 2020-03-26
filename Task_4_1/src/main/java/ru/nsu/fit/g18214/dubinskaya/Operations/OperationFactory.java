package ru.nsu.fit.g18214.dubinskaya.Operations;

/**
 * this interface using by calculator to parse operations.
 */
public interface OperationFactory {
  /**
   * this method check factory can support this operation
   * @param operationName - name of checked operation.
   * @return true if operation supported.
   * @throws NullPointerException is you put null as operationName
   */
  boolean isSupported(String operationName) throws NullPointerException;
  /**
   * this method return Operation
   * @param operationName - name of return operation.
   * @return Operation if it supported, else return null
   * @throws NullPointerException is you put null as operationName
   */
  Operation getOperation(String operationName);
}
