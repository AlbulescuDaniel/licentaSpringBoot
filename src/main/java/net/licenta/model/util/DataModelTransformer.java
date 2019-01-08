package net.licenta.model.util;

import java.util.stream.Collectors;

import net.licenta.model.dto.AddressDTO;
import net.licenta.model.dto.CreatePatientDTO;
import net.licenta.model.dto.DoctorProfileDTO;
import net.licenta.model.dto.DrugDTO;
import net.licenta.model.dto.HospitalDTO;
import net.licenta.model.dto.HospitalWithSpecializationDTO;
import net.licenta.model.dto.PrescriptionDTO;
import net.licenta.model.dto.PrescriptionDoctorHospitalDTO;
import net.licenta.model.dto.PrescriptionDrugDTO;
import net.licenta.model.dto.PrescriptionWithPatientNameDTO;
import net.licenta.model.dto.SpecializationDTO;
import net.licenta.model.dto.UserDoctorDTO;
import net.licenta.model.dto.UserPatientDTO;
import net.licenta.model.dto.UserPharmacyDTO;
import net.licenta.model.entity.Address;
import net.licenta.model.entity.Drug;
import net.licenta.model.entity.Hospital;
import net.licenta.model.entity.Prescription;
import net.licenta.model.entity.PrescriptionDrug;
import net.licenta.model.entity.Specialization;
import net.licenta.model.entity.UserDoctor;
import net.licenta.model.entity.UserPatient;
import net.licenta.model.entity.UserPharmacy;

public class DataModelTransformer {

  private DataModelTransformer() {
  }

  public static AddressDTO fromAddressToAddressDTO(Address address) {
    AddressDTO addressDTO = new AddressDTO();
    addressDTO.setCountryName(address.getCountryName());
    addressDTO.setPostalCode(address.getPostalCode());
    addressDTO.setCity(address.getCity());
    addressDTO.setRegion(address.getRegion());
    addressDTO.setStreet(address.getStreet());
    addressDTO.setStreetNumber(address.getStreetNumber());

    return addressDTO;
  }

  public static Address fromAddressDTOToAddress(AddressDTO addressDTO) {
    Address address = new Address();
    address.setCountryName(addressDTO.getCountryName());
    address.setPostalCode(addressDTO.getPostalCode());
    address.setCity(addressDTO.getCity());
    address.setRegion(addressDTO.getRegion());
    address.setStreet(addressDTO.getStreet());
    address.setStreetNumber(addressDTO.getStreetNumber());

    return address;
  }

  public static UserPatientDTO fromPatientToPatientDTO(UserPatient userPatient) {
    UserPatientDTO userPatientDTO = new UserPatientDTO();
    userPatientDTO.setGender(userPatient.getGender());
    userPatientDTO.setId(userPatient.getId());
    userPatientDTO.setFirstName(userPatient.getFirstName());
    userPatientDTO.setLastName(userPatient.getLastName());
    userPatientDTO.setUserName(userPatient.getUserName());
    userPatientDTO.setEmail(userPatient.getEmail());
    userPatientDTO.setPhoneNumber(userPatient.getPhoneNumber());
    userPatientDTO.setCnp(userPatient.getCnp());
    userPatientDTO.setBirthDate(userPatient.getBirthDate());
    userPatientDTO.setPassword(userPatient.getPassword());
    userPatientDTO.setAddressDTO(fromAddressToAddressDTO(userPatient.getAddress()));

    return userPatientDTO;
  }

  public static UserPatient fromPatientDTOToPatient(UserPatientDTO userPatientDTO) {
    UserPatient userPatient = new UserPatient();
    userPatient.setGender(userPatientDTO.getGender());
    userPatient.setId(userPatientDTO.getId());
    userPatient.setFirstName(userPatientDTO.getFirstName());
    userPatient.setLastName(userPatientDTO.getLastName());
    userPatient.setUserName(userPatientDTO.getUserName());
    userPatient.setEmail(userPatientDTO.getEmail());
    userPatient.setPhoneNumber(userPatientDTO.getPhoneNumber());
    userPatient.setPassword(userPatientDTO.getPassword());
    userPatient.setCnp(userPatientDTO.getCnp());
    userPatient.setBirthDate(userPatientDTO.getBirthDate());
    userPatient.setAddress(fromAddressDTOToAddress(userPatientDTO.getAddressDTO()));

    return userPatient;
  }

