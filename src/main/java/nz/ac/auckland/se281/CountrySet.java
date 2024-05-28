package nz.ac.auckland.se281;

import java.util.HashSet;
import java.util.Set;

public class CountrySet {
  private Set<C> cSet = new HashSet<C>();

  /**
   * Adds country to CountrySet, stores the country name as a unique country object (C) as the value
   *
   * @param countryObj country object (Object type: C) containing its elements
   */
  public void add(C countryObj) {
    this.cSet.add(countryObj);
  }

  /**
   * Retrieves the country object (C) using its name by iterating through hashset
   *
   * @param key String key to index and get its value
   * @return returns country object of type C
   */
  public C retrieve(String countryName) {
    for (C ele : cSet) {
      if (countryName.equals(ele.getCountry())) {
        return ele;
      }
    }
    return null;
  }
}
