package nz.ac.auckland.se281;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility methods for common tasks.
 *
 * <p>CANNOT CHANGE EXISTING METHODS BUT YOU CAN ADD NEW ONES.
 */
public class Utils {

  public static Scanner scanner = new Scanner(System.in);

  public static List<String> readCountries() {
    return readCsv("./src/main/resources/countries.csv");
  }

  public static List<String> readAdjacencies() {
    return readCsv("./src/main/resources/adjacencies.csv");
  }

  /**
   * read the content of a csv file.
   *
   * @param fileName of the csv file.
   * @return a list of String, where each element is a line in the CSV file.
   */
  private static List<String> readCsv(String fileName) {
    List<String> result = new LinkedList<>();
    // init to empty line
    String line;
    // no need to close the BufferedReader, will be close automaticsally because is inside the try
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      while ((line = br.readLine()) != null) {
        result.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1); // exit the program
    }

    return result;
  }

  /**
   * Capitalise the first letter of each word Example: "hello world" -> "Hello World".
   *
   * @param input the string to process.
   * @return the input string with all words with first letter capitalised.
   */
  public static String capitalizeFirstLetterOfEachWord(String input) {
    if (input == null || input.isEmpty()) {
      return input; // Return the input if it's null or empty
    }

    String[] words = input.split("\\s+"); // Split the string by whitespace
    StringBuilder capitalizedString = new StringBuilder();

    for (String word : words) {
      if (!word.isEmpty()) {
        char firstChar = Character.toUpperCase(word.charAt(0));
        String restOfString = word.length() > 1 ? word.substring(1) : "";
        capitalizedString.append(firstChar).append(restOfString).append(" ");
      }
    }
    // Remove the trailing space
    return capitalizedString.toString().trim();
  }

  /**
   * Accepts a user input from the console and checks if the country inputted is in the country set.
   * If so it prints the country info, else it throws an exception and prints an error message.
   *
   * @param cSet the country set (HashSet)
   * @return the country object if found in the country set
   * @throws CountryNotExistantException If the user's input is not in the country set, it throws an
   *     exception
   */
  public static C countryInputCheck(CountrySet cSet, String option)
      throws CountryNotExistantException {
    C cElements;
    switch (option) {
      case "journey":
        MessageCli.INSERT_SOURCE.printMessage();
      case "info":
        MessageCli.INSERT_COUNTRY.printMessage();
      case "destination":
        MessageCli.INSERT_DESTINATION.printMessage();
    }
    String userInput = Utils.scanner.nextLine();
    userInput = Utils.capitalizeFirstLetterOfEachWord(userInput);
    if (cSet.retrieve(userInput) != null) {
      cElements = cSet.retrieve(userInput);
      MessageCli.COUNTRY_INFO.printMessage(
          cElements.getCountry(), cElements.getContinent(), cElements.getTaxFees());
      return cElements;
    } else {
      throw new CountryNotExistantException(MessageCli.INVALID_COUNTRY.getMessage(userInput));
    }
  }
}