  public static UserPatient fromCreatePatientDTOToPatient(CreatePatientDTO createPatientDTO) {
    UserPatient userPatient = new UserPatient();
    userPatient.setGender(createPatientDTO.getGender());
    userPatient.setFirstName(createPatientDTO.getFirstName());
    userPatient.setLastName(createPatientDTO.getLastName());
    userPatient.setEmail(createPatientDTO.getEmail());
    userPatient.setPhoneNumber(createPatientDTO.getPhoneNumber());
    userPatient.setCnp(createPatientDTO.getCnp());
    userPatient.setBirthDate(createPatientDTO.getBirthDate());
    userPatient.setAddress(fromAddressDTOToAddress(createPatientDTO.getAddress()));

    return userPatient;
  }

  public static UserDoctorDTO fromDoctorToDoctorDTO(UserDoctor userDoctor) {
    UserDoctorDTO userDoctorDTO = new UserDoctorDTO();
    userDoctorDTO.setId(userDoctor.getId());
    userDoctorDTO.setFirstName(userDoctor.getFirstName());
    userDoctorDTO.setLastName(userDoctor.getLastName());
    userDoctorDTO.setUserName(userDoctor.getUserName());
    userDoctorDTO.setEmail(userDoctor.getEmail());
    userDoctorDTO.setSpeciality(userDoctor.getSpeciality());
    userDoctorDTO.setPhoneNumber(userDoctor.getPhoneNumber());
    userDoctorDTO.setPassword(userDoctor.getPassword());
    userDoctorDTO.setAddressDTO(fromAddressToAddressDTO(userDoctor.getAddress()));

    return userDoctorDTO;
  }

  public static UserDoctor fromDoctorDTOToDoctor(UserDoctorDTO userDoctorDTO) {
    UserDoctor userDoctor = new UserDoctor();
    userDoctor.setId(userDoctorDTO.getId());
    userDoctor.setFirstName(userDoctorDTO.getFirstName());
    userDoctor.setLastName(userDoctorDTO.getLastName());
    userDoctor.setUserName(userDoctorDTO.getUserName());
    userDoctor.setEmail(userDoctorDTO.getEmail());
    userDoctor.setPhoneNumber(userDoctorDTO.getPhoneNumber());
    userDoctor.setSpeciality(userDoctorDTO.getSpeciality());
    userDoctor.setPassword(userDoctorDTO.getPassword());
    userDoctor.setAddress(fromAddressDTOToAddress(userDoctorDTO.getAddressDTO()));

    return userDoctor;
  }

  public static UserPharmacyDTO fromPharmacyToPharmacyDTO(UserPharmacy userPharmacy) {
    UserPharmacyDTO userPharmacyDTO = new UserPharmacyDTO();
    userPharmacyDTO.setId(userPharmacy.getId());
    userPharmacyDTO.setName(userPharmacy.getName());
    userPharmacyDTO.setUserName(userPharmacy.getUserName());
    userPharmacyDTO.setEmail(userPharmacy.getEmail());
    userPharmacyDTO.setPhoneNumber(userPharmacy.getPhoneNumber());
    userPharmacyDTO.setPassword(userPharmacy.getPassword());
    userPharmacyDTO.setAddressDTO(fromAddressToAddressDTO(userPharmacy.getAddress()));

    return userPharmacyDTO;
  }

  public static UserPharmacy fromPharmacyDTOToPharmacy(UserPharmacyDTO userPharmacyDTO) {
    UserPharmacy userPharmacy = new UserPharmacy();
    userPharmacy.setId(userPharmacyDTO.getId());
    userPharmacy.setName(userPharmacyDTO.getName());
    userPharmacy.setUserName(userPharmacyDTO.getUserName());
    userPharmacy.setEmail(userPharmacyDTO.getEmail());
    userPharmacy.setPhoneNumber(userPharmacyDTO.getPhoneNumber());
    userPharmacy.setPassword(userPharmacyDTO.getPassword());
    userPharmacy.setAddress(fromAddressDTOToAddress(userPharmacyDTO.getAddressDTO()));

    return userPharmacy;
  }

