package net.licenta.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.licenta.Constants;
import net.licenta.email.RegisterMailContentBuilder;
import net.licenta.error.ErrorDetailsBadRequest;
import net.licenta.model.dto.CreatePatientDTO;
import net.licenta.model.dto.UserPatientDTO;
import net.licenta.model.entity.UserPatient;
import net.licenta.model.util.DataModelTransformer;
import net.licenta.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  @Autowired
  PatientRepository patientRepository;

  @Autowired
  RegisterMailContentBuilder registerMailContentBuilder;

  @Autowired
  public JavaMailSender emailSender;

  @Override
  public Set<UserPatientDTO> getAllPatients() {
    return patientRepository.findAll().stream().map(DataModelTransformer::fromPatientToPatientDTO).collect(Collectors.toSet());
  }

  @Override
  public Optional<UserPatientDTO> getPatientById(Long id) {
    return patientRepository.findById(id).map(entity -> Optional.ofNullable(DataModelTransformer.fromPatientToPatientDTO(entity))).orElseGet(Optional::empty);
  }

  @Override
  public Optional<UserPatientDTO> createPatient(CreatePatientDTO createPatientDTO) {
    UserPatient patient = DataModelTransformer.fromCreatePatientDTOToPatient(createPatientDTO);

    String userName = createPatientDTO.getFirstName() + "." + createPatientDTO.getLastName();

    patient.setUserName(userName);
    int count = 0;
    while (patientRepository.findByUserNameIgnoreCase(userName).isPresent()) {
      count++;
      userName = createPatientDTO.getFirstName() + "." + createPatientDTO.getLastName() + count;
      patient.setUserName(userName);
    }

    String randomPassword = RandomStringUtils.randomAlphanumeric(8);
    patient.setPassword(bCryptPasswordEncoder.encode(randomPassword));

    Optional<UserPatientDTO> optional = Optional.ofNullable(DataModelTransformer.fromPatientToPatientDTO(patientRepository.save(patient)));

    if (optional.isPresent() && sendMail(createPatientDTO.getEmail(), patient.getUserName(), randomPassword)) {
      return optional;
    }
    else {
      patientRepository.delete(patient);
      throw new ErrorDetailsBadRequest(LocalDateTime.now(), Constants.EMAIL_ERROR, ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE).getString(Constants.BUNDLE_EMAIL_SEND_FAILED));
    }
  }

  @Override
  public Optional<UserPatientDTO> updatePatient(Long id, UserPatientDTO userPatientDTO) {
    return patientRepository.findById(id).map(entity -> {
      UserPatient userPatient = DataModelTransformer.fromPatientDTOToPatient(userPatientDTO);
      BeanUtils.copyProperties(userPatient, entity);
      entity.setId(id);
      return Optional.of(DataModelTransformer.fromPatientToPatientDTO(patientRepository.save(entity)));
    }).orElseGet(Optional::empty);
  }

  @Override
  public Boolean deletePatientById(Long id) {
    return patientRepository.findById(id).map(entity -> {
      patientRepository.deleteById(id);
      return true;
    }).orElseGet(() -> false);
  }

  @Override
  public Boolean deleteAllPatients() {
    patientRepository.deleteAll();
    return patientRepository.count() == 0L;
  }

  public Boolean sendMail(String to, String userName, String passsword) {
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setTo(to);
      messageHelper.setSubject("Your account is ready!");
      String content = registerMailContentBuilder.build(userName, passsword);
      messageHelper.setText(content, true);
    };
    try {
      emailSender.send(messagePreparator);
      return true;
    }
    catch (MailException e) {
      return false;
    }
  }

  @Override
  public Optional<UserPatientDTO> getPatientByUsername(String userName) {
    return patientRepository.findByUserNameIgnoreCase(userName).map(entity -> Optional.ofNullable(DataModelTransformer.fromPatientToPatientDTO(entity))).orElseGet(Optional::empty);
  }
}
