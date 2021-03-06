package net.licenta.controller;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import net.licenta.model.dto.PrescriptionDTO;
import net.licenta.model.dto.PrescriptionDetailsDTO;
import net.licenta.model.dto.PrescriptionWithPatientNameDTO;

public interface PrescriptionController {

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<Set<PrescriptionDTO>> getAllPrescriptions();
  
  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]','[{authority=PAT}]','[{authority=PHA}]')")
  public ResponseEntity<PrescriptionDTO> getPrescriptionById(Long id);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]')")
  public ResponseEntity<PrescriptionDTO> createPrescription(PrescriptionDTO prescriptionDTO, String firstName, String lastName);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]')")
  public ResponseEntity<PrescriptionDTO> updatePrescription(Long id, PrescriptionDTO prescriptionDTO);
  
  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]')")
  public ResponseEntity<HttpStatus> deletePrescriptionById(Long id);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<HttpStatus> deleteAllPrescriptions();
  
  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]','[{authority=PAT}]')")
  public ResponseEntity<Set<PrescriptionWithPatientNameDTO>> getPatientPrescriptionsByPatientNameAndDateBetwwen(String firstName, String lastName, LocalDate startDate, LocalDate endDate);
  
  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]','[{authority=PAT}]')")
  public ResponseEntity<Set<PrescriptionDTO>> getPatientPrescriptionsByPatientUserNameAndDateBetwwen(String userName, LocalDate startDate, LocalDate endDate);
  
  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]','[{authority=PAT}]','[{authority=PHA}]')")
  public ResponseEntity<PrescriptionDetailsDTO> getPrescriptionDetails(Long id);
}