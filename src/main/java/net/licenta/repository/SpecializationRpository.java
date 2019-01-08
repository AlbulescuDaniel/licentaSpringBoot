package net.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.licenta.model.entity.Specialization;

public interface SpecializationRpository extends JpaRepository<Specialization, Long>{

}
