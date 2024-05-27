package nz.ac.auckland.se281;

import java.util.HashMap;

public class CountryMap {
  private HashMap<String, C> cMap = new HashMap<String, C>();

  /**
   * Adds country to CountryMap, stores the country name as a key and the country object (C) as the
   * value
   *
   * @param countryName country name as a String
   * @param countryObj country object (Object type: C) containing its elements
   */
  public void add(String countryName, C countryObj) {
    this.cMap.put(countryName, countryObj);
  }

  /**
   * Retrieves the country object (C) using its key (String)
   *
   * @param key String key to index and get its value
   * @return returns country object of type C
   */
  public C retrieve(String key) {
    return this.cMap.get(key);
  }
}
