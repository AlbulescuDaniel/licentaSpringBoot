package net.licenta.service;

import java.util.Optional;
import java.util.Set;

import net.licenta.model.dto.PrescriptionDTO;

public interface PrescriptionService {

  public Set<PrescriptionDTO> getAllPrescriptions();
  
  public Optional<PrescriptionDTO> getPrescriptionById(Long id);
  
  public Optional<PrescriptionDTO> createPrescription(PrescriptionDTO prescriptionDTO);
  
  public Optional<PrescriptionDTO> updatePrescription(Long id, PrescriptionDTO prescriptionDTO);
  
  public Boolean deletePrescriptionById(Long id);
  
  public Boolean deleteAllPrescriptions();
}
