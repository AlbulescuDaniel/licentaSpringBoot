package net.licenta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import net.licenta.model.entity.Drug;

public interface DrugRepository extends JpaRepository<Drug, Long>{
  
  public Optional<Drug> findByNameIgnoreCase(String name);

  public List<Drug> findByNameContainingIgnoreCase(String name);


}
