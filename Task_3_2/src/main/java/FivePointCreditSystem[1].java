public class FivePointCreditSystem extends Credit<Integer>{

  private int mark;

  public FivePointCreditSystem(int mark){
    if (mark > 5 || mark < 2) throw new IllegalArgumentException();
    this.mark = mark;
  }
  public Integer getMark() {
    return mark;
  }

  float addToAverageSum() {
    return mark;
  }

  float addToAverageElements() {
    return 1;
  }

  boolean isGood() {
    return mark == 4;
  }

  boolean isExcellent() {
    return mark == 5;
  }

  boolean isDiff() {
    return true;
  }
}
