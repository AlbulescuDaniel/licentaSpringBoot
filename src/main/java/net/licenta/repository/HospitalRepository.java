package net.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.Hospital;

public interface HospitalRepository  extends JpaRepository<Hospital, Long>{
  
}
