package net.licenta.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.licenta.Constants;
import net.licenta.model.entity.User;
import net.licenta.model.entity.UserDoctor;
import net.licenta.model.entity.UserPatient;
import net.licenta.model.entity.UserPharmacy;
import net.licenta.repository.DoctorRepository;
import net.licenta.repository.PatientRepository;
import net.licenta.repository.PharmacyRepository;

@Service
public class LoginService implements UserDetailsService {

  @Autowired
  PatientRepository patientRepository;

  @Autowired
  DoctorRepository doctorRepository;

  @Autowired
  PharmacyRepository pharmacyRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {

    Optional<UserPatient> userPatient = patientRepository.findByUserName(username);
    if (userPatient.isPresent()) {
      return new org.springframework.security.core.userdetails.User(userPatient.get().getUserName(), userPatient.get().getPassword(), getAuthority(userPatient.get()));
    }
    Optional<UserDoctor> userDoctor = doctorRepository.findByUserName(username);
    if (userDoctor.isPresent()) {
      return new org.springframework.security.core.userdetails.User(userDoctor.get().getUserName(), userDoctor.get().getPassword(), getAuthority(userDoctor.get()));
    }
    Optional<UserPharmacy> userPharmacy = pharmacyRepository.findByUserName(username);
    if (userPharmacy.isPresent()) {
      return new org.springframework.security.core.userdetails.User(userPharmacy.get().getUserName(), userPharmacy.get().getPassword(), getAuthority(userPharmacy.get()));
    }

    throw new UsernameNotFoundException(ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE).getString(Constants.BUNDLE_USER_ID_NOT_FOUND));
  }

  private Set<SimpleGrantedAuthority> getAuthority(User user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    System.err.println(user.getRoleType().toString());
    authorities.add(new SimpleGrantedAuthority(user.getRoleType().toString()));
    return authorities;
  }

}
