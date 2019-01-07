package net.licenta.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author daniel.albulescu
 *
 */
@Embeddable
public class Address {

  @Column(name = "COUNTRY_NAME")
  private String countryName;

  @Column(name = "POSTAL_CODE")
  private String postalCode;

  @Column(name = "CITY")
  private String city;

  @Column(name = "REGION")
  private String region;

  @Column(name = "STREET")
  private String street;

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