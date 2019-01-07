package net.licenta.service;

import java.util.Optional;
import java.util.Set;

import net.licenta.model.dto.CreatePatientDTO;
import net.licenta.model.dto.UserPatientDTO;

public interface PatientService {

  public Set<UserPatientDTO> getAllPatients();
  
  public Optional<UserPatientDTO> getPatientById(Long id);
  
  public Optional<UserPatientDTO> createPatient(CreatePatientDTO userPatientDTO);
  
  public Optional<UserPatientDTO> updatePatient(Long id, UserPatientDTO userPatientDTO);
  
  public Boolean deletePatientById(Long id);
  
  public Boolean deleteAllPatients();

  public Optional<UserPatientDTO> getPatientByUsername(String userName);
}
