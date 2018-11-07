package net.licenta.model.dto;

import java.util.List;

public class DrugDTO {
  
  private Long id;
  private String name;
  private String description;
  private String manufacturer;
  private String activeComponent;
  private List<PrescriptionDrugDTO> prescriptionDrugsDTO;

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

  public List<PrescriptionDrugDTO> getPrescriptionDrugsDTO() {
    return prescriptionDrugsDTO;
  }

  public void setPrescriptionDrugsDTO(List<PrescriptionDrugDTO> prescriptionDrugsDTO) {
    this.prescriptionDrugsDTO = prescriptionDrugsDTO;
  }
}
