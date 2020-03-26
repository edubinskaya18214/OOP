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
  public Record(String discipline, int hours, Credit mark, String teacher) throws IllegalArgumentException {
    disciplineName = discipline;
    this.hours = hours;
    this.mark = mark;
    teacherName = teacher;
  }

  /**
   * @return number of hours you study this discipline.
   */
  public int getHours() {
    return hours;
  }
  /**
   * @return mark on this discipline.
   */
  public Credit getCredit() {
    return mark;
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
