package net.licenta.model.dto;

import javax.validation.constraints.NotNull;

public class SpecializationDTO {

  @NotNull
  private String name;

  public SpecializationDTO(@NotNull String name) {
    super();
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
}
