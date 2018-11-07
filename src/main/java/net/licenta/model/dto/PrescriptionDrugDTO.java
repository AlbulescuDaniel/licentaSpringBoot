package net.licenta.model.dto;

public class PrescriptionDrugDTO {
  
  private Long id;
  private Boolean checked;
  private String description;
  private Integer pillsNumber;
  private PrescriptionDTO prescriptionDTO;
  private DrugDTO drugDTO;

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

  public PrescriptionDTO getPrescriptionDTO() {
    return prescriptionDTO;
  }

  public void setPrescriptionDTO(PrescriptionDTO prescriptionDTO) {
    this.prescriptionDTO = prescriptionDTO;
  }

  public DrugDTO getDrugDTO() {
    return drugDTO;
  }

  public void setDrugDTO(DrugDTO drugDTO) {
    this.drugDTO = drugDTO;
  }
}
