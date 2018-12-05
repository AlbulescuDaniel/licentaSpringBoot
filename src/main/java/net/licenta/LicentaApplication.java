package net.licenta;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.licenta.model.entity.Address;
import net.licenta.model.entity.Drug;
import net.licenta.model.entity.Hospital;
import net.licenta.model.entity.RoleType;
import net.licenta.model.entity.UserDoctor;
import net.licenta.model.entity.UserGender;
import net.licenta.model.entity.UserPatient;
import net.licenta.repository.DoctorRepository;
import net.licenta.repository.DrugRepository;
import net.licenta.repository.HospitalRepository;
import net.licenta.repository.PatientRepository;

@RestController("/test")
@SpringBootApplication
public class LicentaApplication {
  
  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
  private DoctorRepository doctorRepository;
  private PatientRepository patientRepository;
  private DrugRepository drugRepository;
  private HospitalRepository hospitalRepository;

  public static void main(String[] args) {
    SpringApplication.run(LicentaApplication.class, args);
  }

  @GetMapping
  public String test() {
    return "Running";
  }

  /**
   * Bean used to take messages from messages.properties
   * 
   * @param messageSource
   * @return
   */
  @Bean
  public LocalValidatorFactoryBean validator(MessageSource messageSource) {
    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
    validatorFactoryBean.setValidationMessageSource(messageSource);
    return validatorFactoryBean;
  }

  /**
   * Bean used to create default instance for BCryptPasswordEncoder
   * 
   * @return
   */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Bean used to insert dummy objects in database
   */
  @Bean
  CommandLineRunner init(DoctorRepository doctorRepository, PatientRepository patientRepository, DrugRepository drugRepository, HospitalRepository hospitalRepository) {
    this.doctorRepository = doctorRepository;
    this.patientRepository = patientRepository;
    this.drugRepository = drugRepository;
    this.hospitalRepository = hospitalRepository;

    return args -> {

      Address address = new Address();
      address.setCity("Brasov");
      address.setCountryName("Romania");
      address.setPostalCode("12345");
      address.setRegion("Brasov");
      address.setStreet("Infratirii");
      address.setStreetNumber("1");

      UserDoctor userDoctor = new UserDoctor();
      userDoctor.setId(1L);
      userDoctor.setActivated(true);
      userDoctor.setEmail("doctor@doctor@gmail.com");
      userDoctor.setFirstName("doctor");
      userDoctor.setLastName("doctor");
      userDoctor.setPassword(bCryptPasswordEncoder.encode("aA1!aaaa"));
      userDoctor.setPhoneNumber("1234");
      userDoctor.setRoleType(RoleType.DOC);
      userDoctor.setUserName("abc");
      userDoctor.setSpeciality("General");
      userDoctor.setAddress(address);
     
      UserPatient userPatient = new UserPatient();
      userPatient.setId(1L);
      userPatient.setAddress(address);
      userPatient.setBirthDate(LocalDate.now().minusYears(20));
      userPatient.setCnp("1111111111");
      userPatient.setEmail("patient@patient.com");
      userPatient.setFirstName("patient");
      userPatient.setGender(UserGender.Male);
      userPatient.setLastName("patient");
      userPatient.setPassword(bCryptPasswordEncoder.encode("aA1!aaaa"));
      userPatient.setPhoneNumber("43211");
      userPatient.setUserName("asd");
      userPatient.setRoleType(RoleType.PAT);

      Drug drug = new Drug();
      drug.setActiveComponent("malai");
      drug.setDescription("mamaliga");
      drug.setId(1L);
      drug.setManufacturer("moara cu noroc");
      drug.setName("malai");
      
      Hospital hospital = new Hospital();
      hospital.setId(1L);
      hospital.setAddress(address);
      hospital.setName("Judetean");
      hospital.setPhone("0268  320022");
      hospital.setUrc("001927812");
      hospital.setWebSite("http://www.hospbv.ro/");
      hospital.setEmail("sjbrasov@rdslink.ro");
      
      this.doctorRepository.save(userDoctor);
      System.out.println("Doctors: " + this.doctorRepository.count());
      
      this.hospitalRepository.save(hospital);
      System.out.println("Hospitals: " + this.hospitalRepository.count());
      
      userDoctor.setHospital(hospital);
      this.doctorRepository.save(userDoctor);
      
      hospital.getDoctors().add(userDoctor);
      this.hospitalRepository.save(hospital);
      
      this.patientRepository.save(userPatient);
      System.out.println("Patients: " + this.patientRepository.count());
      
      this.drugRepository.save(drug);
      System.out.println("Drugs: " + this.drugRepository.count());
    };
  }
}
