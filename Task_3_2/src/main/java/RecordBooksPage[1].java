import java.util.ArrayList;
import java.util.Iterator;

public class RecordBooksPage {
  private ArrayList<Record> records = new ArrayList<>();

  /**
   * method addRecord add new record in ArrayList records.
   * @param discipline - name of discipline.
   * @param hours - number of hours studying this discipline.
   * @param mark - credit.
   * @param teacher - name of teacher.
   * @throws IllegalArgumentException if mark doesn't in ['3','5'] or 'з'.
   * */
  public void addRecord(String discipline, int hours, Credit mark, String teacher) {
    Record current = new Record(discipline, hours, mark, teacher);
    records.add(current);
  }

  /**
   * method addRecord add new record in ArrayList records.
   * @param addedRecord - record that will add it ArrayList records.
   * @throws NullPointerException - if added Record is null pointer.
   */
  public void addRecord(Record addedRecord) throws NullPointerException {
    if (addedRecord == null)
      throw new NullPointerException();
    records.add(addedRecord);
  }

  /**
   * delete record and return it's link.
   * @param disciplineName - name of delete discipline.
   * @return deleted record, if record doesn't exist return null.
   */
  public Record deleteRecord(String disciplineName){
    Iterator<Record> iter = records.iterator();
    int i = 0;
    while(iter.hasNext()){
      Record current = iter.next();
      if (current.getDisciplineName().equals(disciplineName)){
        records.remove(i);
        return current;
      }
      i++;
    }
    return null;
  }

  /**
   * find and return record.
   * @param disciplineName - name of finding discipline.
   * @return - this record, or null if this record doesn't exist.
   */
  public Record getRecord(String disciplineName){
    for (Record current : records) {
      if (current.getDisciplineName().equals(disciplineName)) {
        return current;
      }
    }
    return null;
  }

  /**
   * @return ArrayList with all records.
   */
  public ArrayList<Record> getRecords() {
    return records;
  }
}
