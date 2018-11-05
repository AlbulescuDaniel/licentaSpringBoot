package net.licenta.model.util;

import net.licenta.model.dto.AddressDTO;
import net.licenta.model.dto.UserPatientDTO;
import net.licenta.model.entity.Address;
import net.licenta.model.entity.UserPatient;

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
}
