package ru.nsu.fit.g18214.dubinskaya;

public class Date {

  private int day;
  private int month;
  private int year;
  private long daysFromAD;
  private boolean isAD = true;

  /*
   * class constructor, create class Date
   * @param numDays - it's num days from era beginning.
   */
  public Date(long numDays) {
    if (numDays == 0)
      numDays = 1;
    if (numDays < 0) {
      isAD = false;
      numDays = Math.abs(numDays);
    }
    daysFromAD = numDays;
    final int DAYS_IN_CENTURY = 36524;
    int century = (int) numDays / DAYS_IN_CENTURY;
    numDays = numDays - century * DAYS_IN_CENTURY;
    numDays += century / 4;
    int y = 1;
    // counting year
    while ((y % 4 == 0 && numDays > 366) || (y % 4 != 0 && numDays > 365)) {
      ++y;
      if (y % 4 == 0) {
        numDays -= 365;
      } else {
        numDays -= 366;
      }
    }
    // counting month
    boolean flag = false;
    int m = 1;
    for (int i = 1; !flag; ++i) {
      switch (i) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
          if (numDays > 31) {
            numDays -= 31;
          } else {
            flag = true;
            m = i;
          }
          break;
        case 4:
        case 6:
        case 9:
        case 11:
          if (numDays > 30) {
            numDays -= 30;
          } else {
            flag = true;
            m = i;
          }
          break;
        case 2:
          if (y % 4 == 0 && y != 0) {
            if (numDays > 29) {
              numDays -= 29;
            } else {
              flag = true;
              m = i;
            }
          } else {
            if (numDays > 28) {
              numDays -= 28;
            } else {
              flag = true;
              m = i;
            }
          }
          break;
      }
      day = (int) numDays;
      month = m;
      year = y + 100 * century;
    }
  }

  /*
   * class constructor, create class Date.
   * @param setDay - num days should be less more then 0 and not more than number or days in month setMonth.
   * @param setMonth - month number from year beginning, 0 < setMonth <= 12.
   * @param setYear - year number from era beginning, year != 0, if setYear < 0, it'll be "BC".
   * @throw IllegalArgumentException if one of argument incorrect.
   */

  public Date(int setDay, int setMonth, int setYear) throws IllegalArgumentException {
    isAD = setYear >= 0;
    setYear = Math.abs(setYear);
    if (isIncorrectDate(setDay, setMonth, setYear)) {
      throw new IllegalArgumentException();
    }
    day = setDay;
    month = setMonth;
    year = setYear;
    daysFromAD = countDays(day, month, year);
  }

  /*
   * method correctDate return true it date is correct
   * @param setDay - num days should be less more then 0 and not more than number or days in month setMonth.
   * @param setMonth - month number from year beginning, 0 < setMonth <= 12.
   * @param setYear - year number from era beginning, year != 0.
   */
  public static boolean isIncorrectDate(int setDay, int setMonth, int setYear) {
    if (setMonth < 1 || setMonth > 12 || setDay < 1 || setYear == 0) {
      return true;
    }
    switch (setMonth) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        if (setDay > 31) {
          return true;
        }
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        if (setDay > 30) {
          return true;
        }
        break;
      case 2:
        if (((setYear % 4 == 0) && !(setYear % 100 == 0)) || (setYear % 400 == 0)) {
          if (setDay > 29) {
            return true;
          }
        } else if (setDay > 28) {
          return true;
        }
        break;
    }
    return false;
  }

  /*
   * method countDays - from standard data format return num days from era beginning.
   * @param day - num days should be less more then 0 and not more than number or days in month month.
   * @param month - month number from year beginning, 0 < month <= 12.
   * @param year - year number from era beginning, year != 0
   * @throw IllegalArgumentException if one of argument incorrect.
   */
  public static long countDays(int day, int month, int year)
      throws IllegalArgumentException {
    if (isIncorrectDate(day, month, year)) {
      throw new IllegalArgumentException();
    }
    long countDays;
    countDays = (year / 100) * 36524;
    countDays += year / 400;
    switch (month - 1) {
      case 0:
        break;
      case 1:
        countDays += 31;
        break;
      case 2:
        countDays += 59;
        break;
      case 3:
        countDays += 90;
        break;
      case 4:
        countDays += 120;
        break;
      case 5:
        countDays += 151;
        break;
      case 6:
        countDays += 181;
        break;
      case 7:
        countDays += 212;
        break;
      case 8:
        countDays += 243;
        break;
      case 9:
        countDays += 273;
        break;
      case 10:
        countDays += 304;
        break;
      case 11:
        countDays += 334;
        break;
    }
    if ((((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) && month > 2) {
      countDays += 1;
    }
    for (int i = 1; i < year % 100; ++i) {
      if (i % 4 == 0) {
        countDays += 366;
      } else {
        countDays += 365;
      }
    }
    countDays += day;
    return countDays;
  }
  /*
   * @return num days in date.
   */
  public int getDay() {
    return day;
  }

  /*
   * @return month's num in date.
   */
  public int getMonth() {
    return month;
  }

  /*
   * @return year's num in date.
   */
  public int getYear() {
    return year;
  }

  /*
   * @param Date d - compared date.
   * @return true if this date after Date d.
   * @throw NullPointerExceptions if @param d == null.
   */
  public boolean isAfter(Date d) {
    if (d == null) {
      throw new NullPointerException();
    }
    return (d.daysFromAD < this.daysFromAD);
  }

  /*
   * @param Date d - compared date.
   * @return true if this date before Date d.
   * @throw NullPointerExceptions if @param d == null.
   */
  public boolean isBefore(Date d) {
    if (d == null) {
      throw new NullPointerException();
    }
    return (d.daysFromAD > this.daysFromAD);
  }

  /*
   * @param Date d - compared date.
   * @return true if this date equals Date d.
   * @throw NullPointerExceptions if @param d == null.
   */
  public boolean isEquals(Date d) {
    if (d == null) {
      throw new NullPointerException();
    }
    return (d.daysFromAD == this.daysFromAD);
  }

  /*
   * @return date in format "dd MM YY era".
   */
  public String toString() {
    String date = "";
    date += weekDayToString() + " ";
    date += Calendar.MONTH[month-1] + " ";
    date += day + ", " + Math.abs(year) + " ";
    if (isAD) {
      date += "AD";
    } else {
      date += "BC";
    }
    return date;
  }

  /*
   * @return num days from era beginning.
   */
  public long getDaysFromEraBeginning() {
    return daysFromAD;
  }

  /*
   * @return week day as string.
   */
  public String weekDayToString() {
    int weekday = ((int) (daysFromAD - 1) % 7);
    return Calendar.WEEKDAY[weekday];
  }
  /*
   * @return weekday as int.
   */
  public int weekDayToInt() {
    return (int) (daysFromAD - 1) % 7 + 1;
  }
}
