package net.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.UserPatient;

public interface PatientRepository extends JpaRepository<UserPatient, Long>{

}
