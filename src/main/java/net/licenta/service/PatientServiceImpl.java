package net.licenta.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.licenta.model.dto.UserPatientDTO;
import net.licenta.model.entity.UserPatient;
import net.licenta.model.util.DataModelTransformer;
import net.licenta.repository.PatientRepository;

@Service
public class PatientServiceImpl implements UserDetailsService, PatientService {

  @Autowired
  PatientRepository patientRepository;

  @Override
  public Set<UserPatientDTO> getAllPatients() {
    return patientRepository.findAll().stream().map(DataModelTransformer::fromPatientToPatientDTO).collect(Collectors.toSet());
  }

  @Override
  public Optional<UserPatientDTO> getPatientById(Long id) {
    return patientRepository.findById(id).map(entity -> Optional.ofNullable(DataModelTransformer.fromPatientToPatientDTO(entity))).orElseGet(Optional::empty);
  }

  @Override
  public Optional<UserPatientDTO> createPatient(UserPatientDTO userPatientDTO) {
    UserPatient patient = DataModelTransformer.fromPatientDTOToPatient(userPatientDTO);
    return Optional.ofNullable(DataModelTransformer.fromPatientToPatientDTO(patientRepository.save(patient)));
  }

  @Override
  public Optional<UserPatientDTO> updatePatient(Long id, UserPatientDTO userPatientDTO) {
    return patientRepository.findById(id).map(entity -> {
      // ownerValidator.validateOwner(entity.getUserName());
      UserPatient userPatient = DataModelTransformer.fromPatientDTOToPatient(userPatientDTO);
      BeanUtils.copyProperties(userPatient, entity);
      entity.setId(id);
      return Optional.of(DataModelTransformer.fromPatientToPatientDTO(patientRepository.save(entity)));
    }).orElseGet(Optional::empty);
  }

  @Override
  public Boolean deletePatientById(Long id) {
    return patientRepository.findById(id).map(entity -> {
      // ownerValidator.validateOwner(entity.getUserName());
      patientRepository.deleteById(id);
      return true;
    }).orElseGet(() -> false);
  }

  @Override
  public Boolean deleteAllPatients() {
    patientRepository.deleteAll();
    return patientRepository.count() == 0L;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }
}
