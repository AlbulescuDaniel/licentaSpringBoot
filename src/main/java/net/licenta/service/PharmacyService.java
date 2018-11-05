package net.licenta.service;

import java.util.Optional;
import java.util.Set;

import net.licenta.model.dto.UserPharmacyDTO;

public interface PharmacyService {

  public Set<UserPharmacyDTO> getAllPharmacies();
  
  public Optional<UserPharmacyDTO> getPharmacyById(Long id);
  
  public Optional<UserPharmacyDTO> createPharmacy(UserPharmacyDTO userPharmacyDTO);
  
  public Optional<UserPharmacyDTO> updatePharmacy(Long id, UserPharmacyDTO userPharmacyDTO);
  
  public Boolean deletePharmacyById(Long id);
  
  public Boolean deleteAllPharmacies();
}
