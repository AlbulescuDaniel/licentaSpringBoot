package net.licenta.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.licenta.model.dto.UserPatientDTO;

public interface PatientController {

  public ResponseEntity<Set<UserPatientDTO>> getAllPatients();
  
  public ResponseEntity<UserPatientDTO> getPatientById(Long id);

  public ResponseEntity<UserPatientDTO> createPatient(UserPatientDTO userDTO);

  public ResponseEntity<UserPatientDTO> updatePatientById(Long id, UserPatientDTO userDTO);
  
  public ResponseEntity<HttpStatus> deletePatientById(Long id);

  public ResponseEntity<HttpStatus> deleteAllPatients();
}