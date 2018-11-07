package net.licenta.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import net.licenta.model.dto.UserPharmacyDTO;

public interface PharmacyController {

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<Set<UserPharmacyDTO>> getAllPharmacies();

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]','[{authority=PAT}]','[{authority=PHA}]')")
  public ResponseEntity<UserPharmacyDTO> getPharmacyById(Long id);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=PHA}]')")
  public ResponseEntity<UserPharmacyDTO> createPharmacy(UserPharmacyDTO userPharmacyDTO);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=PHA}]')")
  public ResponseEntity<UserPharmacyDTO> updatePharmacyById(Long id, UserPharmacyDTO userPharmacyDTO);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<HttpStatus> deletePharmacyById(Long id);

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]')")
  public ResponseEntity<HttpStatus> deleteAllPharmacies();
}