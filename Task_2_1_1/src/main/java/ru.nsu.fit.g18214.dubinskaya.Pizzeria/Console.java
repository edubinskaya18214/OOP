package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Console {

  public static void main(String[] args) throws InterruptedException {
    String input;
    while (true) {
      System.out.println("     Input \"1\" if you want to open pizzeria\n" +
          "     Input \"2\" if you want to finish using application\n" +
          "     Input \"3\" if you want to change path to config file\n");
      Scanner scan = new Scanner(System.in);
      input = scan.nextLine();
      switch (input) {
        case ("2"):
          return;
        case ("1"):
          if (!createPizzeria()) return;
          break;
        case("3"):
          alternativePath = takeWayToOpenFile();
          if (alternativePath.equals("stop"))
            return;
          useAlternativePath = true;
          break;
      }
    }
  }

  private static String takeWayToOpenFile() {
    Scanner scan = new Scanner(System.in);
    String out = "     Input path to correct json file\n     Or input \"stop\" to finish using application \n";
    System.out.println(out);
    return scan.nextLine();
  }

  private static final String definePath = "./res/test2.json";
  private static boolean useAlternativePath = false;
  private static String alternativePath = null;
  private static boolean createPizzeria() throws InterruptedException {
    PizzeriaConfig config = createConfig();
    if (config == null)
      return false;
    Pizzeria pizza = new Pizzeria(config);
    pizza.run();
    return true;
  }

  private static PizzeriaConfig createConfig(){
    Gson gson = new Gson();
    String currPath;
    PizzeriaConfig config = null;
    if (useAlternativePath)
      currPath = alternativePath;
    else currPath = definePath;
    boolean error = true;
    while (error) {
      if (!(new File(currPath).exists())) {
        System.out.println("     file not exist");
        error = true;
      } else try (Reader reader = new FileReader(currPath)) {
        config = gson.fromJson(reader, PizzeriaConfig.class);
        error = !config.isCorrect();
        if (error) System.out.println("     config file incorrect");
      } catch (IOException e) {
        System.out.println("     config file incorrect");
        error = true;
      } catch (com.google.gson.JsonSyntaxException e){
        System.out.println("     incorrect syntax in json file");
        error = true;
      }
      if (error) {
        String input = takeWayToOpenFile();
        if (input.equals("stop"))
          return null;
        currPath = input;
        alternativePath = input;
        useAlternativePath = true;
      }
    }
    return config;
  }
}
