package net.licenta.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import net.licenta.model.entity.UserGender;

public class UserPatientDTO extends UserDTO{
  
  @NotNull(message = "{user.firstName.notNull}")
  @Pattern(regexp = "^\\b(?!.*?\\s{2})[A-Za-z ]{2,64}\\b$", message = "{user.firstName.format}")
  private String firstName;

  @NotNull(message = "{user.lastName.notNull}")
  @Pattern(regexp = "^\\b(?!.*?\\s{2})[A-Za-z ]{2,64}\\b$", message = "{user.lastName.format}")
  private String lastName;

  @NotNull
  private String cnp;
  
  @NotNull
  private LocalDate birthDate;
   
  @NotNull
  private UserGender gender;

  public UserGender getGender() {
    return gender;
  }

  public void setGender(UserGender gender) {
    this.gender = gender;
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
}