  public static DrugDTO fromDrugToDrugDTO(Drug drug) {
    DrugDTO drugDTO = new DrugDTO();
    drugDTO.setId(drug.getId());
    drugDTO.setName(drug.getName());
    drugDTO.setAdministrationMethod(drug.getAdministrationMethod());
    drugDTO.setComposition(drug.getComposition());
    drugDTO.setContraindications(drug.getContraindications());
    drugDTO.setExcipients(drug.getExcipients());
    drugDTO.setIncompatibilities(drug.getIncompatibilities());
    drugDTO.setMarketingAuthorisationHolder(drug.getMarketingAuthorisationHolder());
    drugDTO.setOverdose(drug.getOverdose());
    drugDTO.setPharmaceuticalForm(drug.getPharmaceuticalForm());
    drugDTO.setPharmacokineticProperties(drug.getPharmacokineticProperties());
    drugDTO.setShelfLife(drug.getShelfLife());
    drugDTO.setSpecialPrecautionsForStorage(drug.getSpecialPrecautionsForStorage());
    drugDTO.setSpecialWarnings(drug.getSpecialWarnings());
    drugDTO.setTherapeuticIndications(drug.getTherapeuticIndications());

    return drugDTO;
  }

  public static Drug fromDrugDTOToDrug(DrugDTO drugDTO) {
    Drug drug = new Drug();
    drug.setId(drugDTO.getId());
    drug.setName(drugDTO.getName());
    drug.setAdministrationMethod(drugDTO.getAdministrationMethod());
    drug.setComposition(drugDTO.getComposition());
    drug.setContraindications(drugDTO.getContraindications());
    drug.setExcipients(drugDTO.getExcipients());
    drug.setIncompatibilities(drugDTO.getIncompatibilities());
    drug.setMarketingAuthorisationHolder(drugDTO.getMarketingAuthorisationHolder());
    drug.setOverdose(drugDTO.getOverdose());
    drug.setPharmaceuticalForm(drugDTO.getPharmaceuticalForm());
    drug.setPharmacokineticProperties(drugDTO.getPharmacokineticProperties());
    drug.setShelfLife(drugDTO.getShelfLife());
    drug.setSpecialPrecautionsForStorage(drugDTO.getSpecialPrecautionsForStorage());
    drug.setSpecialWarnings(drugDTO.getSpecialWarnings());
    drug.setTherapeuticIndications(drugDTO.getTherapeuticIndications());

    return drug;
  }

  public static PrescriptionDrug fromPrescriptionDrugDTOToPrescriptionDrug(PrescriptionDrugDTO prescriptionDrugDTO) {
    PrescriptionDrug prescriptionDrug = new PrescriptionDrug();
    prescriptionDrug.setId(prescriptionDrugDTO.getId());
    prescriptionDrug.setDays(prescriptionDrugDTO.getDays());
    prescriptionDrug.setDescription(prescriptionDrugDTO.getDescription());
    prescriptionDrug.setPillsNumber(prescriptionDrugDTO.getPillsNumber());
    Drug drug = new Drug();
    drug.setName(prescriptionDrugDTO.getDrug());
    prescriptionDrug.setDrug(drug);

    return prescriptionDrug;
  }

  public static PrescriptionDrugDTO fromPrescriptionDrugToPrescriptionDrugDTO(PrescriptionDrug prescriptionDrug) {
    PrescriptionDrugDTO prescriptionDrugDTO = new PrescriptionDrugDTO();
    prescriptionDrugDTO.setDays(prescriptionDrug.getDays());
    prescriptionDrugDTO.setId(prescriptionDrug.getId());
    prescriptionDrugDTO.setDescription(prescriptionDrug.getDescription());
    prescriptionDrugDTO.setPillsNumber(prescriptionDrug.getPillsNumber());
    prescriptionDrugDTO.setDrug(prescriptionDrug.getDrug().getName());

    return prescriptionDrugDTO;
  }

  public static PrescriptionDTO fromPrescriptionToPrescriptionDTO(Prescription prescription) {
    PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
    prescriptionDTO.setPrescriptionNumber(prescription.getPrescriptionNumber());
    prescriptionDTO.setUserGender(prescription.getUserGender());
    prescriptionDTO.setNationality(prescription.getNationality());
    prescriptionDTO.setHospitalType(prescription.getHospitalType());
    prescriptionDTO.setPatientType(prescription.getPatientType());
    prescriptionDTO.setId(prescription.getId());
    prescriptionDTO.setDiagnostic(prescription.getDiagnostic());
    prescriptionDTO.setDatePrescripted(prescription.getDatePrescripted());
    prescriptionDTO.setPrescriptionDrugs(prescription.getPrescriptionDrugs().stream().map(DataModelTransformer::fromPrescriptionDrugToPrescriptionDrugDTO).collect(Collectors.toList()));

    return prescriptionDTO;
  }

