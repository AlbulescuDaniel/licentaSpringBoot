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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "T_PRESCRIPTION")
public class Prescription extends EntityAudit{
  @Id
  @Column(name = "ID_PRESCRIPTION")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "DIAGNOSTIC")
  private String diagnostic;

  @Column(name = "DAYS")
  private Integer days;

  @Column(name = "DATE_PRESCRIPTED")
  private LocalDate datePrescripted;

  @OneToMany(mappedBy = "prescription", fetch = FetchType.EAGER)
  private List<PrescriptionDrug> prescriptionDrugs;

  @ManyToOne
  @JoinColumn(name = "ID_PATIENT")
  private UserPatient patient;

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("diagnostic", diagnostic).append("days", days).append("datePrescripted", datePrescripted).append("prescriptionDrugs", prescriptionDrugs)
        .append("patient", patient).toString();
  }

  public Prescription() {
    super();
    prescriptionDrugs = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long idPrescription) {
    this.id = idPrescription;
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

  public UserPatient getPatient() {
    return patient;
  }

  public void setPatient(UserPatient patient) {
    this.patient = patient;
  }
}
