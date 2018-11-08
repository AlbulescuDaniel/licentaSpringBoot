package net.licenta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.UserPatient;

public interface PatientRepository extends JpaRepository<UserPatient, Long> {

  Optional<UserPatient> findByUserName(String username);

  Optional<UserPatient> findByFirstNameAndLastName(String firstName, String lastName);
}