  public static Prescription fromPrescriptionDTOToPrescription(PrescriptionDTO prescriptionDTO) {
    Prescription prescription = new Prescription();
    prescription.setId(prescriptionDTO.getId());
    prescription.setDiagnostic(prescriptionDTO.getDiagnostic());
    prescription.setPrescriptionNumber(prescriptionDTO.getPrescriptionNumber());
    prescription.setUserGender(prescriptionDTO.getUserGender());
    prescription.setNationality(prescriptionDTO.getNationality());
    prescription.setHospitalType(prescriptionDTO.getHospitalType());
    prescription.setPatientType(prescriptionDTO.getPatientType());
    prescription.setDatePrescripted(prescriptionDTO.getDatePrescripted());

    prescription.setPrescriptionDrugs(prescriptionDTO.getPrescriptionDrugs().stream().map(DataModelTransformer::fromPrescriptionDrugDTOToPrescriptionDrug).collect(Collectors.toList()));

    return prescription;
  }

  public static PrescriptionWithPatientNameDTO fromPrescriptionToPrescriptionWithPatientNameDTO(Prescription prescription) {
    PrescriptionWithPatientNameDTO prescriptionDTO = new PrescriptionWithPatientNameDTO();
    prescriptionDTO.setPrescriptionNumber(prescription.getPrescriptionNumber());
    prescriptionDTO.setUserGender(prescription.getUserGender());
    prescriptionDTO.setNationality(prescription.getNationality());
    prescriptionDTO.setHospitalType(prescription.getHospitalType());
    prescriptionDTO.setPatientType(prescription.getPatientType());
    prescriptionDTO.setId(prescription.getId());
    prescriptionDTO.setDiagnostic(prescription.getDiagnostic());
    prescriptionDTO.setDatePrescripted(prescription.getDatePrescripted());
    prescriptionDTO.setPrescriptionDrugs(prescription.getPrescriptionDrugs().stream().map(DataModelTransformer::fromPrescriptionDrugToPrescriptionDrugDTO).collect(Collectors.toList()));

    return prescriptionDTO;
  }

  public static Prescription fromPrescriptionWithPatientNameDTOToPrescription(PrescriptionWithPatientNameDTO prescriptionDTO) {
    Prescription prescription = new Prescription();
    prescription.setId(prescriptionDTO.getId());
    prescription.setDiagnostic(prescriptionDTO.getDiagnostic());
    prescription.setPrescriptionNumber(prescriptionDTO.getPrescriptionNumber());
    prescription.setUserGender(prescriptionDTO.getUserGender());
    prescription.setNationality(prescriptionDTO.getNationality());
    prescription.setHospitalType(prescriptionDTO.getHospitalType());
    prescription.setPatientType(prescriptionDTO.getPatientType());
    prescription.setDatePrescripted(prescriptionDTO.getDatePrescripted());
    prescription.setPrescriptionDrugs(prescriptionDTO.getPrescriptionDrugs().stream().map(DataModelTransformer::fromPrescriptionDrugDTOToPrescriptionDrug).collect(Collectors.toList()));

    return prescription;
  }

  public static Hospital fromHospitalToHospitalDTO(HospitalDTO hospitalDTO) {
    Hospital hospital = new Hospital();
    hospital.setId(hospitalDTO.getId());
    hospital.setName(hospitalDTO.getName());
    hospital.setPhone(hospitalDTO.getPhone());
    hospital.setUrc(hospitalDTO.getUrc());
    hospital.setAddress(fromAddressDTOToAddress(hospitalDTO.getAddressDTO()));
    hospital.setWebSite(hospitalDTO.getWebSite());
    hospital.setEmail(hospitalDTO.getEmail());
    hospital.setDoctors(hospitalDTO.getDoctorsDTO().stream().map(DataModelTransformer::fromDoctorDTOToDoctor).collect(Collectors.toSet()));

    return hospital;
  }

  public static HospitalDTO fromHospitalDTOToHospital(Hospital hospital) {
    HospitalDTO hospitalDTO = new HospitalDTO();
    hospitalDTO.setId(hospital.getId());
    hospitalDTO.setName(hospital.getName());
    hospitalDTO.setPhone(hospital.getPhone());
    hospitalDTO.setUrc(hospital.getUrc());
    hospitalDTO.setAddressDTO(fromAddressToAddressDTO(hospital.getAddress()));
    hospitalDTO.setWebSite(hospital.getWebSite());
    hospitalDTO.setEmail(hospital.getEmail());
    hospitalDTO.setDoctorsDTO(hospital.getDoctors().stream().map(DataModelTransformer::fromDoctorToDoctorDTO).collect(Collectors.toSet()));

    return hospitalDTO;
  }

