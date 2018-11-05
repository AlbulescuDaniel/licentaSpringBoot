package net.licenta.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserPatientDTO extends UserDTO{
  
  @NotNull(message = "{user.firstName.notNull}")
  @Pattern(regexp = "^\\b(?!.*?\\s{2})[A-Za-z ]{2,64}\\b$", message = "{user.firstName.format}")
  private String firstName;

  @NotNull(message = "{user.lastName.notNull}")
  @Pattern(regexp = "^\\b(?!.*?\\s{2})[A-Za-z ]{2,64}\\b$", message = "{user.lastName.format}")
  private String lastName;

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
