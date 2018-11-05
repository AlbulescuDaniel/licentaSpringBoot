package net.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.UserPharmacy;

public interface PharmacyRepository extends JpaRepository<UserPharmacy, Long>{

}
