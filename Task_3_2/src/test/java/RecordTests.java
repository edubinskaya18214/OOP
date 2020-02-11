import org.junit.Assert;
import org.junit.Test;

public class RecordTests {
  @Test
  public void creatingRecordGetSet(){
    Record record = new Record("Введение в алгебру и анализ", 180, '4',"Васкевич");
    Assert.assertEquals(4, record.getCredit());
    Assert.assertEquals(180, record.getHours());
    Assert.assertEquals("Васкевич", record.getTeacherName());
    Assert.assertEquals("Введение в алгебру и анализ", record.getDisciplineName());
    Assert.assertTrue(record.isDif());
  }

  @Test
  public void throwingExceptions(){
    Record record = new Record("Введение в алгебру и анализ", 180, '4',"Васкевич");
    try{
      record = new Record("Введение в алгебру и анализ", 180, '1',"Васкевич");
      Assert.fail();
    }catch (IllegalArgumentException ignored){};
    try{
      record = new Record("Введение в алгебру и анализ", 180, '6',"Васкевич");
      Assert.fail();
    }catch (IllegalArgumentException ignored){};
    try{
      record = new Record("Введение в алгебру и анализ", 180, 'i',"Васкевич");
      Assert.fail();
    }catch (IllegalArgumentException ignored){};
  }
}
