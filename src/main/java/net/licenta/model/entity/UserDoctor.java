package net.licenta.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author daniel.albulescu
 *
 */
@Entity
@Table(name = "T_DOCTORS")
public class UserDoctor extends User {

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @JoinColumn(name = "ID_HOSPITAL")
  @ManyToOne(cascade  = CascadeType.DETACH)
  private Hospital hospital;

  public UserDoctor() {
    super();
    super.setRoleType(RoleType.DOC);
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

  public Hospital getHospital() {
    return hospital;
  }

  public void setHospital(Hospital hospital) {
    this.hospital = hospital;
  }
}
