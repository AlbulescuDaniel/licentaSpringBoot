package net.licenta.model.util;

import java.util.stream.Collectors;

import net.licenta.model.dto.AddressDTO;
import net.licenta.model.dto.DrugDTO;
import net.licenta.model.dto.PrescriptionDTO;
import net.licenta.model.dto.PrescriptionDrugDTO;
import net.licenta.model.dto.UserDoctorDTO;
import net.licenta.model.dto.UserPatientDTO;
import net.licenta.model.dto.UserPharmacyDTO;
import net.licenta.model.entity.Address;
import net.licenta.model.entity.Drug;
import net.licenta.model.entity.Prescription;
import net.licenta.model.entity.PrescriptionDrug;
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
    userPatientDTO.setId(userPatient.getId());
    userPatientDTO.setFirstName(userPatient.getFirstName());
    userPatientDTO.setLastName(userPatient.getLastName());
    userPatientDTO.setUserName(userPatient.getUserName());
    userPatientDTO.setEmail(userPatient.getEmail());
    userPatientDTO.setPhoneNumber(userPatient.getPhoneNumber());
    userPatientDTO.setPassword(userPatient.getPassword());
    userPatientDTO.setAddressDTO(fromAddressToAddressDTO(userPatient.getAddress()));

    return userPatientDTO;
  }

  public static UserPatient fromPatientDTOToPatient(UserPatientDTO userPatientDTO) {
    UserPatient userPatient = new UserPatient();
    userPatient.setId(userPatientDTO.getId());
    userPatient.setFirstName(userPatientDTO.getFirstName());
    userPatient.setLastName(userPatientDTO.getLastName());
    userPatient.setUserName(userPatientDTO.getUserName());
    userPatient.setEmail(userPatientDTO.getEmail());
    userPatient.setPhoneNumber(userPatientDTO.getPhoneNumber());
    userPatient.setPassword(userPatientDTO.getPassword());
    userPatient.setAddress(fromAddressDTOToAddress(userPatientDTO.getAddressDTO()));

    return userPatient;
  }

  public static UserDoctorDTO fromDoctorToDoctorDTO(UserDoctor userDoctor) {
    UserDoctorDTO userDoctorDTO = new UserDoctorDTO();
    userDoctorDTO.setId(userDoctor.getId());
    userDoctorDTO.setFirstName(userDoctor.getFirstName());
    userDoctorDTO.setLastName(userDoctor.getLastName());
    userDoctorDTO.setUserName(userDoctor.getUserName());
    userDoctorDTO.setEmail(userDoctor.getEmail());
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
    drugDTO.setActiveComponent(drug.getActiveComponent());
    drugDTO.setDescription(drug.getDescription());
    drugDTO.setManufacturer(drug.getManufacturer());
    drugDTO.setName(drug.getName());

    return drugDTO;
  }

  public static Drug fromDrugDTOToDrug(DrugDTO drugDTO) {
    Drug drug = new Drug();
    drug.setId(drugDTO.getId());
    drug.setActiveComponent(drugDTO.getActiveComponent());
    drug.setDescription(drugDTO.getDescription());
    drug.setManufacturer(drugDTO.getManufacturer());
    drug.setName(drugDTO.getName());

    return drug;
  }

  public static PrescriptionDrug fromPrescriptionDrugDTOToPrescriptionDrug(PrescriptionDrugDTO prescriptionDrugDTO) {
    PrescriptionDrug prescriptionDrug = new PrescriptionDrug();
    prescriptionDrug.setId(prescriptionDrugDTO.getId());
    prescriptionDrug.setChecked(prescriptionDrugDTO.getChecked());
    prescriptionDrug.setDescription(prescriptionDrugDTO.getDescription());
    prescriptionDrug.setPillsNumber(prescriptionDrugDTO.getPillsNumber());
    prescriptionDrug.setDrug(fromDrugDTOToDrug(prescriptionDrugDTO.getDrugDTO()));

    return prescriptionDrug;
  }

  public static PrescriptionDrugDTO fromPrescriptionDrugToPrescriptionDrugDTO(PrescriptionDrug prescriptionDrug) {
    PrescriptionDrugDTO prescriptionDrugDTO = new PrescriptionDrugDTO();
    prescriptionDrugDTO.setId(prescriptionDrug.getId());
    prescriptionDrugDTO.setChecked(prescriptionDrug.getChecked());
    prescriptionDrugDTO.setDescription(prescriptionDrug.getDescription());
    prescriptionDrugDTO.setPillsNumber(prescriptionDrug.getPillsNumber());
    prescriptionDrugDTO.setDrugDTO(fromDrugToDrugDTO(prescriptionDrug.getDrug()));

    return prescriptionDrugDTO;
  }

  public static PrescriptionDTO fromPrescriptionToPrescriptionDTO(Prescription prescription) {
    PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
    prescriptionDTO.setId(prescription.getId());
    prescriptionDTO.setDiagnostic(prescription.getDiagnostic());
    prescriptionDTO.setDays(prescription.getDays());
    prescriptionDTO.setDatePrescripted(prescription.getDatePrescripted());
    prescriptionDTO.setPrescriptionDrugs(prescription.getPrescriptionDrugs().stream().map(DataModelTransformer::fromPrescriptionDrugToPrescriptionDrugDTO).collect(Collectors.toList()));

    return prescriptionDTO;
  }

  public static Prescription fromPrescriptionDTOToPrescription(PrescriptionDTO prescriptionDTO) {
    Prescription prescription = new Prescription();
    prescription.setId(prescriptionDTO.getId());
    prescription.setDiagnostic(prescriptionDTO.getDiagnostic());
    prescription.setDays(prescriptionDTO.getDays());
    prescription.setDatePrescripted(prescriptionDTO.getDatePrescripted());
    prescription.setPrescriptionDrugs(prescriptionDTO.getPrescriptionDrugs().stream().map(DataModelTransformer::fromPrescriptionDrugDTOToPrescriptionDrug).collect(Collectors.toList()));

    return prescription;
  }
}
