package net.licenta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.UserDoctor;

public interface DoctorRepository extends JpaRepository<UserDoctor, Long>{

  Optional<UserDoctor> findByUserName(String username);

}
