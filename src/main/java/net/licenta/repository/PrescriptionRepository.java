package net.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
