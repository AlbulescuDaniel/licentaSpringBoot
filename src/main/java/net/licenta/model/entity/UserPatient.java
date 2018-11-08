package net.licenta.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author daniel.albulescu
 *
 */
@Entity
@Table(name = "T_PATIENTS")
public class UserPatient extends User {

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @OneToMany(mappedBy = "patient")
  private List<Prescription> prescriptions;

  public UserPatient() {
    super();
    super.setRoleType(RoleType.PAT);
    prescriptions = new ArrayList<>();
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

  public List<Prescription> getPrescriptions() {
    return prescriptions;
  }

  public void setPrescriptions(List<Prescription> prescriptions) {
    this.prescriptions = prescriptions;
  }

}
