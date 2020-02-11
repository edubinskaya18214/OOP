public class Record {
  private String disciplineName;
  private int hours;
  private Credit mark;
  private String teacherName;

  /**
   * @param discipline - name of discipline.
   * @param hours      - number of hours studying this discipline.
   * @param mark       - credit.
   * @param teacher    - name of teacher.
   * @throws IllegalArgumentException if mark doesn't in ['3','5'] or 'з'.
   */
  public Record(String discipline, int hours, char mark, String teacher) throws IllegalArgumentException {
    disciplineName = discipline;
    this.hours = hours;
    this.mark = new Credit(mark);
    teacherName = teacher;
  }

  /**
   * @return number of hours you study this discipline.
   */
  public int getHours() {
    return hours;
  }

  /**
   * @return true if credit in bound [3,5] if you have 'з' return false.
   */
  public boolean isDif() {
    return mark.isDif();
  }

  /**
   * @return mark on this discipline.
   */
  public int getCredit() {
    return mark.getMark();
  }

  /**
   * @return teacher's name.
   */
  public String getTeacherName() {
    return teacherName;
  }

  /**
   * @return discipline's name.
   */
  public String getDisciplineName() {
    return disciplineName;
  }
}
