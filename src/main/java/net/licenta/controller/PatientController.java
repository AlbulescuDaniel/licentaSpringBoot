package net.licenta.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import net.licenta.model.dto.UserPatientDTO;

public interface PatientController {

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<Set<UserPatientDTO>> getAllPatients();

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]','[{authority=PAT}]','[{authority=PHA}]')")
  public ResponseEntity<UserPatientDTO> getPatientById(Long id);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]')")
  public ResponseEntity<UserPatientDTO> createPatient(UserPatientDTO userDTO);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]')")
  public ResponseEntity<UserPatientDTO> updatePatientById(Long id, UserPatientDTO userDTO);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]')")
  public ResponseEntity<HttpStatus> deletePatientById(Long id);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<HttpStatus> deleteAllPatients();
}