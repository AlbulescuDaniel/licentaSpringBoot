package net.licenta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.UserPharmacy;

public interface PharmacyRepository extends JpaRepository<UserPharmacy, Long>{

  Optional<UserPharmacy> findByUserName(String username);

}
