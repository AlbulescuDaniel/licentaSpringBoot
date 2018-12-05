package net.licenta.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import net.licenta.model.entity.UserGender;

public class CreatePatientDTO {

  @NotNull
  private String email;

  @NotNull
  private String phoneNumber;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String cnp;

  @NotNull
  private LocalDate birthDate;

  @NotNull
  private UserGender gender;

  @NotNull
  private AddressDTO address;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCnp() {
    return cnp;
  }

  public void setCnp(String cnp) {
    this.cnp = cnp;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public UserGender getGender() {
    return gender;
  }

  public void setGender(UserGender gender) {
    this.gender = gender;
  }

  public AddressDTO getAddress() {
    return address;
  }

  public void setAddress(AddressDTO address) {
    this.address = address;
  }
}
