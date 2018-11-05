package net.licenta.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class UserDTO implements Comparable<UserDTO> {
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
  
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof UserDTO)) {
      return false;
    }
    UserDTO castOther = (UserDTO)other;
    return new EqualsBuilder().append(id, castOther.id).append(userName, castOther.userName).append(email, castOther.email).append(phoneNumber, castOther.phoneNumber)
        .append(password, castOther.password).append(addressDTO, castOther.addressDTO).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(id).append(userName).append(email).append(phoneNumber).append(password).append(addressDTO).toHashCode();
  }

  @Override
  public int compareTo(final UserDTO other) {
    return new CompareToBuilder().append(userName, other.userName).append(email, other.email).append(phoneNumber, other.phoneNumber).append(password, other.password)
        .append(addressDTO, other.addressDTO).toComparison();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("userName", userName).append("email", email).append("phoneNumber", phoneNumber).append("password", password).append("addressDTO", addressDTO).toString();
  }

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
