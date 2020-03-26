import org.junit.Assert;
import org.junit.Test;

public class RecordBookPageTests {

  private RecordBooksPage createPages() {
    Credit<Integer> FIVE = new FivePointCreditSystem(5);
    Credit<Integer> FOUR = new FivePointCreditSystem(4);
    Credit<Character> PASS = new TwoPointCreditSystem('з');
    RecordBooksPage page1 = new RecordBooksPage();
    page1.addRecord("Введение в алгебру и анализ", 180, FOUR, "Васкевич");
    page1.addRecord("Введение в дискретную математику", 180, FIVE, "Власов");
    page1.addRecord("ФКиС", 32, PASS, "Рябуха");
    page1.addRecord("Цифровые платформы", 144, PASS, "Иртегов");
    page1.addRecord("История России", 108, FIVE, "Рябцева");
    page1.addRecord("Основы культуры речи", 72, FIVE, "Борзенкова");
    page1.addRecord("Иностранный язык", 108, PASS, "Хоцкина");
    page1.addRecord("Декларативное програмирование", 108, FOUR, "Власов");
    page1.addRecord("Физкультура", 34, PASS, "Захарин");
    page1.addRecord("Императивное програмирование", 180, FOUR, "Зайцев");
    return page1;
  }

  @Test
  public void throwExceptions() {
    RecordBooksPage page1 = createPages();
    try {
      page1.addRecord("Введение в алгебру и анализ", 180,  new FivePointCreditSystem(11), "Васкевич");
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    };
    try {
      page1.addRecord("Введение в алгебру и анализ", 180, new  FivePointCreditSystem(105), "Васкевич");
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    };
    try {
      page1.addRecord(null);
      Assert.fail();
    } catch (NullPointerException ignored) {
    };
  }
}
