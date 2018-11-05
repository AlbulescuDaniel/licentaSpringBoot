package net.licenta.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author daniel.albulescu
 *
 */
@Embeddable
public class Address {

  // @Pattern(regexp ="^\\b(?!.*?\\s{2})[A-Za-z ]{2,100}\\b$", message ="{address.countryName.format}")
  // @NotNull(message = "{address.countryName.notNull}")
  @Column(name = "COUNTRY_NAME")
  private String countryName;

  // @Pattern(regexp = "^\\d{5}$|^\\d{5}$|^\\d{5}(-\\d{4})?$", message = "{address.postalCode.format}")
  // @NotNull(message = "{address.postalCode.notNull}")
  @Column(name = "POSTAL_CODE")
  private String postalCode;

  // @Pattern(regexp = "^\\b(?!.*?\\s{2})[A-Za-z ]{2,64}\\b$", message = "{address.city.format}")
  // @NotNull(message = "{address.city.notNull}")
  @Column(name = "CITY")
  private String city;

  // @Pattern(regexp = "^\\b(?!.*?\\s{2})[A-Za-z ]{2,64}\\b$", message = "{address.region.format}")
  // @NotNull(message = "{address.region.notNull}")
  @Column(name = "REGION")
  private String region;

  // @Pattern(regexp = "^\\b(?!.*?\\s{2})[A-Za-z0-9 ]{2,64}\\b$", message = "{address.street.format}")
  // @NotNull(message = "{address.street.notNull}")
  @Column(name = "STREET")
  private String street;

  // @Pattern(regexp = "^[A-Za-z0-9]{1,5}$", message = "{address.streetNumber.format}")
  // @NotNull(message = "{address.streetNumber.notNull}")
  @Column(name = "STREET_NUMBER")
  private String streetNumber;

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String town) {
    this.region = town;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }
}