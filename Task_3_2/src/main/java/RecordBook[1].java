import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class RecordBook {
  private int bookNumber;
  private String studentsName;
  private String faculty;
  private int beginYear;
  private int endYear;
  private int qualifyWorkCredit = -1;
  private ArrayList<RecordBooksPage> lists = new ArrayList<>();

  /**
   * Record book constructor. Create object RecordBook.
   *
   * @param bookNumber - set as student's record book's number.
   * @param student    - set as student's name.
   * @param faculty    - set as name of student's faculty.
   * @param beginYear  - year of beginning studying.
   * @param endYear    - year of ending studying.
   */
  public RecordBook(int bookNumber, String student, String faculty, int beginYear, int endYear) {
    this.bookNumber = bookNumber;
    this.studentsName = student;
    this.faculty = faculty;
    this.beginYear = beginYear;
    this.endYear = endYear;
  }

  /**
   * @return student's name.
   */
  public String getStudentName() {
    return studentsName;
  }

  /**
   * method getFaculty.
   *
   * @return faculty's name.
   */
  public String getFaculty() {
    return faculty;
  }

  /**
   * method getBookNumber.
   *
   * @return book's number.
   */
  public int getBookNumber() {
    return bookNumber;
  }

  /**
   * method getQualifyingWork.
   *
   * @return Qualify work credit.
   */
  public int getQualifyingWork() {
    return qualifyWorkCredit;
  }

  /**
   * method getPages.
   *
   * @return all saved pages in RecordBook as ArrayList<RecordBooksPage>
   */
  public ArrayList<RecordBooksPage> getPages() {
    return lists;
  }

  /**
   * method RecordBooksPage
   *
   * @param semesterNum - number of semester
   * @return RecordBookPage with number semesterNum, should be >= 1
   * if page with this number doesn't exist return null pointer.
   * @throws IllegalArgumentException if RecordBookPage < 1.
   */
  public RecordBooksPage getPage(int semesterNum) {
    if (semesterNum < 1) throw new IllegalArgumentException();
    if (semesterNum > lists.size()) return null;
    return lists.get(semesterNum - 1);
  }

  /**
   * method getAverage.
   *
   * @return average, using all credits in record book.
   */
  public float getAverage() {
    Iterator<RecordBooksPage> iter = lists.iterator();
    int markNum = 0;
    int markSum = 0;
    RecordBooksPage current;
    while (iter.hasNext()) {
      current = iter.next();
      ArrayList<Record> records = current.getRecords();
      Iterator<Record> iter2 = records.iterator();
      while (iter2.hasNext()) {
        Record currentRec = iter2.next();
        markNum += currentRec.getCredit().addToAverageElements();
        markSum += currentRec.getCredit().addToAverageSum();
      }
    }
    return (float) (Math.round((float) markSum / markNum * 100)) / 100;
  }

  /**
   * method setQualifyWork.
   *
   * @param mark - credit that will be save as diploma
   * @throws IllegalArgumentException if mark > 5 or mark < 3.
   */
  public void setQualifyingWork(int mark) {
    if (mark < 3 || mark > 5)
      throw new IllegalArgumentException();
    qualifyWorkCredit = mark;
  }

  /**
   * @param page - page that will be added as next semester.
   * @throws IndexOutOfBoundsException if page > num of semesters.
   * @throws NullPointerException      if page == null.
   */
  public void addPage(RecordBooksPage page) {
    if (page == null) throw new NullPointerException();
    if (lists.size() > (endYear - beginYear) * 2) throw new IndexOutOfBoundsException();
    lists.add(page);
  }

  /**
   * method isIncreasedScholarships.
   *
   * @return true if in the last semester (on the last page) we have only '5' credits.
   */
  public boolean isIncreasedScholarships() {
    if (lists.size() - 1 == 0) return false;
    ArrayList<Record> recordsList = getPage(lists.size() - 1).getRecords();
    int excellent = 0;
    int good = 0;
    int less = 0;
    for (Record currentRecord : recordsList) {
        Credit currentCredit = currentRecord.getCredit();
        if (currentCredit.isDiff()) {
          if (currentCredit.isExcellent()) excellent++;
          else if (currentCredit.isGood()) good++;
          else less++;
      }
    }
    return good == 0 && less == 0;
  }
  /**
   * method isHonorDegree.
   *
   * @return true if 75% last credits in book is 5,
   * and if no one credit 3,
   * and if have QualifyWork it have credit 5.
   */
  public boolean isHonorDegree() {
    if (qualifyWorkCredit != -1 && qualifyWorkCredit < 5) return false;
    HashMap<String, Credit> discipline = new HashMap<>();
    Iterator<RecordBooksPage> pages = lists.iterator();
    RecordBooksPage currentPage;
    while (pages.hasNext()) {
      currentPage = pages.next();
      ArrayList<Record> recordsList = currentPage.getRecords();
      for (Record currentRecord : recordsList) {
        discipline.put(currentRecord.getDisciplineName(), currentRecord.getCredit());
      }
    }
    int excellent = 0;
    int good = 0;
    int less = 0;
    for (String i : discipline.keySet()) {
      Credit currentCredit = discipline.get(i);
      if (currentCredit.isDiff()) {
        if (currentCredit.isExcellent()) excellent++;
        else if (currentCredit.isGood()) good++;
        else less++;
      }
    }
    if (less > 0) return false;
    return (((float) excellent) / (float) (good + excellent) >= 0.75);
  }
}
