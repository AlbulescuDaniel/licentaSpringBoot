package net.licenta.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

public class UserDTO {
  private Long id;

  @NotNull(message = "{user.userName.notNull}")
  @Pattern(regexp = "^[a-z0-9_.]{3,25}$", message = "{user.userName.format}")
  private String userName;

  @Email(message = "{user.email.format}")
  @NotNull(message = "{user.email.notNull}")
  private String email;

  @Pattern(regexp = "^\\+?(\\d[\\s\\- _]?){3,15}$", message = "{user.phone.format}")
  @NotNull(message = "{user.phone.notNull}")
  private String phoneNumber;

  @NotNull(message = "{user.password.notNull}")
  @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\w+)(?=\\S+$).{8,32}$", message = "{user.password.format}", flags = Flag.UNICODE_CASE)
  private String password;

  @NotNull(message = "{address.notNull}")
  private AddressDTO addressDTO;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public AddressDTO getAddressDTO() {
    return addressDTO;
  }

  public void setAddressDTO(AddressDTO addressDTO) {
    this.addressDTO = addressDTO;
  }
}
