package net.licenta.model.dto;

import javax.validation.constraints.NotNull;

public class RoleDTO {
  
  @NotNull(message = "Role code can nor be null")
  private String code;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