  public static PrescriptionDoctorHospitalDTO fromDoctorToPrescriptionDoctorHospitalDTO(UserDoctor doctor) {
    Hospital hospital = doctor.getHospital();

    PrescriptionDoctorHospitalDTO prescriptionDoctorHospitalDTO = new PrescriptionDoctorHospitalDTO();
    prescriptionDoctorHospitalDTO.setDoctorEmail(doctor.getEmail());
    prescriptionDoctorHospitalDTO.setHospitalName(hospital.getName());
    prescriptionDoctorHospitalDTO.setHospitalPhone(hospital.getPhone());
    prescriptionDoctorHospitalDTO.setHospitalState(hospital.getAddress().getCountryName());
    prescriptionDoctorHospitalDTO.setHospitalURC(hospital.getUrc());

    return prescriptionDoctorHospitalDTO;
  }
  
  public static DoctorProfileDTO fromDoctorToDoctorProfileDTO(UserDoctor userDoctor) {
    Hospital hospital = userDoctor.getHospital();
    
    DoctorProfileDTO profileDTO = new DoctorProfileDTO();
    profileDTO.setFirstName(userDoctor.getFirstName());
    profileDTO.setLastName(userDoctor.getLastName());
    profileDTO.setUserName(userDoctor.getUserName());
    profileDTO.setEmail(userDoctor.getEmail());
    profileDTO.setSpeciality(userDoctor.getSpeciality());
    profileDTO.setPhoneNumber(userDoctor.getPhoneNumber());
    profileDTO.setAddressDTO(fromAddressToAddressDTO(userDoctor.getAddress()));
    profileDTO.setHospitalName(hospital.getName());
    profileDTO.setHospitalEmail(hospital.getEmail());

    return profileDTO;
  }
  
  public static SpecializationDTO fromSpecializationToSpecializationDTO(Specialization specialization) {
    return new SpecializationDTO(specialization.getName());
  }
  
  public static Specialization fromSpecializationDTOToSpecialization(SpecializationDTO specialization) {
    return new Specialization(specialization.getName());
  }
  
  public static HospitalWithSpecializationDTO fromHospitalToHospitalWithSpecializationDTO(Hospital hospital) {
    HospitalWithSpecializationDTO hospitalWithSpecializationDTO = new HospitalWithSpecializationDTO();
    hospitalWithSpecializationDTO.setId(hospital.getId());
    hospitalWithSpecializationDTO.setName(hospital.getName());
    hospitalWithSpecializationDTO.setPhone(hospital.getPhone());
    hospitalWithSpecializationDTO.setUrc(hospital.getUrc());
    hospitalWithSpecializationDTO.setAddressDTO(fromAddressToAddressDTO(hospital.getAddress()));
    hospitalWithSpecializationDTO.setWebSite(hospital.getWebSite());
    hospitalWithSpecializationDTO.setEmail(hospital.getEmail());
    hospitalWithSpecializationDTO.setSpecializationDTOs(hospital.getSpecializations().stream().map(s -> fromSpecializationToSpecializationDTO(s)).collect(Collectors.toSet()));
    
    return hospitalWithSpecializationDTO;
  }
  
  public static Hospital fromHospitalWithSpecializationDTOToHospital(HospitalWithSpecializationDTO hospitalWithSpecializationDTO) {
    Hospital hospital = new Hospital();
    hospital.setId(hospitalWithSpecializationDTO.getId());
    hospital.setName(hospitalWithSpecializationDTO.getName());
    hospital.setPhone(hospitalWithSpecializationDTO.getPhone());
    hospital.setUrc(hospitalWithSpecializationDTO.getUrc());
    hospital.setAddress(fromAddressDTOToAddress(hospitalWithSpecializationDTO.getAddressDTO()));
    hospital.setWebSite(hospitalWithSpecializationDTO.getWebSite());
    hospital.setEmail(hospitalWithSpecializationDTO.getEmail());
    hospital.setSpecializations(hospitalWithSpecializationDTO.getSpecializationDTOs().stream().map(s -> fromSpecializationDTOToSpecialization(s)).collect(Collectors.toList()));
    return hospital;
  }
}
