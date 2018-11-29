package net.licenta.model.dto;

import javax.validation.constraints.NotNull;

public class PrescriptionDoctorHospitalDTO {

  @NotNull
  private Long prescriptionNumber;

  @NotNull
  private String hospitalName;

  @NotNull
  private String hospitalURC;

  @NotNull
  private String hospitalState;

  @NotNull
  private String hospitalPhone;

  @NotNull
  private String doctorEmail;

  public Long getPrescriptionNumber() {
    return prescriptionNumber;
  }

  public void setPrescriptionNumber(Long prescriptionNumber) {
    this.prescriptionNumber = prescriptionNumber;
  }

  public String getHospitalName() {
    return hospitalName;
  }

  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }

  public String getHospitalURC() {
    return hospitalURC;
  }

  public void setHospitalURC(String hospitalURC) {
    this.hospitalURC = hospitalURC;
  }

  public String getHospitalState() {
    return hospitalState;
  }

  public void setHospitalState(String hospitalState) {
    this.hospitalState = hospitalState;
  }

  public String getHospitalPhone() {
    return hospitalPhone;
  }

  public void setHospitalPhone(String hospitalPhone) {
    this.hospitalPhone = hospitalPhone;
  }

  public String getDoctorEmail() {
    return doctorEmail;
  }

  public void setDoctorEmail(String doctorEmail) {
    this.doctorEmail = doctorEmail;
  }
}
