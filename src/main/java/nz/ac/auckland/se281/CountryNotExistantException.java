package nz.ac.auckland.se281;

/**
 * Exception thrown when a country doesn't exist in a given country map when searched for based on
 * its name
 */
public class CountryNotExistantException extends Exception {

  /**
   * Constructor for CountryNotExistantException and stores an error message to be then printed
   *
   * @param s error message string
   */
  public CountryNotExistantException(String s) {
    super(s);
  }
}
