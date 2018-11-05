package net.licenta.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserPharmacyDTO extends UserDTO{

  @NotNull(message = "{user.firstName.notNull}")
  @Pattern(regexp = "^\\b(?!.*?\\s{2})[A-Za-z ]{2,64}\\b$", message = "pharmacy forst name (n. i.)")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
