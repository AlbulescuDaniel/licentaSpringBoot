package net.licenta.model.dto;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionDetailsDTO {
  private String doctorName;
  private String doctorSpeciality;
  private String doctorEmail;
  private String hospitalName;
  private String hospitalCity;
  private String hospitalEmail;
  private String diagnostic;
  private LocalDate prescriptionDate;
  private List<PrescriptionDrugDTO> prescriptionDrugDTO;

  public PrescriptionDetailsDTO() {
    super();
  }

  public PrescriptionDetailsDTO(String doctorName, String doctorSpeciality, String doctorEmail, String hospitalName, String hospitalCity, String hospitalEmail, String diagnostic,
      LocalDate prescriptionDate, List<PrescriptionDrugDTO> prescriptionDrugDTO) {
    super();
    this.doctorName = doctorName;
    this.doctorSpeciality = doctorSpeciality;
    this.doctorEmail = doctorEmail;
    this.hospitalName = hospitalName;
    this.hospitalCity = hospitalCity;
    this.hospitalEmail = hospitalEmail;
    this.diagnostic = diagnostic;
    this.prescriptionDate = prescriptionDate;
    this.prescriptionDrugDTO = prescriptionDrugDTO;
  }

  public String getDoctorName() {
    return doctorName;
  }

  public void setDoctorName(String doctorName) {
    this.doctorName = doctorName;
  }

  public String getDoctorSpeciality() {
    return doctorSpeciality;
  }

  public void setDoctorSpeciality(String doctorSpeciality) {
    this.doctorSpeciality = doctorSpeciality;
  }

  public String getDoctorEmail() {
    return doctorEmail;
  }

  public void setDoctorEmail(String doctorEmail) {
    this.doctorEmail = doctorEmail;
  }

  public String getHospitalName() {
    return hospitalName;
  }

  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }

  public String getHospitalCity() {
    return hospitalCity;
  }

  public void setHospitalCity(String hospitalCity) {
    this.hospitalCity = hospitalCity;
  }

  public String getHospitalEmail() {
    return hospitalEmail;
  }

  public void setHospitalEmail(String hospitalEmail) {
    this.hospitalEmail = hospitalEmail;
  }

  public String getDiagnostic() {
    return diagnostic;
  }

  public void setDiagnostic(String diagnostic) {
    this.diagnostic = diagnostic;
  }

  public LocalDate getPrescriptionDate() {
    return prescriptionDate;
  }

  public void setPrescriptionDate(LocalDate prescriptionDate) {
    this.prescriptionDate = prescriptionDate;
  }

  public List<PrescriptionDrugDTO> getPrescriptionDrugDTO() {
    return prescriptionDrugDTO;
  }

  public void setPrescriptionDrugDTO(List<PrescriptionDrugDTO> prescriptionDrugDTO) {
    this.prescriptionDrugDTO = prescriptionDrugDTO;
  }
}