package nz.ac.auckland.se281;

public class C {
  private String country = "";
  private String continent = "";
  private String taxFees = "";

  /**
   * Constructor that sets the value for this country's
   *
   * @param csvLine the csv line that is used to set values for this country's elements
   */
  public C(String[] csvLine) {
    this.country = csvLine[0];
    this.continent = csvLine[1];
    this.taxFees = csvLine[2];
  }

  /**
   * returns the country name for this C object as a String
   *
   * @return Country name (String)
   */
  public String getCountry() {
    return this.country;
  }

  /**
   * returns the continent name for this C object as a String
   *
   * @return Continent name (String)
   */
  public String getContinent() {
    return this.continent;
  }

  /**
   * returns the Tax Fees for this C object as an Integer
   *
   * @return Tax Fees (Integer)
   */
  public String getTaxFees() {
    return this.taxFees;
  }
}
