package net.licenta.model.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

public class HospitalWithSpecializationDTO {

  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String urc;

  @NotNull
  private String phone;
  
  @NotNull(message = "{address.notNull}")
  private AddressDTO addressDTO;
  
  @NotNull
  private String webSite;
  
  @NotNull
  private String email;
  
  @NotNull
  private Set<SpecializationDTO> specializationDTOs = new HashSet<>();

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

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<SpecializationDTO> getSpecializationDTOs() {
    return specializationDTOs;
  }

  public void setSpecializationDTOs(Set<SpecializationDTO> specializationDTOs) {
    this.specializationDTOs = specializationDTOs;
  }
}
