package net.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.Drug;

public interface DrugRepository extends JpaRepository<Drug, Long>{
}
