package net.licenta.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.licenta.model.dto.UserPharmacyDTO;
import net.licenta.model.entity.UserPharmacy;
import net.licenta.model.util.DataModelTransformer;
import net.licenta.repository.PharmacyRepository;

@Service
public class PharmacyServiceImpl implements PharmacyService {

  @Autowired
  PharmacyRepository pharmacyRepository;

  @Override
  public Set<UserPharmacyDTO> getAllPharmacies() {
    return pharmacyRepository.findAll().stream().map(DataModelTransformer::fromPharmacyToPharmacyDTO).collect(Collectors.toSet());
  }

  @Override
  public Optional<UserPharmacyDTO> getPharmacyById(Long id) {
    return pharmacyRepository.findById(id).map(entity -> Optional.ofNullable(DataModelTransformer.fromPharmacyToPharmacyDTO(entity))).orElseGet(Optional::empty);
  }

  @Override
  public Optional<UserPharmacyDTO> createPharmacy(UserPharmacyDTO userPharmacyDTO) {
    UserPharmacy pharmacy = DataModelTransformer.fromPharmacyDTOToPharmacy(userPharmacyDTO);
    return Optional.ofNullable(DataModelTransformer.fromPharmacyToPharmacyDTO(pharmacyRepository.save(pharmacy)));
  }

  @Override
  public Optional<UserPharmacyDTO> updatePharmacy(Long id, UserPharmacyDTO userPharmacyDTO) {
    return pharmacyRepository.findById(id).map(entity -> {
      UserPharmacy userPharmacy = DataModelTransformer.fromPharmacyDTOToPharmacy(userPharmacyDTO);
      BeanUtils.copyProperties(userPharmacy, entity);
      entity.setId(id);
      return Optional.of(DataModelTransformer.fromPharmacyToPharmacyDTO(pharmacyRepository.save(entity)));
    }).orElseGet(Optional::empty);
  }

  @Override
  public Boolean deletePharmacyById(Long id) {
    return pharmacyRepository.findById(id).map(entity -> {
      pharmacyRepository.deleteById(id);
      return true;
    }).orElseGet(() -> false);
  }

  @Override
  public Boolean deleteAllPharmacies() {
    pharmacyRepository.deleteAll();
    return pharmacyRepository.count() == 0L;
  }
}
