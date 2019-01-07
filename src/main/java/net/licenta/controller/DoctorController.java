package net.licenta.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import net.licenta.model.dto.DoctorProfileDTO;
import net.licenta.model.dto.PrescriptionDoctorHospitalDTO;
import net.licenta.model.dto.UserDoctorDTO;

public interface DoctorController {

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<Set<UserDoctorDTO>> getAllDoctors();
  
  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]','[{authority=PAT}]')")
  public ResponseEntity<UserDoctorDTO> getDoctorById(Long id);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<UserDoctorDTO> createDoctor(UserDoctorDTO userDTO);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]')")
  public ResponseEntity<UserDoctorDTO> updateDoctorById(Long id, UserDoctorDTO userDTO);
  
  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<HttpStatus> deleteDoctorById(Long id);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<HttpStatus> deleteAllDoctors();
  
  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]')")
  public ResponseEntity<PrescriptionDoctorHospitalDTO> getDoctorAndHospitalforAutocomplete(String userName);
  
  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]')")
  public ResponseEntity<DoctorProfileDTO> getDoctorProfile(String userName);
}