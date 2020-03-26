import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class RecordBookTest {

  RecordBook myBook = createMyRecordBook();

  private static RecordBook createMyRecordBook(){
    //Create Page 1
    RecordBooksPage page1 = new RecordBooksPage();
    page1.addRecord("Введение в алгебру и анализ", 180, '4',"Васкевич");
    page1.addRecord("Введение в дискретную математику", 180, '5', "Власов");
    page1.addRecord("ФКиС", 32, 'з', "Рябуха");
    page1.addRecord("Цифровые платформы", 144, 'з', "Иртегов");
    page1.addRecord("История России", 108, '5', "Рябцева");
    page1.addRecord("Основы культуры речи", 72, '5', "Борзенкова");
    page1.addRecord("Иностранный язык", 108, 'з', "Хоцкина");
    page1.addRecord("Декларативное програмирование", 108, '4', "Власов");
    page1.addRecord("Физкультура", 34, 'з', "Захарин");
    page1.addRecord("Императивное програмирование", 180, '4', "Зайцев");
    //Create Page 2
    RecordBooksPage page2 = new RecordBooksPage();
    page2.addRecord("ФКиС", 32, 'з', "Рябуха");
    page2.addRecord("Физкультура", 34, 'з', "Захарин");
    page2.addRecord("Декларативное програмирование", 144, '4', "Мигинский");
    page2.addRecord("Цифровые платформы", 216, '5', "Иртегов");
    page2.addRecord("Иностранный язык", 108, '4', "Хоцкина");
    page2.addRecord("Введение в алгебру и анализ", 180, '4',"Васкевич");
    page2.addRecord("Введение в дискретную математику", 180, '4', "Власов");
    page2.addRecord("Императивное програмирование", 180, '5', "Зайцев");
    RecordBook myBook = new RecordBook(180639, "Дубинская Екатерина", "ФИТ",2018,2022);
    myBook.addPage(page1);
    myBook.addPage(page2);
    return myBook;
  }

  @Test
  public void isHonorDegree(){
    Assert.assertFalse(myBook.isHonorDegree());
    Assert.assertEquals(4.42, myBook.getAverage(), 0.1);
    RecordBooksPage page1 = myBook.getPage(1);
    page1.addRecord("Вау я молодец", 180,'5', "Дубинская");
    page1.addRecord("Вау я молодец!", 180,'5', "Дубинская");
    page1.addRecord("Вау я молодец!!", 180,'5', "Дубинская");
    page1.addRecord("Вау я супер молодец!!", 180,'5', "Дубинская");
    page1.addRecord("Вау я очень молодец молодец!!!!!", 180,'5', "Дубинская");
    page1.addRecord("Вау я очень молодец молодец!!!", 180,'5', "Дубинская");
    page1.addRecord("Вау я очень молодец молоде!!ц!!!", 180,'5', "Дубинская");
    page1.addRecord("Вау я очень молодец молодец!!!!!", 180,'5', "Дубинская");
    page1.addRecord("Вау я очень молодец молодец!!!!!!", 180,'5', "Дубинская");
    Assert.assertTrue(myBook.isHonorDegree());
  }

  @Test
  public void AverageDegree(){
    myBook = createMyRecordBook();
    float average = myBook.getAverage();
    Assert.assertEquals(4.42, average, 0.01);
    Assert.assertFalse(myBook.isIncreasedScholarships());
  }

  @Test
  public void CatchExceptions(){
    try{
      myBook.addPage(null);
      Assert.fail();
    }catch (NullPointerException ignored){};
    RecordBooksPage page = myBook.getPage(1);
    try{
      myBook.getPage(0);
      Assert.fail();
    }catch (IllegalArgumentException ignored){};
    try{
      myBook.setQualifyingWork(0);
      Assert.fail();
    }catch (IllegalArgumentException ignored){};

  }
}
