import java.io.*;
import java.util.Scanner;

public class findSubStr {

    findSubStr(String s, String sub){
        zFunction zf = new zFunction();
        int[] arr = zf.returnNumSubs(s, sub);
        for(int i = 0; i < arr.length; ++i){
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args){
        String str, substr;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        substr = scanner.nextLine();
        findSubStr f = new findSubStr(str, substr);
    }
}
