package ru.nsu.fit.g18214.dubinskaya;

import org.junit.Assert;
import org.junit.Test;

public class DateTests {
  @Test
  public void CorrectCountingDaysAndCorrectOutput(){
    Date now = new Date(27, 11, 2019);
    Date myBirthday = new Date( 17, 3, 2001);
    Assert.assertEquals("Saturday March 17, 2001 AD", myBirthday.toString());
    Assert.assertTrue(now.isAfter(myBirthday));
    }
   @Test
  public void CorrectDataSavingFromDays(){
    Date now = new Date(27, 11, 2019);
    Date isNow = new Date(now.getDaysFromEraBeginning());
    Assert.assertEquals(isNow.getDaysFromEraBeginning(), Date.countDays(isNow.getDay(), isNow.getMonth(), isNow.getYear()));
    Date past = new Date(1);
    Assert.assertEquals(1,  Date.countDays(past.getDay(), past.getMonth(), past.getYear()));
   }
   @Test
   public void AfterBeforeEqualsTest(){
    Date checked = new Date(10000);
    Date after = new Date(10001);
    Date before = new Date(checked.getDay() - 1, checked.getMonth(), checked.getYear());
    Date equals = new Date(10000);
    Assert.assertTrue(checked.isAfter(before));
    Assert.assertTrue(checked.isBefore(after));
    Assert.assertTrue(checked.isEquals(equals));
   }

   @Test
  public void CorrectCountingDaysWeek(){
    Date d1 = new Date(1);
    Assert.assertEquals(Calendar.WEEKDAY[0], d1.weekDayToString());
    Date today = new Date(28, 11, 2019);
    Assert.assertEquals(Calendar.WEEKDAY[3], today.weekDayToString());
    Date happyNewYear = new Date(1, 1, 2020);
    Assert.assertEquals(Calendar.WEEKDAY[2], happyNewYear.weekDayToString());
   }
   @Test
  public void CatchExceptions(){
     Date check = new Date(11,11,1990);
     try{
       check.isAfter(null);
       Assert.fail();
     }catch(NullPointerException ignored){};
     try{
       check.isBefore(null);
       Assert.fail();
     }catch(NullPointerException ignored){};
     try{
       check.isEquals(null);
       Assert.fail();
     }catch(NullPointerException ignored){};
     try{
       Date c = new Date(11,15,2019);
       Assert.fail();
     }catch(IllegalArgumentException ignored){};
     try{
       Date c = new Date(0,1,2019);
       Assert.fail();
     }catch(IllegalArgumentException ignored){};
     try{
       Date c = new Date(15,1,0);
       Assert.fail();
     }catch(IllegalArgumentException ignored){};
   }

}
