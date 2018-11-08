package net.licenta.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import net.licenta.model.entity.Drug;

public interface DrugRepository extends JpaRepository<Drug, Long>{
  
  public Set<Drug> findByNameIn(Set<String> names);

}
