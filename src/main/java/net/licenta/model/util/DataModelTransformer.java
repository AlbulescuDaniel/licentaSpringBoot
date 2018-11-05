package net.licenta.model.util;

import net.licenta.model.dto.AddressDTO;
import net.licenta.model.dto.UserDoctorDTO;
import net.licenta.model.dto.UserPatientDTO;
import net.licenta.model.dto.UserPharmacyDTO;
import net.licenta.model.entity.Address;
import net.licenta.model.entity.UserDoctor;
import net.licenta.model.entity.UserPatient;
import net.licenta.model.entity.UserPharmacy;

public class DataModelTransformer {

  private DataModelTransformer() {
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

    AddressDTO addressDTO = new AddressDTO();
    addressDTO.setCountryName(userPatient.getAddress().getCountryName());
    addressDTO.setPostalCode(userPatient.getAddress().getPostalCode());
    addressDTO.setCity(userPatient.getAddress().getCity());
    addressDTO.setRegion(userPatient.getAddress().getRegion());
    addressDTO.setStreet(userPatient.getAddress().getStreet());
    addressDTO.setStreetNumber(userPatient.getAddress().getStreetNumber());

    userPatientDTO.setAddressDTO(addressDTO);

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

    Address address = new Address();
    address.setCountryName(userPatientDTO.getAddressDTO().getCountryName());
    address.setPostalCode(userPatientDTO.getAddressDTO().getPostalCode());
    address.setCity(userPatientDTO.getAddressDTO().getCity());
    address.setRegion(userPatientDTO.getAddressDTO().getRegion());
    address.setStreet(userPatientDTO.getAddressDTO().getStreet());
    address.setStreetNumber(userPatientDTO.getAddressDTO().getStreetNumber());

    userPatient.setAddress(address);

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

    AddressDTO addressDTO = new AddressDTO();
    addressDTO.setCountryName(userDoctor.getAddress().getCountryName());
    addressDTO.setPostalCode(userDoctor.getAddress().getPostalCode());
    addressDTO.setCity(userDoctor.getAddress().getCity());
    addressDTO.setRegion(userDoctor.getAddress().getRegion());
    addressDTO.setStreet(userDoctor.getAddress().getStreet());
    addressDTO.setStreetNumber(userDoctor.getAddress().getStreetNumber());

    userDoctorDTO.setAddressDTO(addressDTO);

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

    Address address = new Address();
    address.setCountryName(userDoctorDTO.getAddressDTO().getCountryName());
    address.setPostalCode(userDoctorDTO.getAddressDTO().getPostalCode());
    address.setCity(userDoctorDTO.getAddressDTO().getCity());
    address.setRegion(userDoctorDTO.getAddressDTO().getRegion());
    address.setStreet(userDoctorDTO.getAddressDTO().getStreet());
    address.setStreetNumber(userDoctorDTO.getAddressDTO().getStreetNumber());

    userDoctor.setAddress(address);

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

    AddressDTO addressDTO = new AddressDTO();
    addressDTO.setCountryName(userPharmacy.getAddress().getCountryName());
    addressDTO.setPostalCode(userPharmacy.getAddress().getPostalCode());
    addressDTO.setCity(userPharmacy.getAddress().getCity());
    addressDTO.setRegion(userPharmacy.getAddress().getRegion());
    addressDTO.setStreet(userPharmacy.getAddress().getStreet());
    addressDTO.setStreetNumber(userPharmacy.getAddress().getStreetNumber());

    userPharmacyDTO.setAddressDTO(addressDTO);

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

    Address address = new Address();
    address.setCountryName(userPharmacyDTO.getAddressDTO().getCountryName());
    address.setPostalCode(userPharmacyDTO.getAddressDTO().getPostalCode());
    address.setCity(userPharmacyDTO.getAddressDTO().getCity());
    address.setRegion(userPharmacyDTO.getAddressDTO().getRegion());
    address.setStreet(userPharmacyDTO.getAddressDTO().getStreet());
    address.setStreetNumber(userPharmacyDTO.getAddressDTO().getStreetNumber());

    userPharmacy.setAddress(address);

    return userPharmacy;
  }
}
