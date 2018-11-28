package net.licenta.model.dto;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionDTO {
  
  private Long id;
  private Long prescriptionNumber;
  private Long hospitalType;
  private Long patientType;
  private String diagnostic;
  private Integer days;
  private LocalDate datePrescripted;
  private List<PrescriptionDrugDTO> prescriptionDrugsDTO;

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

  public List<PrescriptionDrugDTO> getPrescriptionDrugs() {
    return prescriptionDrugsDTO;
  }

  public void setPrescriptionDrugs(List<PrescriptionDrugDTO> prescriptionDrugsDTO) {
    this.prescriptionDrugsDTO = prescriptionDrugsDTO;
  }

  public Long getPrescriptionNumber() {
    return prescriptionNumber;
  }

  public void setPrescriptionNumber(Long prescriptionNumber) {
    this.prescriptionNumber = prescriptionNumber;
  }

  public Long getHospitalType() {
    return hospitalType;
  }

  public void setHospitalType(Long hospitalType) {
    this.hospitalType = hospitalType;
  }

  public Long getPatientType() {
    return patientType;
  }

  public void setPatientType(Long patientType) {
    this.patientType = patientType;
  }

  public List<PrescriptionDrugDTO> getPrescriptionDrugsDTO() {
    return prescriptionDrugsDTO;
  }

  public void setPrescriptionDrugsDTO(List<PrescriptionDrugDTO> prescriptionDrugsDTO) {
    this.prescriptionDrugsDTO = prescriptionDrugsDTO;
  }
}
