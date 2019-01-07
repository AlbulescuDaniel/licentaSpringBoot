package net.licenta.service;

import java.util.Optional;
import java.util.Set;

import net.licenta.model.dto.DoctorProfileDTO;
import net.licenta.model.dto.PrescriptionDoctorHospitalDTO;
import net.licenta.model.dto.UserDoctorDTO;

public interface DoctorService {

  public Set<UserDoctorDTO> getAllDoctors();
  
  public Optional<UserDoctorDTO> getDoctorById(Long id);
  
  public Optional<UserDoctorDTO> createDoctor(UserDoctorDTO userPatientDTO);
  
  public Optional<UserDoctorDTO> updateDoctor(Long id, UserDoctorDTO userPatientDTO);
  
  public Boolean deleteDoctorById(Long id);
  
  public Boolean deleteAllDoctors();

  public Optional<PrescriptionDoctorHospitalDTO> getDoctorAndHospitalforAutocomplete(String userName);

  public Optional<DoctorProfileDTO> getDoctorProfile(String userName);
}
