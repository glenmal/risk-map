package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
OOP Requirements

You should use LinkedList with the Queue interface at least once in your code. For example:
Queue<SOME_OBJECT> var = new LinkedList<>();

AND

You should use LinkedList or ArrayList with the List interface at least once in your code. For example:
List<SOME_OBJECT> var = new LinkedList<>(); or List<SOME_OBJECT> var = new ArrayList<>();

*/

/** This class is the main entry point. */
public class MapEngine {
  private CountrySet cSet = new CountrySet();
  private AdjacencyMap adjMap = new AdjacencyMap();

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

    // countries list for loop to create country objects and store in country set
    for (String line : adjacencies) {
      LinkedList<String> paths = new LinkedList<String>();
      String[] lineList = line.split(",");
      for (String name : lineList) {
        paths.add(name);
      }
      this.adjMap.add(lineList[0], paths);
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
    List<String> visited = new ArrayList<>();
    Queue<String> queue = new LinkedList<>();
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
    String jName = journey.getCountry();
    String dName = destination.getCountry();

    // Using BFS and Queue interface to search the Adjacency Map
    queue.add(jName);
    visited.add(jName);
    while (!queue.isEmpty()) {
      String countries = queue.poll();
      for (String name : adjMap.get(countries)) {
        if (!visited.contains(name)) {
          visited.add(name);
          queue.add(name);
          if (visited.contains(dName)) {
            break;
          }
        }
      }
    }

    // search through visited starting from the start of the list (closest to root), and take the
    // first country from that list that has a path to the destination and add to seperate list
    // rinse and repeat until you get to the root (starting point) then you will have a list in
    // reverse order of the shortest path from root to the destination
    // make function for this in Utils
    for (String name : visited) {
      if (adjMap.get(dName).contains(name)) {}
    }
  }
}
