package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    String fastLaneStr = "[";
    List<String> fastLane = new LinkedList<>();
    List<String> visited = new ArrayList<>();
    Queue<String> queue = new LinkedList<>();
    HashMap<String, String> parentMap = new HashMap<String, String>();
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

    if (jName.equals(dName)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    }

    // Using BFS and Queue interface to search the Adjacency Map
    queue.add(jName);
    visited.add(jName);
    while (!queue.isEmpty()) {
      String countries = queue.poll();
      if (dName.equals(jName)) {
        break;
      }
      for (String name : adjMap.get(countries)) {
        if (!visited.contains(name)) {
          // saves parent of node (name) for tracing back
          parentMap.put(name, countries);
          visited.add(name);
          queue.add(name);
        }
      }
    }

    // gets the shortest path by tracing back using parent maps
    fastLane = Utils.fastLane(dName, parentMap, jName);
    ArrayList<String> contList = new ArrayList<String>();
    Integer taxSum = -Integer.parseInt(cSet.retrieve(jName).getTaxFees());
    for (String country : fastLane) {
      C counObject = cSet.retrieve(country);
      taxSum += Integer.parseInt(counObject.getTaxFees());
      if (!contList.contains(counObject.getContinent())) {
        contList.add(counObject.getContinent());
      }

      if (country.equals(dName)) {
        fastLaneStr = fastLaneStr + country + "]";
      } else {
        fastLaneStr = fastLaneStr + country + ", ";
      }
    }
    String contStr = "[";
    int c = 0;
    for (String cont : contList) {
      c++;
      if (c == contList.size()) {
        contStr = contStr + cont + "]";
      } else {
        contStr = contStr + cont + ", ";
      }
    }
    MessageCli.ROUTE_INFO.printMessage(fastLaneStr);
    MessageCli.CONTINENT_INFO.printMessage(contStr);
    MessageCli.TAX_INFO.printMessage(String.valueOf(taxSum));
  }
}
