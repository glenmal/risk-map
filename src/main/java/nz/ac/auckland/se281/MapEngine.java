package nz.ac.auckland.se281;

import java.util.List;

/*
OOP Requirements

You should use HashSet or LinkedHashSet at least once in your code. For example:
Set<SOME_OBJECT> var = new HashSet<>(); or Set<SOME_OBJECT> var = LinkedHashSet<>();

AND

You should use LinkedList with the Queue interface at least once in your code. For example:
Queue<SOME_OBJECT> var = new LinkedList<>();

AND

You should use LinkedList or ArrayList with the List interface at least once in your code. For example:
List<SOME_OBJECT> var = new LinkedList<>(); or List<SOME_OBJECT> var = new ArrayList<>();

*/

/** This class is the main entry point. */
public class MapEngine {
  private CountrySet cSet = new CountrySet();

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    // countries list for loop to create country objects and store in country set
    for (String line : countries) {
      String[] lineList = line.split(",");
      C country = new C(lineList);
      this.cSet.add(country);
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    C info = null;
    while (info == null) {
      try {
        info = Utils.countryInputCheck(this.cSet, "info");
      } catch (CountryNotExistantException e) {
        System.out.println(e);
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    C journey = null;
    C destination = null;
    // user input for starting point
    while (journey == null) {
      try {
        journey = Utils.countryInputCheck(this.cSet, "journey");
      } catch (CountryNotExistantException e) {
        System.out.println(e);
      }
    }

    // user input for destination
    while (destination == null) {
      try {
        destination = Utils.countryInputCheck(this.cSet, "destination");
      } catch (CountryNotExistantException e) {
        System.out.println(e);
      }
    }
  }
}
