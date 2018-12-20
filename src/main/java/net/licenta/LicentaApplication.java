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
      userDoctor.setFirstName("Doctor");
      userDoctor.setLastName("Doctor");
      userDoctor.setPassword(bCryptPasswordEncoder.encode("aA1!aaaa"));
      userDoctor.setPhoneNumber("1234");
      userDoctor.setRoleType(RoleType.DOC);
      userDoctor.setUserName("Doctor.Name");
      userDoctor.setSpeciality("General");
      userDoctor.setAddress(address);

      UserPatient userPatient = new UserPatient();
      userPatient.setId(1L);
      userPatient.setAddress(address);
      userPatient.setBirthDate(LocalDate.now().minusYears(20));
      userPatient.setCnp("1111111111");
      userPatient.setEmail("patient@patient.com");
      userPatient.setFirstName("Patient");
      userPatient.setGender(UserGender.Male);
      userPatient.setLastName("Patient");
      userPatient.setPassword(bCryptPasswordEncoder.encode("aA1!aaaa"));
      userPatient.setPhoneNumber("43211");
      userPatient.setUserName("Patient.Name");
      userPatient.setRoleType(RoleType.PAT);
      
      UserPatient userPatient2 = new UserPatient();
      userPatient.setId(2L);
      userPatient.setAddress(address);
      userPatient.setBirthDate(LocalDate.now().minusYears(20));
      userPatient.setCnp("1111111111");
      userPatient.setEmail("patient@patient.com");
      userPatient.setFirstName("Daniel");
      userPatient.setGender(UserGender.Male);
      userPatient.setLastName("Daniel");
      userPatient.setPassword(bCryptPasswordEncoder.encode("aA1!aaaa"));
      userPatient.setPhoneNumber("43211");
      userPatient.setUserName("Daniel.Daniel");
      userPatient.setRoleType(RoleType.PAT);

      Drug nurofen200mg = new Drug();
      nurofen200mg.setId(1L);
      nurofen200mg.setName("Nurofen 200 mg");
      nurofen200mg.setAdministrationMethod("Adults, including the elderly, and children aged 16 years and over: One or two tablets up to four times daily as required.\n" + "Children:\n"
          + "10-15 years: One tablet up to four times daily as required.\n" + "Not suitable for children under 10 years of age.\n"
          + "Children should not be given Panadol Advance 500 mg Tablets for more than 3 days without consulting a doctor.\n"
          + "These doses should not be repeated more frequently than every four hours nor should more than four doses be given in any 24 hour period.\n" + "Oral administration only. ");

      nurofen200mg.setComposition("Ibuprofen 200 mg\n" + "Excipient(s) with known effect:\n" + "Sucrose\n" + "Sodium ");

      nurofen200mg.setContraindications("Hypersensitivity to ibuprofen or any of the excipients in the product.\n"
          + "Patients who have previously shown hypersensitivity reactions (e.g. asthma, rhinitis, angioedema, or urticaria) in response to aspirin or other non-steroidal anti-inflammatory drugs.\n"
          + "Active or history of recurrent peptic ulcer/haemorrhage (two or more distinct episodes of proven ulceration or bleeding).\n"
          + "History of gastrointestinal bleeding or perforation, related to previous NSAIDs therapy.\n" + "Severe heart failure (NYHA Class IV), renal failure or hepatic failure (see section 4.4)\n"
          + "Last trimester of pregnancy");

      nurofen200mg.setExcipients("Croscarmellose Sodium\n" + "Sodium Laurilsulfate\n" + "Sodium Citrate\n" + "Stearic Acid\n" + "Colloidal Anhydrous Silica\n" + "Carmellose Sodium\n"
          + "French Chalk for Tablets (Talc)\n" + "Acacia Spray Dried\n" + "Sucrose\n" + "Titanium Dioxide\n" + "Macrogol 6000");

      nurofen200mg.setIncompatibilities("Not applicable");

      nurofen200mg.setMarketingAuthorisationHolder("Reckitt Benckiser Healthcare (UK) Ltd\n" + "Slough\n" + "SL1 4AQ");

      nurofen200mg.setOverdose("In children ingestion of more than 400mg/kg may cause symptoms. In adults the dose response effect is less clear cut. The half-life in overdose is 1.5-3 hours.");
      nurofen200mg.setPharmaceuticalForm("Coated Tablet\n" + "A white to off-white, biconvex, round, sugar coated tablet printed 'Nurofen' in black on one face.");
      nurofen200mg.setPharmacokineticProperties(
          "Ibuprofen is a propionic acid derivative NSAID that has demonstrated its efficacy by inhibition of prostaglandin synthesis. In humans, ibuprofen reduces inflammatory pain, swellings and fever. Furthermore, ibuprofen reversibly inhibits platelet aggregation.\n"
              + "Experimental data suggest that ibuprofen may competitively inhibit the effect of low dose aspirin (acetylsalicylic acid) on platelet aggregation when they are dosed concomitantly. Some pharmacodynamics studies show that when single doses of ibuprofen 400mg was taken within 8 h before or within 30 min after immediate release aspirin (acetylsalicylic acid) dosing (81mg), a decreased effect of (acetylsalicylic acid) on the formation of thromboxane or platelet aggregation occurred. Although there are uncertainties regarding extrapolation of these data to the clinical situation, the possibility that regular, long-term use of ibuprofen may reduce the cardioprotective effect of low-dose acetylsalicylic acid cannot be excluded. No clinically relevant effect is considered to be likely for occasional ibuprofen use (see section 4.5).");
      nurofen200mg.setShelfLife("24 months");
      nurofen200mg.setSpecialPrecautionsForStorage("Do not store above 25°C\n" + "Store in the original pack");
      nurofen200mg.setSpecialWarnings(
          "Undesirable effects may be minimised by using the lowest effective dose for the shortest duration necessary to control symptoms (see section 4.2 and GI and cardiovascular risks below).\n"
              + "The elderly have an increased frequency of adverse reactions to NSAIDs especially gastrointestinal bleeding and perforation which may be fatal.");
      nurofen200mg.setTherapeuticIndications("For the relief of migraine-headaches, backache, dental pain, neuralgia and period pains as well as rheumatic and muscular pains.\n"
          + "Nurofen relieves pain and reduces inflammation and temperature as well as relieving headaches and other types of pain. It also relieves cold and flu symptoms.");

      Drug nurofen400mg = new Drug();
      nurofen400mg.setId(3L);
      nurofen400mg.setAdministrationMethod("For oral administration and short-term use only.\n" + "Adults, the elderly and children and adolescents between 12 and 18 years:\n"
          + "Undesirable effects may be minimised by using the lowest effective dose for the shortest duration necessary to control symptoms (see section 4.4).\n"
          + "If in children and adolescents this medicinal product is required for more than 3 days, or if symptoms worsen a doctor should be consulted.\n"
          + "Adults should consult a doctor if symptoms persist or worsen, or if the product is required for more than 10 days.\n"
          + "Children and Adolescents between 12 and 18 years: Take 1 capsule with water, up to three times a day as required.\n"
          + "Adults: Take 1 capsule with water, up to three times a day as required.\n" + "Leave at least 4 hours between doses.\n" + "Do not take more than 3 capsules in any 24 hour period.\n" + "");
      nurofen400mg.setComposition("Each capsule, soft contains Ibuprofen 400 mg\n" + "Excipients with known effect:\n" + "Sorbitol\n" + "Ponceau 4R\n" + "Potassium hydroxide 50% solution (E525)");
      nurofen400mg.setContraindications("Hypersensitivity to ibuprofen or any of the excipients in the product.\n"
          + "Patients who have previously shown hypersensitivity reactions (e.g. asthma, rhinitis, angioderma or urticaria) in response to aspirin or other non steroidal anti-inflammatory drugs.\n"
          + "Active or history of recurrent peptic ulcer/haemorrhage (two or more distinct episodes of proven ulceration or bleeding).\n"
          + "History of gastrointestinal bleeding or perforation, related to previous NSAIDs therapy.\n" + "Severe heart failure (NYHA Class IV), renal failure or hepatic failure (See Section 4.4)\n"
          + "Last trimester of pregnancy\n" + "");
      nurofen400mg
          .setExcipients(" Capsule fill\n" + "Macrogol 600\n" + "Potassium hydroxide 50% solution (E525)\n" + "\n" + "Capsule shell\n" + "Gelatin\n" + "Sorbitol Liquid, Partially Dehydrated (420)\n"
              + "Purified Water\n" + "Ponceau 4R (E124)\n" + "Lecithin (E322)\n" + "Triglycerides , medium chain\n" + "\n" + "Capsule printing\n" + "Ethanol\n" + "White ink *\n"
              + "The ink contains the following residual materials after application: Titanium Dioxide (E171), Polyvinyl Acetate Phthalate, Macrogol 400, ammonium hydroxide (E527).");
      nurofen400mg.setMarketingAuthorisationHolder("Reckitt Benckiser Healthcare (UK) Ltd\n" + "Slough\n" + "SL1 4AQ\n" + "");
      nurofen400mg.setName("Nurofen Express 400 mg");
      nurofen400mg.setOverdose("In children ingestion of more than 400 mg/kg may cause symptoms. In adults the dose response effect is less clear cut. The half-life in overdose is 1.5-3 hours.");
      nurofen400mg.setPharmaceuticalForm(" Capsule, soft.\n" + "A clear red oval soft gelatin capsule printed with an identifying logo in white .");
      nurofen400mg.setPharmacokineticProperties("Ibuprofen is well absorbed from the gastrointestinal tract. Ibuprofen is extensively bound to plasma proteins.\n"
          + "Nurofen Extra Strength 400 mg Liquid Capsules consist of ibuprofen 400 mg dissolved in a hydrophilic solvent inside a gelatin shell. On ingestion, the gelatin shell disintegrates in the gastric juice releasing the solubilised ibuprofen immediately for absorption. The median peak plasma concentration is achieved in approximately 30 minutes after administration when taken on an empty stomach.\n"
          + "The median peak plasma concentration for Nurofen tablets is achieved approximately 1-2 hours after administration. A direct comparison of the 400 mg ibuprofen capsule with 2x200 mg Nurofen tablets showed that the median peak plasma concentration was achieved more than twice as fast for the liquid capsule (32.5 min) compared to the tablets (90 min).When taken with food, peak plasma levels may be delayed.\n"
          + "Ibuprofen is metabolised in the liver to two major metabolites with primary excretion via the kidneys, either as such or as major conjugates, together with a negligible amount of unchanged ibuprofen. Excretion by the kidney is both rapid and complete.\n"
          + "Elimination half-life is approximately 2 hours.\n" + "No significant differences in pharmacokinetic profile are observed in the elderly.\n"
          + "In limited studies, ibuprofen appears in the breast milk in very low concentrations.\n" + "");
      nurofen400mg.setShelfLife("24 months.");
      nurofen400mg.setSpecialPrecautionsForStorage("Store below 25°C.");
      nurofen400mg.setSpecialWarnings("\n"
          + "Undesirable effects may be minimised by using the lowest effective dose for the shortest duration necessary to control symptoms (see GI and cardiovascular risks below).\n"
          + "The elderly have an increased frequency of adverse reactions to NSAIDs, especially gastrointestinal bleeding and perforation, which may be fatal.\n" + "\n" + "Respiratory:\n"
          + "Bronchospasm may be precipitated in patients suffering from, or with a history of, bronchial asthma or allergic disease.\n" + "\n" + "Other NSAIDs:\n"
          + "The use of ibuprofen with concomitant NSAIDs including cyclooxygenase-2 selective inhibitors should be avoided (see section 4.5).\n" + "\n" + "SLE and mixed connective tissue disease:\n"
          + "Systemic lupus erythematosus as well as those with mixed connective tissue disease – increased risk of aseptic meningitis (see section 4.8).\n" + "\n" + "Renal:\n" + "\n"
          + "Renal impairment as renal function may further deteriorate (see sections 4.3 and 4.8).\n" + "There is a risk of renal impairment in dehydrated children and adolescents\n" + "\n"
          + "Hepatic:\n" + "\n" + "Hepatic dysfunction (see sections 4.3 and 4.8).\n" + "Cardiovascular and cerebrovascular effects\n"
          + "Caution (discussion with doctor or pharmacist) is required prior to starting treatment in patients with a history of hypertension and/or heart failure as fluid retention, hypertension and oedema have been reported in association with NSAID therapy.\n"
          + "Clinical studies suggest that use of ibuprofen, particularly at a high dose (2400mg/day) may be associated with a small increased risk of arterial thrombotic events (for example myocardial infarction or stroke). Overall, epidemiological studies do not suggest that low dose ibuprofen (e.g. ≤ 1200mg/day) is associated with an increased risk of arterial thrombotic events.\n"
          + "Patients with uncontrolled hypertension, congestive heart failure (NYHA II-III), established ischaemic heart disease, peripheral arterial disease, and/or cerebrovascular disease should only be treated with ibuprofen after careful consideration and high doses (2400 mg/day) should be avoided.\n"
          + "Careful consideration should also be exercised before initiating long-term treatment of patients with risk factors for cardiovascular events (e.g. hypertension, hyperlipidaemia, diabetes mellitus, smoking), particularly if high doses of ibuprofen (2400 mg/day) are required.\n"
          + "\n" + "Impaired female fertility:\n"
          + "There is limited evidence that drugs which inhibit cyclooxygenase/ prostaglandin synthesis may cause impairment of female fertility by an effect on ovulation. This is reversible upon withdrawal of treatment.\n"
          + "\n" + "Gastrointestinal:\n"
          + "NSAIDs should be given with care to patients with a history of gastrointestinal disease (ulcerative colitis, Crohn's disease) as these conditions may be exacerbated (see section 4.8).\n"
          + "GI bleeding, ulceration or perforation, which can be fatal has been reported with all NSAIDs at any time during treatment, with or without warning symptoms or a previous history of GI events.\n"
          + "The risk of GI bleeding, ulceration or perforation is higher with increasing NSAID doses, in patients with a history of ulcer, particularly if complicated with haemorrhage or perforation (see section 4.3), and in the elderly. These patients should commence treatment on the lowest dose available.\n"
          + "Patients with a history of GI toxicity, particularly the elderly, should report any unusual abdominal symptoms (especially GI bleeding) particularly in the initial stages of treatment.\n"
          + "Caution should be advised in patients receiving concomitant medications which could increase the risk of ulceration or bleeding, such as oral corticosteroids, anticoagulants such as warfarin, selective serotonin-reuptake inhibitors or antiplatelet agents such as aspirin (see section 4.5).\n"
          + "When GI bleeding or ulceration occurs in patients receiving ibuprofen, the treatment should be withdrawn.\n" + "\n" + "Dermatological:\n"
          + "Serious skin reactions, some of them fatal, including exfoliative dermatitis, Stevens-Johnson syndrome and toxic epidermal necrolysis, have been reported very rarely in association with the use of NSAIDs (see section 4.8). Patients appear to be at highest risk of these reactions early in the course of therapy, the onset of the reaction occurring in the majority of cases within the first month of treatment. Ibuprofen should be discontinued at the first appearance of skin rash, mucosal lesions, or any other sign of hypersensitivity.\n"
          + "This medicine contains 27.9 mg potassium per capsule. To be taken into consideration by patients with reduced kidney function or patients on a controlled potassium diet.\n"
          + "Contains Sorbitol. Patients with rare hereditary problems of fructose intolerance should not take this medicine.\n"
          + "Also contains Ponceau 4R (E124) which may cause allergic reactions.\n" + "The label will include:\n" + "Read the enclosed leaflet before taking this product\n" + "Do not take if you:\n"
          + "• have (or have had two or more episodes of ) a stomach ulcer, perforation or bleeding\n" + "• are allergic to ibuprofen, to any of the ingredients, or to aspirin or other painkillers\n"
          + "• are taking other NSAID pain killers or aspirin with a daily dose above 75mg\n" + "Speak to a pharmacist or your doctor before taking if you:\n"
          + "• have or have had asthma, diabetes, high cholesterol, high blood pressure, a stroke, heart, liver, kidney or bowel problems or are dehydrated\n" + "• are a smoker\n" + "• are pregnant\n"
          + "If symptoms persist or worsen, or if new symptoms occur, consult your doctor or pharmacist.\n" + "");
      nurofen400mg.setTherapeuticIndications("Adults, elderly and Children over 12 years:\n"
          + "Nurofen Extra Strength 400 mg Liquid Capsules are indicated for symptomatic relief of non-serious arthritic conditions, rheumatic or muscular pain, backache, neuralgia , migraine, headaches, dental pain, dysmenorrhoea, feverishness, colds and influenza.\n"
          + "");

      Drug panadol = new Drug();
      panadol.setId(2L);
      panadol.setAdministrationMethod("Adults, including the elderly, and children aged 16 years and over: One or two tablets up to four times daily as required.\n" + "Children:\n"
          + "10-15 years: One tablet up to four times daily as required.\n" + "Not suitable for children under 10 years of age.\n"
          + "Children should not be given Panadol Advance 500 mg Tablets for more than 3 days without consulting a doctor.\n"
          + "These doses should not be repeated more frequently than every four hours nor should more than four doses be given in any 24 hour period.\n" + "Oral administration only. ");
      panadol.setComposition("Each tablet contains Paracetamol 500.0 mg ");
      panadol.setContraindications("Hypersensitivity to paracetamol or any of the other constituents.");
      panadol.setExcipients(" Tablet core:\n" + "Pregelatinised starch\n" + "Calcium carbonate\n" + "Alginic acid\n" + "Crospovidone\n" + "Povidone (K-25)\n" + "Magnesium stearate\n"
          + "Colloidal anhydrous silica\n" + "Parahydroxybenzoates (sodium methyl parahydroxybenzoate (E219), sodium ethyl parahydroxybenzoate (E215) and sodium propyl parahydroxybenzoate (E217))\n"
          + "\nFilm coat and polish:\n" + "Opadry white (YS-1-7003)\n" + "Carnauba wax\n" + "Purified water ");
      panadol.setIncompatibilities("Not applicable. ");
      panadol.setMarketingAuthorisationHolder(
          "GlaxoSmithKline Consumer Healthcare (UK) Trading Limited\n" + "980 Great West Road\n" + "Brentford\n" + "Middlesex\n" + "TW8 9GS\n" + "United Kingdom\n" + "");
      panadol.setName("Panadol Advance 500 mg Tablets");
      panadol.setOverdose(
          "Liver damage is possible in adults who have taken 10g or more of paracetamol. Ingestion of 5g or more of paracetamol may lead to liver damage if the patient has risk factors (see below). ");
      panadol.setPharmaceuticalForm(
          "Film-coated tablet\n" + "White to off-white film-coated capsule-shaped tablet with flat edges, debossed with a 'P' within a circle on one face and with a breakline on one side.\n" + "");
      panadol.setPharmacokineticProperties(
          "Paracetamol is rapidly and almost completely absorbed from the gastrointestinal tract. The concentration in plasma reaches a peak in 30 to 60 minutes and the plasma half-life is 1 - 4 hours after therapeutic doses. Paracetamol is relatively uniformly distributed throughout most body fluids. Binding of the drug to plasma proteins is variable; 20 to 30% may be bound at the concentrations encountered during acute intoxication. Following therapeutic doses 90 - 100% of the drug may be recovered in the urine within the first day. However, practically no paracetamol is excreted unchanged and the bulk is excreted after hepatic conjugation.\n"
              + "Panadol Advance 500 mg Tablets contain a disintegrant system which accelerates tablet dissolution compared to standard paracetamol tablets.\n"
              + "Human scintigraphy data demonstrate that Panadol Advance 500 mg Tablets generally start to disintegrate by 5 minutes post dose in the stomach. There is also less between-subject and less within- subject variability (p<0.0001) in early absorption of paracetamol from Panadol Advance 500 mg Tablets compared to standard paracetamol tablets.\n"
              + "Human pharmacokinetic data demonstrate that the time taken to reach plasma paracetamol therapeutic threshold (4-7mcg/ml) is at least 37% faster with Panadol Advance 500 mg Tablets compared to standard paracetamol tablets (P<0.05).\n"
              + "Total extent of absorption of paracetamol from Panadol Advance 500 mg Tablets is equivalent to that from standard paracetamol tablets. ");
      panadol.setShelfLife("36 months. ");
      panadol.setSpecialPrecautionsForStorage("None. ");
      panadol.setSpecialWarnings(
          "Care is advised in the administration of paracetamol to patients with renal or hepatic impairment. The hazard of overdose is greater in those with noncirrhotic alcoholic liver disease.\n"
              + "Do not exceed the stated dose.\n" + "Patients should be advised to consult their doctor if their headaches become persistent.\n"
              + "Patients should be advised not to take other paracetamol-containing products concurrently.\n"
              + "Patients should be advised to consult a doctor if they suffer from non-serious arthritis and need to take painkillers every day.\n"
              + "Sodium methyl-, sodium ethyl- and sodium propyl- parahydroxybenzoates (E219, E215, E217) may cause allergic reactions (possibly delayed).\n"
              + "If symptoms persist consult your doctor.\n" + "Keep out of the reach and sight of children.\n" + "\nPack Label:\n"
              + "Immediate medical advice should be sought in the event of an overdose, even if you feel well.\n" + "Do not take with any other paracetamol-containing products.\n"
              + "\nPatient Information Leaflet:\n"
              + "Immediate medical advice should be sought in the event of an overdose, even if you feel well, because of the risk of delayed, serious liver damage. ");
      panadol.setTherapeuticIndications(
          "Panadol Advance 500 mg Tablets are a mild analgesic and antipyretic, and are recommended for the treatment of most painful and febrile conditions, for example, headache including migraine and tension headaches, toothache, backache, rheumatic and muscle pains, dysmenorrhoea, sore throat, and for relieving the fever, aches and pains of colds and flu. Also recommended for the symptomatic relief of pain due to non-serious arthritis. ");

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
      this.patientRepository.save(userPatient2);
      System.out.println("Patients: " + this.patientRepository.count());

      this.drugRepository.save(nurofen200mg);
      this.drugRepository.save(panadol);
      this.drugRepository.save(nurofen400mg);
      System.out.println("Drugs: " + this.drugRepository.count());
    };
  }
}
