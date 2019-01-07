package net.licenta.service;

import java.util.Set;

import net.licenta.model.dto.HospitalWithSpecializationDTO;

public interface HospitalService {
  
  public Set<HospitalWithSpecializationDTO> findHospitalByFilters(String city, String specialization);
}
