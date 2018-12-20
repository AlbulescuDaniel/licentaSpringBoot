package net.licenta.model.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import net.licenta.model.entity.UserGender;

public class PrescriptionWithPatientNameDTO {
  
  private Long id;
  private Long prescriptionNumber;
  private String hospitalType;
  private String patientType;
  private String diagnostic;
  private LocalDate datePrescripted;
  private UserGender userGender;
  private String nationality;
  private String patientFirstName;
  private String patientLastName;
  private List<PrescriptionDrugDTO> prescriptionDrugsDTO;
  private LocalDate birthDate;

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
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

  public List<PrescriptionDrugDTO> getPrescriptionDrugs() {
    return prescriptionDrugsDTO;
  }

  public void setPrescriptionDrugs(List<PrescriptionDrugDTO> prescriptionDrugsDTO) {
    this.prescriptionDrugsDTO = prescriptionDrugsDTO;
  }

  public String getPatientFirstName() {
    return patientFirstName;
  }

  public void setPatientFirstName(String patientFirstName) {
    this.patientFirstName = patientFirstName;
  }

  public String getPatientLastName() {
    return patientLastName;
  }

  public void setPatientLastName(String patientLastName) {
    this.patientLastName = patientLastName;
  }

  @Override
  public String toString() {
    return "PrescriptionWithPatientNameDTO [id=" + id + ", prescriptionNumber=" + prescriptionNumber + ", hospitalType=" + hospitalType + ", patientType=" + patientType + ", diagnostic=" + diagnostic
        + ", datePrescripted=" + datePrescripted + ", userGender=" + userGender + ", nationality=" + nationality + ", patientFirstName=" + patientFirstName + ", patientLastName=" + patientLastName
        + ", prescriptionDrugsDTO=" + prescriptionDrugsDTO + ", birthDate=" + birthDate + "]";
  }
}
