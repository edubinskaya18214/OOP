public abstract class Credit<T> {

  private T mark;
  abstract public T getMark();
  abstract float addToAverageSum();
  abstract float addToAverageElements();
  abstract boolean isGood();
  abstract boolean isExcellent();
  abstract boolean isDiff();
}
