package net.licenta.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
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
import net.licenta.model.dto.PrescriptionDetailsDTO;
import net.licenta.model.dto.PrescriptionDrugDTO;
import net.licenta.model.dto.PrescriptionWithPatientNameDTO;
import net.licenta.model.entity.Drug;
import net.licenta.model.entity.Hospital;
import net.licenta.model.entity.Prescription;
import net.licenta.model.entity.PrescriptionDrug;
import net.licenta.model.entity.UserPatient;
import net.licenta.model.util.DataModelTransformer;
import net.licenta.repository.DoctorRepository;
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

  @Autowired
  DoctorRepository doctorRepository;

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
    return patientRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName).map(patient -> {

      Set<Drug> drugs = new HashSet<>();
      prescriptionDTO.getPrescriptionDrugs().forEach(pd -> {
        Optional<Drug> drug = drugRepository.findByNameIgnoreCase(pd.getDrug());
        if (!drug.isPresent()) {
          throw new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE).getString(Constants.BUNDLE_PRESCRIPTION_CREATE_NO_DRUGS));
        }
        drugs.add(drug.get());
      });

      Prescription prescription = DataModelTransformer.fromPrescriptionDTOToPrescription(prescriptionDTO);
      List<PrescriptionDrug> prescriptionDrugs = prescriptionDTO.getPrescriptionDrugs().stream().map(prescriptionDrugEntity -> {
        PrescriptionDrug prescriptionDrug = DataModelTransformer.fromPrescriptionDrugDTOToPrescriptionDrug(prescriptionDrugEntity);
        prescriptionDrug.setDrug(drugs.stream().filter(drug -> drug.getName().equalsIgnoreCase(prescriptionDrugEntity.getDrug())).collect(CustomCollector.toSingleton()));
        prescriptionDrug.setPrescription(prescription);
        return prescriptionDrug;
      }).collect(Collectors.toList());

      prescription.setPatient(patient);
      prescription.setPrescriptionDrugs(prescriptionDrugs);
      return Optional.ofNullable(DataModelTransformer.fromPrescriptionToPrescriptionDTO(prescriptionRepository.save(prescription)));
    }).orElseThrow(() -> new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE).getString(Constants.BUNDLE_PATIENT_NAME_DO_NOT_EXIST)));
  }

  @Override
  public Optional<PrescriptionDTO> updatePrescription(Long id, PrescriptionDTO prescriptionDTO) {
    return prescriptionRepository.findById(id).map(entity -> {

      Set<Drug> drugs = new HashSet<>();
      prescriptionDTO.getPrescriptionDrugs().forEach(pd -> {
        Optional<Drug> drug = drugRepository.findByNameIgnoreCase(pd.getDrug());
        if (!drug.isPresent()) {
          throw new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE).getString(Constants.BUNDLE_PRESCRIPTION_CREATE_NO_DRUGS));
        }

        drugs.add(drug.get());
      });

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
  public Optional<PrescriptionDetailsDTO> getPrescriptionDetails(Long id) {
    return prescriptionRepository.findById(id).map(prescription -> doctorRepository.findByUserName(prescription.getCreationUser()).map(doctor -> {
      Hospital hospital = doctor.getHospital();
      List<PrescriptionDrugDTO> drugDTOs = prescription.getPrescriptionDrugs().stream().map(DataModelTransformer::fromPrescriptionDrugToPrescriptionDrugDTO).collect(Collectors.toList());

      PrescriptionDetailsDTO prescriptionDetailsDTO = new PrescriptionDetailsDTO(doctor.getFirstName() + " " + doctor.getLastName(), doctor.getSpeciality(), doctor.getEmail(), hospital.getName(),
          hospital.getAddress().getCity(), hospital.getEmail(), prescription.getDiagnostic(), prescription.getDatePrescripted(), drugDTOs);

      return Optional.ofNullable(prescriptionDetailsDTO);
    }).orElseGet(Optional::empty)).orElseGet(Optional::empty);
  }

  @Override
  public Set<PrescriptionDTO> getPatientPrescriptionsByPatientUserNameAndDateBetwwen(String userName, LocalDate startDate, LocalDate endDate) {
    return patientRepository.findByUserNameIgnoreCase(userName)
        .map(patient -> prescriptionRepository.findByPatientAndDatePrescriptedBetweenOrderByDatePrescriptedAscIdAsc(patient, startDate, endDate).stream()
            .map(DataModelTransformer::fromPrescriptionToPrescriptionDTO).collect(Collectors.toSet()))
        .orElseThrow(() -> new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE).getString(Constants.BUNDLE_USER_DO_NOT_FOUND)));

  }

  @Override
  public Set<PrescriptionWithPatientNameDTO> getPatientPrescriptionsByPatientFirstNameAndLastName(String firstName, String lastName, LocalDate startDate, LocalDate endDate) {

    Set<PrescriptionWithPatientNameDTO> prescriptionWithPatientNameDTOs = new HashSet<>();

    List<UserPatient> patients = patientRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingOrderByFirstNameAsc(firstName, lastName);

    if (patients.size() == 0) {
      throw new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, "1");
    }

    patients.forEach(patient -> prescriptionRepository.findByPatientAndDatePrescriptedBetweenOrderByDatePrescriptedAscIdAsc(patient, startDate, endDate).forEach(prescription -> {
      PrescriptionWithPatientNameDTO prescriptionWithPatientNameDTO = DataModelTransformer.fromPrescriptionToPrescriptionWithPatientNameDTO(prescription);
      prescriptionWithPatientNameDTO.setPatientFirstName(patient.getFirstName());
      prescriptionWithPatientNameDTO.setPatientLastName(patient.getLastName());
      prescriptionWithPatientNameDTO.setBirthDate(patient.getBirthDate());
      prescriptionWithPatientNameDTOs.add(prescriptionWithPatientNameDTO);
    }));

    if (prescriptionWithPatientNameDTOs.isEmpty()) {
      throw new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, "0");
    }

    return prescriptionWithPatientNameDTOs;
  }

  @Override
  public Set<PrescriptionWithPatientNameDTO> getPatientPrescriptionsByPatientFirstName(String firstName, LocalDate startDate, LocalDate endDate) {

    Set<PrescriptionWithPatientNameDTO> prescriptionWithPatientNameDTOs = new HashSet<>();

    List<UserPatient> patients = patientRepository.findByFirstNameIgnoreCaseContainingOrderByFirstNameAsc(firstName);

    if (patients.size() == 0) {
      throw new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, "1");
    }

    patients.forEach(patient -> prescriptionRepository.findByPatientAndDatePrescriptedBetweenOrderByDatePrescriptedAscIdAsc(patient, startDate, endDate).forEach(prescription -> {
      PrescriptionWithPatientNameDTO prescriptionWithPatientNameDTO = DataModelTransformer.fromPrescriptionToPrescriptionWithPatientNameDTO(prescription);
      prescriptionWithPatientNameDTO.setPatientFirstName(patient.getFirstName());
      prescriptionWithPatientNameDTO.setPatientLastName(patient.getLastName());
      prescriptionWithPatientNameDTO.setBirthDate(patient.getBirthDate());
      prescriptionWithPatientNameDTOs.add(prescriptionWithPatientNameDTO);
    }));

    if (prescriptionWithPatientNameDTOs.isEmpty()) {
      throw new ErrorDetailsNotFound(LocalDateTime.now(), Constants.NOT_FOUND, "0");
    }

    return prescriptionWithPatientNameDTOs;
  }
}
