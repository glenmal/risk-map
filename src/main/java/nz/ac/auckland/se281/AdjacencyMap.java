package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.LinkedList;

public class AdjacencyMap {
  private HashMap<String, LinkedList<String>> adjMap = new HashMap<String, LinkedList<String>>();

  /**
   * Adds countryName and its relationships to object, stores the country name as a String and
   * country relationships as unique country objects (C)
   *
   * @param countryObj country object (Object type: C) containing its elements
   */
  public void add(String name, LinkedList<String> paths) {
    paths.removeFirst();
    this.adjMap.put(name, paths);
  }

  public LinkedList<String> get(String name) {
    return adjMap.get(name);
  }
}
