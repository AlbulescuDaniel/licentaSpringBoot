package net.licenta.repository;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.Prescription;
import net.licenta.model.entity.UserPatient;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

  public Set<Prescription> findByPatientAndDatePrescriptedBetween(UserPatient patient, LocalDate startDate, LocalDate endDate);
}
