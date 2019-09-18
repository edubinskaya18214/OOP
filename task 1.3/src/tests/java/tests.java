import org.junit.Assert;
import org.junit.Test;

public class tests {
    private zFunction gen = new zFunction();
    @Test
    public void test1() {
        String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
        String sub = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
        int[] checkArr = {0};
        Assert.assertArrayEquals(checkArr, gen.returnNumSubs(s,sub));
        System.out.println("test 1 is successful");
    }
    @Test
    public void test2(){
                  //0123456789012345678901234567890123456789012345678901234567
        String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
        String sub = "яблоки";
        int[] checkArr = {18};
        Assert.assertArrayEquals(checkArr, gen.returnNumSubs(s,sub));
        System.out.println("test 2 is successful");
    }
    @Test
    public void test3(){
                  //0123456789012345678901234567890123456789012345678901234567
        String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
        String sub = "Карл у Клары украл кораллы, а Клара у Карла украла Кларнет, жалко мне клару, как она будет без кораллов, хоспаде, что я пишу";
        int[] checkArr = {};
        Assert.assertArrayEquals(checkArr, gen.returnNumSubs(s,sub));
        System.out.println("test 3 is successful");
    }
    @Test
    public void test4(){
                  //0123456789012345678901234567890123456789012345678901234567
        String s = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют";
        String sub = "Я, конечно, люблю яблоки, но я думаю, они меня преследуют. Хотя, наверное, неплохо иметь поклонников";
        int[] checkArr = {};
        Assert.assertArrayEquals(checkArr, gen.returnNumSubs(s,sub));
        System.out.println("test 4 is successful");
    }
    @Test
    public void test5(){
                  //0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
        String s = "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
                "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
                "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
                "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
                "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
                "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа";
        String sub = "а";
        int[] checkArr = new int[600];
        for (int i = 0; i < 600; ++i)
            checkArr[i] = i;
        Assert.assertArrayEquals(checkArr, gen.returnNumSubs(s,sub));
        System.out.println("test 5 is successful");
    }
}