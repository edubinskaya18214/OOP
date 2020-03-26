public class TwoPointCreditSystem extends Credit<Character> {

  Boolean mark;

  public TwoPointCreditSystem(char mark) {
    if (mark == 'з') this.mark = true;
    else if (mark == 'н') this.mark = false;
    else throw new IllegalArgumentException();
  }

  public Character getMark() {
    if (mark) return 'з';
    else return 'н';
  }

  float addToAverageSum() {
    return 0;
  }

  float addToAverageElements() {
    return 0;
  }

  boolean isGood() {
    return false;
  }

  boolean isExcellent() {
    return false;
  }

  boolean isDiff() {
    return false;
  }
}
