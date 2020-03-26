import org.junit.Assert;
import org.junit.Test;

public class RecordBookPageTests {
  private RecordBooksPage createPages() {
    RecordBooksPage page1 = new RecordBooksPage();
    page1.addRecord("Введение в алгебру и анализ", 180, '4', "Васкевич");
    page1.addRecord("Введение в дискретную математику", 180, '5', "Власов");
    page1.addRecord("ФКиС", 32, 'з', "Рябуха");
    page1.addRecord("Цифровые платформы", 144, 'з', "Иртегов");
    page1.addRecord("История России", 108, '5', "Рябцева");
    page1.addRecord("Основы культуры речи", 72, '5', "Борзенкова");
    page1.addRecord("Иностранный язык", 108, 'з', "Хоцкина");
    page1.addRecord("Декларативное програмирование", 108, '4', "Власов");
    page1.addRecord("Физкультура", 34, 'з', "Захарин");
    page1.addRecord("Императивное програмирование", 180, '4', "Зайцев");
    return page1;
  }

  @Test
  public void testCreatePageGetAverage(){
    RecordBooksPage page1 = createPages();
    Assert.assertEquals(4.5, page1.getAverage(), 0.0001);
    page1.deleteRecord("ФКиС");
    Assert.assertEquals(4.5, page1.getAverage(), 0.0001);
    page1.deleteRecord("Декларативное програмирование");
    Assert.assertEquals(4.59, page1.getAverage(), 0.01);
    page1.deleteRecord("История России");
    Assert.assertEquals(4.5, page1.getAverage(), 0.0001);
    Assert.assertNull(page1.deleteRecord("История России"));
    Assert.assertNull(page1.getRecord("История России"));
    Record history = new Record("История России", 108, '5', "Рябцева");
    page1.addRecord(history);
    Assert.assertEquals(4.59, page1.getAverage(), 0.01);
    Assert.assertEquals(history, page1.getRecord("История России"));
  }

  @Test
  public void throwExceptions(){
    RecordBooksPage page1 = createPages();
    try{
      page1.addRecord("Введение в алгебру и анализ", 180, 'i',"Васкевич");
      Assert.fail();
    }catch (IllegalArgumentException ignored){};
    try{
      page1.addRecord("Введение в алгебру и анализ", 180, '1',"Васкевич");
      Assert.fail();
    }catch (IllegalArgumentException ignored){};
    try{
      page1.addRecord(null);
      Assert.fail();
    }catch (NullPointerException ignored){};
  }
}
