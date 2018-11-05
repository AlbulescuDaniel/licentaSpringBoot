package net.licenta.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author daniel.albulescu
 *
 */
@Entity
@Table(name = "T_PHARMACIES")
public class UserPharmacy extends User{

  @Column(name = "NAME")
  private String name;

  public UserPharmacy() {
    super();
    super.setRoleType(RoleType.PHA);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
