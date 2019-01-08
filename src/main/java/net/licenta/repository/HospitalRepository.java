package net.licenta.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.dto.HospitalWithSpecializationDTO;
import net.licenta.model.entity.Hospital;

public interface HospitalRepository  extends JpaRepository<Hospital, Long>{
  
  public Set<Hospital> findByAddressCityIgnoreCase(String city);
}
