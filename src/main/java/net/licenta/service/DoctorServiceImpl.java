package net.licenta.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.licenta.model.dto.DrugDTO;
import net.licenta.model.entity.Drug;
import net.licenta.model.util.DataModelTransformer;
import net.licenta.repository.DrugRepository;

@Service
public class DoctorServiceImpl implements DrugService {

  @Autowired
  DrugRepository drugRepository;

  @Override
  public Set<DrugDTO> getAllDrugs() {
    return drugRepository.findAll().stream().map(DataModelTransformer::fromDrugToDrugDTO).collect(Collectors.toSet());
  }

  @Override
  public Optional<DrugDTO> getDrugById(Long id) {
    return drugRepository.findById(id).map(entity -> Optional.ofNullable(DataModelTransformer.fromDrugToDrugDTO(entity))).orElseGet(Optional::empty);
  }

  @Override
  public Optional<DrugDTO> createDrug(DrugDTO drugDTO) {
    Drug drug = DataModelTransformer.fromDrugDTOToDrug(drugDTO);
    return Optional.ofNullable(DataModelTransformer.fromDrugToDrugDTO(drugRepository.save(drug)));
  }

  @Override
  public Optional<DrugDTO> updateDrug(Long id, DrugDTO drugDTO) {
    return drugRepository.findById(id).map(entity -> {
      // ownerValidator.validateOwner(entity.getUserName());
      Drug drug = DataModelTransformer.fromDrugDTOToDrug(drugDTO);
      BeanUtils.copyProperties(drug, entity);
      entity.setId(id);
      return Optional.of(DataModelTransformer.fromDrugToDrugDTO(drugRepository.save(entity)));
    }).orElseGet(Optional::empty);
  }

  @Override
  public Boolean deleteDrugById(Long id) {
    return drugRepository.findById(id).map(entity -> {
      // ownerValidator.validateOwner(entity.getUserName());
      drugRepository.deleteById(id);
      return true;
    }).orElseGet(() -> false);
  }

  @Override
  public Boolean deleteAllDrugs() {
    drugRepository.deleteAll();
    return drugRepository.count() == 0L;
  }
}
