package net.licenta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.UserPatient;

public interface PatientRepository extends JpaRepository<UserPatient, Long> {

  Optional<UserPatient> findByUserNameIgnoreCase(String username);
  
  Optional<UserPatient> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

  List<UserPatient> findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingOrderByFirstNameAsc(String firstName, String lastName);

  List<UserPatient> findByFirstNameIgnoreCaseContainingOrderByFirstNameAsc(String firstName);
}
