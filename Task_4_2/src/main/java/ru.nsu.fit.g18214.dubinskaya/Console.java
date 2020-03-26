package ru.nsu.fit.g18214.dubinskaya;

import java.util.Scanner;

public class Console {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Calculator c = new Calculator();
    String get = "";
    String help = "   \"calc your_expression\" to calculate your_expression.\n" +
        "   for example: \" calc + 58 * 7 sin 3\"\n" +
        "   \"save_ans on/off\" to turn on/off saving answers\n" +
        "   \"help\" to print this message again\n" +
        "   \"stop\" to finish\n";
    System.out.println(help);
    while (!get.equals("stop")) {
      get = scan.next();
      switch (get) {
        case "stop":
          return;
        case "calc":
          get = scan.nextLine();
          try {
            double res = c.calculate(get);
            System.out.println(res);
          } catch (IllegalArgumentException e) {
            System.out.println("Incorrect input: calc " + get);
          }
          break;
        case "help":
          System.out.println(help);
          break;
        case "save_ans":
          get = scan.next();
          if (get.equals("on")) c.setSavingAnswers(true);
          else if (get.equals("off")) c.setSavingAnswers(false);
          else System.out.println("Incorrect input: save_ans " + get);
          break;
        default:
          System.out.println("Incorrect input:  " + get);
          System.out.println("Print \"help\" to get help");
      }
    }
  }
}
