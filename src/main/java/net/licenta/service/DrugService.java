package net.licenta.service;

import java.util.Optional;
import java.util.Set;

import net.licenta.model.dto.DrugDTO;

public interface DrugService {

  public Set<DrugDTO> getAllDrugs();
  
  public Optional<DrugDTO> getDrugById(Long id);
  
  public Optional<DrugDTO> createDrug(DrugDTO userPatientDTO);
  
  public Optional<DrugDTO> updateDrug(Long id, DrugDTO userPatientDTO);
  
  public Boolean deleteDrugById(Long id);
  
  public Boolean deleteAllDrugs();
}
