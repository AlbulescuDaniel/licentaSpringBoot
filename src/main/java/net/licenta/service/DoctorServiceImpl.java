package net.licenta.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.licenta.model.dto.PrescriptionDoctorHospitalDTO;
import net.licenta.model.dto.UserDoctorDTO;
import net.licenta.model.entity.Prescription;
import net.licenta.model.entity.UserDoctor;
import net.licenta.model.util.DataModelTransformer;
import net.licenta.repository.DoctorRepository;
import net.licenta.repository.PrescriptionRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

  @Autowired
  DoctorRepository doctorRepository;
  
  @Autowired
  PrescriptionRepository prescriptionRepository;

  @Override
  public Set<UserDoctorDTO> getAllDoctors() {
    return doctorRepository.findAll().stream().map(DataModelTransformer::fromDoctorToDoctorDTO).collect(Collectors.toSet());
  }

  @Override
  public Optional<UserDoctorDTO> getDoctorById(Long id) {
    return doctorRepository.findById(id).map(entity -> Optional.ofNullable(DataModelTransformer.fromDoctorToDoctorDTO(entity))).orElseGet(Optional::empty);
  }

  @Override
  public Optional<UserDoctorDTO> createDoctor(UserDoctorDTO userDoctorDTO) {
    UserDoctor patient = DataModelTransformer.fromDoctorDTOToDoctor(userDoctorDTO);
    return Optional.ofNullable(DataModelTransformer.fromDoctorToDoctorDTO(doctorRepository.save(patient)));
  }

  @Override
  public Optional<UserDoctorDTO> updateDoctor(Long id, UserDoctorDTO userDoctorDTO) {
    return doctorRepository.findById(id).map(entity -> {
      UserDoctor userDoctor = DataModelTransformer.fromDoctorDTOToDoctor(userDoctorDTO);
      BeanUtils.copyProperties(userDoctor, entity);
      entity.setId(id);
      return Optional.of(DataModelTransformer.fromDoctorToDoctorDTO(doctorRepository.save(entity)));
    }).orElseGet(Optional::empty);
  }

  @Override
  public Boolean deleteDoctorById(Long id) {
    return doctorRepository.findById(id).map(entity -> {
      doctorRepository.deleteById(id);
      return true;
    }).orElseGet(() -> false);
  }

  @Override
  public Boolean deleteAllDoctors() {
    doctorRepository.deleteAll();
    return doctorRepository.count() == 0L;
  }

  @Override
  public Optional<PrescriptionDoctorHospitalDTO> getDoctorAndHospitalforAutocomplete(String userName) {
    return doctorRepository.findByUserName(userName).map(entity -> {
      PrescriptionDoctorHospitalDTO  prescriptionDoctorHospitalDTO = DataModelTransformer.fromDoctorToPrescriptionDoctorHospitalDTO(entity);
      prescriptionDoctorHospitalDTO.setPrescriptionNumber(prescriptionRepository.findTopByOrderByIdDesc().map(Prescription::getId).orElseGet(() -> 0L) + 1);
      
      return prescriptionDoctorHospitalDTO;
    });
  }
}
