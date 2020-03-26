package ru.nsu.fit.g18214.dubinskaya;

import org.junit.Assert;
import org.junit.Test;

public class CalendarTests {
  private Date now = new Date(27, 11, 2019);

  @Test
  public void constructorTest() {
    Calendar c = new Calendar(now);
    Calendar c1 = new Calendar(27, 11, 2019);
    Assert.assertEquals(0, c.daysBetweenDates(c1.getToday()));
  }

  @Test
  public void countingDaysTest() {
    Calendar c = new Calendar(now);
    Date newYear = new Date(1, 1, 2020);
    long day = Calendar.daysBetweenDates(c.getToday(), newYear);
    Assert.assertEquals(35, day);
    Date yearBefore = new Date(27, 11, 2018);
    Assert.assertEquals(365, c.daysBetweenDates(yearBefore));
    Assert.assertEquals(365 * 4 + 1, c.daysBetweenDates(new Date(27, 11, 2015)));
  }

  @Test
  public void addDaysMonthsYears() {
    Calendar c = new Calendar(now);
    Date current = c.addYears(5);
    Assert.assertEquals("Wednesday November 27, 2024 AD", current.toString());
    current = c.addYears(-5);
    Assert.assertEquals("Thursday November 27, 2014 AD", current.toString());
    current = c.addMonths(5);
    Assert.assertEquals("Monday April 27, 2020 AD", current.toString());
    current = c.addMonths(-5);
    Assert.assertEquals("Thursday June 27, 2019 AD", current.toString());
    current = c.addDays(-(365 * 4) - 1);
    Assert.assertTrue(current.isEquals(new Date(27, 11, 2015)));
    current = c.addWeeks(2);
    Assert.assertTrue(current.isEquals(new Date(11, 12, 2019)));
  }

  @Test
  public void findingDates() {
    Date current;
    Calendar c = new Calendar(now);
    current = c.getNearestWeekdayMonthDay(Calendar.FRIDAY, 13);
    Assert.assertEquals(current.toString(), "Friday December 13, 2019 AD");
    current = c.getNearestDayMonth(29, Calendar.FEBRUARY);
    Assert.assertEquals(current.toString(), "Saturday February 29, 2020 AD");
  }

  @Test
  public void catchIncorrectDate() {
    try {
      Calendar c = new Calendar(11, 15, 2019);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    ;
    try {
      Calendar c = new Calendar(0, 1, 2019);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    ;
    try {
      Calendar c = new Calendar(15, 1, 0);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    ;
    try {
      Calendar c = new Calendar(1, -1, 2019);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    ;
  }

  @Test
  public void catchIncorrectSet() {
    Calendar c = new Calendar(1, 2, 1);
    try {
      c.setDay(-5);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    ;
    try {
      c.setDay(29);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    ;
    try {
      c.setMonths(0);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    ;
    try {
      c.setMonths(13);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    ;
    try {
      c.setYear(0);
      Assert.fail();
    } catch (IllegalArgumentException ignored) {
    }
    ;
  }
  @Test
  public void catchNullPointer(){
    Calendar c = new Calendar(1, 2, 1);
    try {
      c = new Calendar(null);
      Assert.fail();
    } catch (NullPointerException ignored) {
    }
    ;
    try {
      c.daysBetweenDates(null);
      Assert.fail();
    } catch (NullPointerException ignored) {
    }
    ;
    try {
      Calendar.daysBetweenDates(null, now);
      Assert.fail();
    } catch (NullPointerException ignored) {
    }
    ;
  }
}
