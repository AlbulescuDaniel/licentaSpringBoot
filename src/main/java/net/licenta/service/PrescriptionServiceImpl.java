package net.licenta.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.licenta.Constants;
import net.licenta.error.ErrorDetailsNotFound;
import net.licenta.model.dto.PrescriptionDTO;
import net.licenta.model.dto.PrescriptionDrugDTO;
import net.licenta.model.entity.Drug;
import net.licenta.model.entity.Prescription;
import net.licenta.model.entity.PrescriptionDrug;
import net.licenta.model.util.DataModelTransformer;
import net.licenta.repository.DrugRepository;
import net.licenta.repository.PatientRepository;
import net.licenta.repository.PrescriptionRepository;
import net.licenta.utill.CustomCollector;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

  @Autowired
  PrescriptionRepository prescriptionRepository;

  @Autowired
  PatientRepository patientRepository;

  @Autowired
  DrugRepository drugRepository;

  @Override
  public Set<PrescriptionDTO> getAllPrescriptions() {
    return prescriptionRepository.findAll().stream().map(DataModelTransformer::fromPrescriptionToPrescriptionDTO).collect(Collectors.toSet());
  }

  @Override
  public Optional<PrescriptionDTO> getPrescriptionById(Long id) {
    return prescriptionRepository.findById(id).map(entity -> Optional.ofNullable(DataModelTransformer.fromPrescriptionToPrescriptionDTO(entity))).orElseGet(Optional::empty);
  }

  @Override
  public Optional<PrescriptionDTO> createPrescription(PrescriptionDTO prescriptionDTO, String firstName, String lastName) {
    return patientRepository.findByFirstNameAndLastName(firstName, lastName).map(patient -> {
      Set<Drug> drugs = drugRepository.findByNameIn(prescriptionDTO.getPrescriptionDrugs().stream().map(PrescriptionDrugDTO::getDrug).collect(Collectors.toSet()));

      if (drugs.size() != prescriptionDTO.getPrescriptionDrugs().size()) {
        throw new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE).getString(Constants.BUNDLE_PRESCRIPTION_CREATE_NO_DRUGS));
      }

      Prescription prescription = DataModelTransformer.fromPrescriptionDTOToPrescription(prescriptionDTO);
      List<PrescriptionDrug> prescriptionDrugs = prescriptionDTO.getPrescriptionDrugs().stream().map(prescriptionDrugEntity -> {
        PrescriptionDrug prescriptionDrug = DataModelTransformer.fromPrescriptionDrugDTOToPrescriptionDrug(prescriptionDrugEntity);
        prescriptionDrug.setDrug(drugs.stream().filter(drug -> drug.getName().equals(prescriptionDrugEntity.getDrug())).collect(CustomCollector.toSingleton()));
        prescriptionDrug.setPrescription(prescription);
        return prescriptionDrug;
      }).collect(Collectors.toList());
      
      prescription.setPatient(patient);
      prescription.setPrescriptionDrugs(prescriptionDrugs);
      return Optional.ofNullable(DataModelTransformer.fromPrescriptionToPrescriptionDTO(prescriptionRepository.save(prescription)));
    }).orElseGet(Optional::empty);
  }

  @Override
  public Optional<PrescriptionDTO> updatePrescription(Long id, PrescriptionDTO prescriptionDTO) {
    return prescriptionRepository.findById(id).map(entity -> {
      Set<Drug> drugs = drugRepository.findByNameIn(prescriptionDTO.getPrescriptionDrugs().stream().map(PrescriptionDrugDTO::getDrug).collect(Collectors.toSet()));

      if (drugs.size() != prescriptionDTO.getPrescriptionDrugs().size()) {
        throw new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE).getString(Constants.BUNDLE_PRESCRIPTION_CREATE_NO_DRUGS));
      }

      Prescription prescription = DataModelTransformer.fromPrescriptionDTOToPrescription(prescriptionDTO);
      BeanUtils.copyProperties(prescription, entity);
      entity.setId(id);
      entity.setPrescriptionDrugs(prescription.getPrescriptionDrugs());

      return Optional.ofNullable(DataModelTransformer.fromPrescriptionToPrescriptionDTO(prescriptionRepository.save(entity)));
    }).orElseGet(Optional::empty);
  }

  @Override
  public Boolean deletePrescriptionById(Long id) {
    return prescriptionRepository.findById(id).map(entity -> {
      prescriptionRepository.deleteById(id);
      return true;
    }).orElseGet(() -> false);
  }

  @Override
  public Boolean deleteAllPrescriptions() {
    prescriptionRepository.deleteAll();
    return prescriptionRepository.count() == 0L;
  }

  @Override
  public Set<PrescriptionDTO> getPatientPrescriptionsByPatientName(String firstName, String lastName, LocalDate startDate, LocalDate endDate) {
    return patientRepository.findByFirstNameAndLastName(firstName, lastName).map(patient -> prescriptionRepository.findByPatientAndDatePrescriptedBetween(patient, startDate, endDate).stream()
        .map(DataModelTransformer::fromPrescriptionToPrescriptionDTO).collect(Collectors.toSet())).orElseThrow(() -> new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND,
            ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE).getString(Constants.BUNDLE_USER_DO_NOT_FOUND)));
  }
}
