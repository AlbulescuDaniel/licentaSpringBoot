package net.licenta.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import net.licenta.model.dto.PrescriptionDTO;
import net.licenta.model.dto.PrescriptionDetailsDTO;
import net.licenta.model.dto.PrescriptionWithPatientNameDTO;

public interface PrescriptionService {

  public Set<PrescriptionDTO> getAllPrescriptions();
  
  public Optional<PrescriptionDTO> getPrescriptionById(Long id);
  
  public Optional<PrescriptionDTO> createPrescription(PrescriptionDTO prescriptionDTO, String firstName, String lastName);
  
  public Optional<PrescriptionDTO> updatePrescription(Long id, PrescriptionDTO prescriptionDTO);
  
  public Boolean deletePrescriptionById(Long id);
  
  public Boolean deleteAllPrescriptions();

  public Set<PrescriptionWithPatientNameDTO> getPatientPrescriptionsByPatientFirstNameAndLastName(String firstName, String lastName, LocalDate startDate, LocalDate endDate);

  public Optional<PrescriptionDetailsDTO> getPrescriptionDetails(Long id);

  public Set<PrescriptionDTO> getPatientPrescriptionsByPatientUserNameAndDateBetwwen(String userName, LocalDate minusYears, LocalDate now);

  public Set<PrescriptionWithPatientNameDTO> getPatientPrescriptionsByPatientFirstName(String firstName, LocalDate startDate, LocalDate endDate);
}
