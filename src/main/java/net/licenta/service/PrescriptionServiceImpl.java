package net.licenta.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.licenta.Constants;
import net.licenta.error.ErrorDetailsNotFound;
import net.licenta.model.dto.PrescriptionDTO;
import net.licenta.model.entity.Drug;
import net.licenta.model.entity.Prescription;
import net.licenta.model.util.DataModelTransformer;
import net.licenta.repository.DrugRepository;
import net.licenta.repository.PrescriptionRepository;

public class PrescriptionServiceImpl implements PrescriptionService {

  @Autowired
  PrescriptionRepository prescriptionRepository;

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
  public Optional<PrescriptionDTO> createPrescription(PrescriptionDTO prescriptionDTO) {
    Set<Drug> drugs = drugRepository
        .findByNameIn(prescriptionDTO.getPrescriptionDrugs().stream().map(entity -> DataModelTransformer.fromDrugDTOToDrug(entity.getDrugDTO()).getName()).collect(Collectors.toSet()));

    if (drugs.size() != prescriptionDTO.getPrescriptionDrugs().size()) {
      throw new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE).getString(Constants.BUNDLE_PRESCRIPTION_CREATE_NO_DRUGS));
    }

    Prescription prescription = DataModelTransformer.fromPrescriptionDTOToPrescription(prescriptionDTO);
    return Optional.ofNullable(DataModelTransformer.fromPrescriptionToPrescriptionDTO(prescriptionRepository.save(prescription)));
  }

  @Override
  public Optional<PrescriptionDTO> updatePrescription(Long id, PrescriptionDTO prescriptionDTO) {
    return prescriptionRepository.findById(id).map(entity -> {

      Set<Drug> drugs = drugRepository
          .findByNameIn(prescriptionDTO.getPrescriptionDrugs().stream().map(drug -> DataModelTransformer.fromDrugDTOToDrug(drug.getDrugDTO()).getName()).collect(Collectors.toSet()));

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
}
