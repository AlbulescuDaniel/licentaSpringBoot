package net.licenta.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PrescriptionDrugDTO {
  
  private Long id;
  private String description;
  private Integer pillsNumber;
  @JsonIgnore
  private PrescriptionDTO prescriptionDTO;
  private String drug;
  private Integer days;

  public Long getId() {
    return id;
  }

  public Integer getDays() {
    return days;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  public void setId(Long id) {
    this.id = id;
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

  public PrescriptionDTO getPrescriptionDTO() {
    return prescriptionDTO;
  }

  public void setPrescriptionDTO(PrescriptionDTO prescriptionDTO) {
    this.prescriptionDTO = prescriptionDTO;
  }

  public String getDrug() {
    return drug;
  }

  public void setDrug(String drug) {
    this.drug = drug;
  }

  @Override
  public String toString() {
    return "PrescriptionDrugDTO [id=" + id + ", description=" + description + ", pillsNumber=" + pillsNumber + ", prescriptionDTO=" + prescriptionDTO + ", drug=" + drug + ", days=" + days + "]";
  }
}
