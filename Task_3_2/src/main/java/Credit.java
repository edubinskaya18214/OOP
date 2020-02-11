class Credit {
  private boolean isDif = true;
  private int mark;
  public Credit(char mark){
    if (mark > '2' && mark <= '5')
      this.mark = mark - '0';
    else if (mark == 'з')
      isDif = false;
    else throw new IllegalArgumentException();
  }
  boolean isDif(){
    return isDif;
  }
  int getMark(){
    return mark;
  }
  void setMark(char mark){
    if (mark > '2' && mark <= '5')
      this.mark = mark - '0';
    else if (mark == 'з')
      isDif = false;
    else throw new IllegalArgumentException();
  }
}
