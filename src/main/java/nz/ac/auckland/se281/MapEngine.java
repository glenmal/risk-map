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

AND

When the user types an incorrect country name (i.e., a country not present in the file),
your code must throw a custom exception that you created.
Another part of your code should catch the exception and recover from the issue
by asking the user to insert another country name.

*/

/** This class is the main entry point. */
public class MapEngine {
  private CountryMap cMap = new CountryMap();

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    // countries list for loop to create country objects and store in country map
    for (String line : countries) {
      String[] lineList = line.split(",");
      C country = new C(lineList);
      this.cMap.add(lineList[0], country);
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    Boolean exists = false;
    while (!exists) {
      try {
        exists = Utils.countryInputCheck(this.cMap);
      } catch (CountryNotExistantException e) {
        System.out.println(e);
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
