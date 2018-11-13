package net.licenta.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Table(name = "T_DRUG")
@Entity
public class Drug {

  @Id
  @Column(name = "ID_DRUG")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "MANUFACTURER")
  private String manufacturer;

  @Column(name = "ACTIVE_COMPONENT")
  private String activeComponent;

  @OneToMany(mappedBy = "drug")
  private List<PrescriptionDrug> prescriptionDrugs;

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("name", name).append("description", description).append("manufacturer", manufacturer).append("activeComponent", activeComponent).toString();
  }

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getActiveComponent() {
    return activeComponent;
  }

  public void setActiveComponent(String activeComponent) {
    this.activeComponent = activeComponent;
  }
}
