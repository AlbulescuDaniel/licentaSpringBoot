package net.licenta.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.licenta.model.dto.UserPharmacyDTO;

public interface PharmacyController {

  public ResponseEntity<Set<UserPharmacyDTO>> getAllPharmacies();
  
  public ResponseEntity<UserPharmacyDTO> getPharmacyById(Long id);

  public ResponseEntity<UserPharmacyDTO> createPharmacy(UserPharmacyDTO userPharmacyDTO);

  public ResponseEntity<UserPharmacyDTO> updatePharmacyById(Long id, UserPharmacyDTO userPharmacyDTO);
  
  public ResponseEntity<HttpStatus> deletePharmacyById(Long id);

  public ResponseEntity<HttpStatus> deleteAllPharmacies();
}