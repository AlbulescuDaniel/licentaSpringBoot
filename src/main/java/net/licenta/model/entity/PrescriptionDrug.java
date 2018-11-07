package net.licenta.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "T_PRESCRIPTION_DRUG")
@Entity
public class PrescriptionDrug {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "checked")
  private Boolean checked;

  @Column(name = "description")
  private String description;

  @Column(name = "pillsNumber")
  private Integer pillsNumber;

  @ManyToOne
  @JoinColumn(name = "ID_PRESCRIPTION")
  private Prescription prescription;

  @ManyToOne
  @JoinColumn(name = "ID_DRUG")
  private Drug drug;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getChecked() {
    return checked;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPillsNumber() {
    return pillsNumber;
  }

  public void setPillsNumber(Integer pillsNumber) {
    this.pillsNumber = pillsNumber;
  }

  public Prescription getPrescription() {
    return prescription;
  }

  public void setPrescription(Prescription prescription) {
    this.prescription = prescription;
  }

  public Drug getDrug() {
    return drug;
  }

  public void setDrug(Drug drug) {
    this.drug = drug;
  }
}