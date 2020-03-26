package ru.nsu.fit.g18214.dubinskaya;

public class Calendar {

  public static final int JANUARY = 1;
  public static final int FEBRUARY = 2;
  public static final int MARCH = 3;
  public static final int APRIL = 4;
  public static final int MAY = 5;
  public static final int JUNE = 6;
  public static final int JULY = 7;
  public static final int AUGUST = 8;
  public static final int SEPTEMBER = 9;
  public static final int OCTOBER = 10;
  public static final int NOVEMBER = 11;
  public static final int DECEMBER = 12;

  public static final String[] MONTH = {
    "January", "February",
    "March", "April",
    "May", "June",
    "July", "August",
    "September", "October",
    "November", "December"
  };

  public static final int MONDAY = 1;
  public static final int TUESDAY = 2;
  public static final int WEDNESDAY = 3;
  public static final int THURSDAY = 4;
  public static final int FRIDAY = 5;
  public static final int SATURDAY = 6;
  public static final int SUNDAY = 7;

  public static final String[] WEEKDAY = {
    "Monday", "Tuesday",
    "Wednesday", "Thursday",
    "Friday", "Saturday",
    "Sunday"
  };

  private Date today;
  private int todayDay;
  private int todayMonth;
  private int todayYear;
  /*
   * Calendar constructor.
   * @param day, month, year should be correct as date (recommended use Date.correctDate()).
   * @throw IllegalArgumentException if date incorrect.
   */
  public Calendar(int day, int month, int year) throws IllegalArgumentException {
    if (Date.isIncorrectDate(day, month, year)) throw new IllegalArgumentException();
    today = new Date(day, month, year);
    todayDay = today.getDay();
    todayYear = today.getYear();
    todayMonth = today.getMonth();
  }
  /*
   * Calendar constructor.
   * @param Date.
   * @throws NullPointerException if now == null.
   */
  public Calendar(Date now) throws NullPointerException {
    if (now == null) throw new NullPointerException();
    this.today = now;
    todayDay = now.getDay();
    todayYear = now.getYear();
    todayMonth = now.getMonth();
  }

  /*
   * @param Date date1, date2 - between thous dates finds number of days.
   * @return number of days between @param date and @param date2.
   * @throw NullPointer exception if one of dates is null pointer.
   */
  public static long daysBetweenDates(Date date1, Date date2) throws NullPointerException {
    if (date1 == null || date2 == null) throw new NullPointerException();
    return Math.abs(date1.getDaysFromEraBeginning() - date2.getDaysFromEraBeginning());
  }

  /*
   * @param years - num years to add to date.
   * @return new Date with added years.
   */
  public Date addYears(int years) {
    years += todayYear;
    return new Date(todayDay, todayMonth, years);
  }

  /*
   * @param months - num months to add.
   * @return new Date with added months.
   */
  public Date addMonths(int months) {
    int currentM = todayMonth;
    currentM += months;
    int currentY;
    if (currentM >= 0) {
      currentY = todayYear + (currentM - 1) / 11;
      currentM = currentM % 12;
    } else {
      int times = Math.abs(currentM / 11) + 1;
      currentY = todayYear - times;
      currentM %= 12;
      if (currentM == 0) currentM = 1;
    }

    return new Date(todayDay, currentM, currentY);
  }

  /*
   * @param numDays - will added to current Date.
   * @return Date with added days.
   */
  public Date addDays(int numDays) {
    long currentDays = today.getDaysFromEraBeginning() + numDays;
    return new Date(currentDays);
  }
  /*
   * @param numDays - will added to current Date.
   * @return Date with added weeks.
   */
  public Date addWeeks(int numWeeks) {
    return addDays(7 * numWeeks);
  }
  /*
   * @return current date.
   */
  public Date getToday() {
    return today;
  }
  /*
   * @param newD - set this day in current date.
   * @throws IllegalArgumentException if date with newD become incorrect.
   */
  public void setDay(int newD) throws IllegalArgumentException {
    Date tryDate = new Date(newD, todayMonth, todayYear);
    todayDay = newD;
    today = tryDate;
  }
  /*
   * @param newM - set this month in current date.
   * @throws IllegalArgumentException if date with new months incorrect.
   */
  public void setMonths(int newM) throws IllegalArgumentException {
    Date tryDate = new Date(todayDay, newM, todayYear);
    todayMonth = newM;
    today = tryDate;
  }
  /*
   * @param newY - set this year in current date.
   * @throws IllegalArgumentException if Date today become incorrect with new year.
   */
  public void setYear(int newY) throws IllegalArgumentException {
    Date tryDate = new Date(todayDay, todayMonth, newY);
    todayYear = newY;
    today = tryDate;
  }
  /*
   * return current date as string.
   */
  public String toString() {
    return today.toString();
  }
  /*
   * @param Date date - between this date and current Date in Calendar finding
   * number of days.
   * @return number of days between current Date (this.date) and @param date
   */
  public long daysBetweenDates(Date date) throws NullPointerException {
    if (date == null) throw new NullPointerException();
    return daysBetweenDates(this.today, date);
  }
  /*
   * @param int weekday - weekday in return Date.
   * @param int monthsDay - day in month int return Date.
   * @return nearest Date after today that have weekday and month day as days int parameters.
   * @throws IllegalArgumentException if Date weekday > 7 or weekday < 1 or months day < 1 or months day > 31
   */
  public Date getNearestWeekdayMonthDay(int weekday, int monthsDay) {
    int currentMonths = todayMonth;
    int currentYear = todayYear;
    if (weekday > 7 || weekday < 1 || monthsDay < 1 || monthsDay > 31)
      throw new IllegalArgumentException();
    boolean isAfterToday = (monthsDay >= todayDay);
    Date current = today;
    while (current.weekDayToInt() != weekday || !isAfterToday) {
      if (!Date.isIncorrectDate(monthsDay, currentMonths, currentYear)) {
        current = new Date(monthsDay, currentMonths, currentYear);
        if (current.weekDayToInt() == weekday && isAfterToday) break;
      }
        currentMonths++;
        currentYear += (currentMonths - 1) / 12;
        currentMonths %= 13;
        if (currentMonths == 0) currentMonths = 1;
        isAfterToday = true;
    }
    return current;
  }
  /*
   * @param int day - day int month in return Date.
   * @param int months - month in return Date.
   * @return nearest Date after today that have day and month as in parameters.
   * @throws IllegalArgumentException if Date with this day and month can't exist.
   */
  public Date getNearestDayMonth(int day, int month) {
    boolean isCorrect = !Date.isIncorrectDate(day, month, todayYear);
    int delta = 0;
    for (; delta < 8 && !isCorrect;){
      ++delta;
      isCorrect = !Date.isIncorrectDate(day, month, todayYear + delta);
    }
    if (!isCorrect) throw new IllegalArgumentException();
    Date current = new Date(day, month, todayYear + delta);
    if (today.isAfter(current)){
      isCorrect = false;
      for (; delta < 16 && !isCorrect; ++delta){
        isCorrect = !Date.isIncorrectDate(day, month, todayYear + delta);
      }
      if (!isCorrect) throw new IllegalArgumentException();
      current = new Date(day, month, todayYear + delta);
    }
    return current;
  }
}
