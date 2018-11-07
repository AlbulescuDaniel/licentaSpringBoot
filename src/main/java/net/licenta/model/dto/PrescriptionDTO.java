package net.licenta.model.dto;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionDTO {
  
  private Long idPrescription;
  private String diagnostic;
  private Integer days;
  private LocalDate datePrescripted;
  private List<PrescriptionDrugDTO> prescriptionDrugsDTO;

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

  public List<PrescriptionDrugDTO> getPrescriptionDrugs() {
    return prescriptionDrugsDTO;
  }

  public void setPrescriptionDrugs(List<PrescriptionDrugDTO> prescriptionDrugsDTO) {
    this.prescriptionDrugsDTO = prescriptionDrugsDTO;
  }
}
