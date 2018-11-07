package net.licenta.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_PRESCRIPTION")
public class Prescription {
  @Id
  @Column(name = "ID_PRESCRIPTION")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPrescription;

  @Column(name = "DIAGNOSTIC")
  private String diagnostic;
  
  @Column(name = "DAYS")
  private Integer days;
  
  @Column(name = "DATE_PRESCRIPTED")
  private LocalDate datePrescripted;

  @OneToMany(mappedBy = "prescription", fetch = FetchType.EAGER)
  private List<PrescriptionDrug> prescriptionDrugs;
  
  public Prescription() {
    super();
    prescriptionDrugs = new ArrayList<>();
  }

  public Long getIdPrescription() {
    return idPrescription;
  }

  public void setIdPrescription(Long idPrescription) {
    this.idPrescription = idPrescription;
  }

  public String getDiagnostic() {
    return diagnostic;
  }

  public void setDiagnostic(String diagnostic) {
    this.diagnostic = diagnostic;
  }

  public Integer getDays() {
    return days;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  public LocalDate getDatePrescripted() {
    return datePrescripted;
  }

  public void setDatePrescripted(LocalDate datePrescripted) {
    this.datePrescripted = datePrescripted;
  }

  public List<PrescriptionDrug> getPrescriptionDrugs() {
    return prescriptionDrugs;
  }

  public void setPrescriptionDrugs(List<PrescriptionDrug> prescriptionDrugs) {
    this.prescriptionDrugs = prescriptionDrugs;
  }
}
