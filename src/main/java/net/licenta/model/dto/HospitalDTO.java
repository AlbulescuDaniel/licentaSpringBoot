package net.licenta.model.dto;

import javax.validation.constraints.NotNull;

public class HospitalDTO {

  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String urc;

  @NotNull
  private String phone;
  
  @NotNull(message = "{address.notNull}")
  private AddressDTO addressDTO;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrc() {
    return urc;
  }

  public void setUrc(String urc) {
    this.urc = urc;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public AddressDTO getAddressDTO() {
    return addressDTO;
  }

  public void setAddressDTO(AddressDTO addressDTO) {
    this.addressDTO = addressDTO;
  }
  
  
}
