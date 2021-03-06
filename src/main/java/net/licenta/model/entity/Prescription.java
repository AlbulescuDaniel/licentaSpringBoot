package net.licenta.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Prescription extends EntityAudit {
  @Id
  @Column(name = "ID_PRESCRIPTION")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PRESCRIPTION_NUMBER")
  private Long prescriptionNumber;
  
  @Column(name = "HOSPITAL_TYPE")
  private String hospitalType;
  
  @Column(name = "PATIENT_TYPE")
  private String patientType;

  @Column(name = "DIAGNOSTIC")
  private String diagnostic;

  @Column(name = "DATE_PRESCRIPTED")
  private LocalDate datePrescripted;
  
  @Column(name = "USER_GENDER")
  private UserGender userGender;
  
  @Column(name = "NATIONALITY")
  private String nationality;

  @OneToMany(mappedBy = "prescription", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<PrescriptionDrug> prescriptionDrugs;

  @ManyToOne(cascade = CascadeType.DETACH)
  @JoinColumn(name = "ID_PATIENT")
  private UserPatient patient;

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("diagnostic", diagnostic).append("datePrescripted", datePrescripted).append("prescriptionDrugs", prescriptionDrugs)
        .append("patient", patient).toString();
  }

  public Prescription() {
    super();
    prescriptionDrugs = new ArrayList<>();
    datePrescripted = LocalDate.now();
  }

  public UserGender getUserGender() {
    return userGender;
  }

  public void setUserGender(UserGender userGender) {
    this.userGender = userGender;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public Long getPrescriptionNumber() {
    return prescriptionNumber;
  }

  public void setPrescriptionNumber(Long prescriptionNumber) {
    this.prescriptionNumber = prescriptionNumber;
  }

  public String getHospitalType() {
    return hospitalType;
  }

  public void setHospitalType(String hospitalType) {
    this.hospitalType = hospitalType;
  }

  public String getPatientType() {
    return patientType;
  }

  public void setPatientType(String patientType) {
    this.patientType = patientType;
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
