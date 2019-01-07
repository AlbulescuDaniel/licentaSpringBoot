package net.licenta.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import net.licenta.model.dto.HospitalWithSpecializationDTO;

public interface HospitalController {

  @PreAuthorize("hasAnyAuthority('[{authority=ADM}]','[{authority=DOC}]','[{authority=PAT}]','[{authority=PHA}]')")
  public ResponseEntity<Set<HospitalWithSpecializationDTO>> findHospitalByFilters(String city, String specialization);
}
