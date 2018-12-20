package net.licenta.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "T_DRUG")
@Entity
public class Drug {

  @Id
  @Column(name = "ID_DRUG")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 256)
  private String name;
  
  @Column(length = 16224)
  private String composition;
  
  @Column(length = 16224)
  private String pharmaceuticalForm;
  
  @Column(length = 16224)
  private String therapeuticIndications;
  
  @Column(name = "ADMINISTRATION_METHOD", length = 16224)
  private String administrationMethod;
  
  @Column(name = "CONTRAINDICATIONS", length = 16224)
  private String contraindications;
  
  @Column(length = 16224)
  private String specialWarnings;
  
  @Column(length = 16224)
  private String overdose;
  
  @Column(length = 16224)
  private String pharmacokineticProperties;
  
  @Column(length = 16224)
  private String excipients;
  
  @Column(length = 16224)
  private String incompatibilities;
  
  @Column(length = 16224)
  private String shelfLife;
  
  @Column(length = 16224)
  private String specialPrecautionsForStorage;
  
  @Column(length = 16224)
  private String marketingAuthorisationHolder;
  
  @OneToMany(mappedBy = "drug")
  private List<PrescriptionDrug> prescriptionDrugs;

  public Drug() {
    super();
  }

  public Drug(String name, String composition, String pharmaceuticalForm, String therapeuticIndications, String administrationMethod, String contraindications, String specialWarnings, String overdose,
      String pharmacokineticProperties, String excipients, String incompatibilities, String shelfLife, String specialPrecautionsForStorage, String marketingAuthorisationHolder,
      List<PrescriptionDrug> prescriptionDrugs) {
    super();
    this.name = name;
    this.composition = composition;
    this.pharmaceuticalForm = pharmaceuticalForm;
    this.therapeuticIndications = therapeuticIndications;
    this.administrationMethod = administrationMethod;
    this.contraindications = contraindications;
    this.specialWarnings = specialWarnings;
    this.overdose = overdose;
    this.pharmacokineticProperties = pharmacokineticProperties;
    this.excipients = excipients;
    this.incompatibilities = incompatibilities;
    this.shelfLife = shelfLife;
    this.specialPrecautionsForStorage = specialPrecautionsForStorage;
    this.marketingAuthorisationHolder = marketingAuthorisationHolder;
    this.prescriptionDrugs = prescriptionDrugs;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComposition() {
    return composition;
  }

  public void setComposition(String composition) {
    this.composition = composition;
  }

  public String getPharmaceuticalForm() {
    return pharmaceuticalForm;
  }

  public void setPharmaceuticalForm(String pharmaceuticalForm) {
    this.pharmaceuticalForm = pharmaceuticalForm;
  }

  public String getTherapeuticIndications() {
    return therapeuticIndications;
  }

  public void setTherapeuticIndications(String therapeuticIndications) {
    this.therapeuticIndications = therapeuticIndications;
  }

  public String getAdministrationMethod() {
    return administrationMethod;
  }

  public void setAdministrationMethod(String administrationMethod) {
    this.administrationMethod = administrationMethod;
  }

  public String getContraindications() {
    return contraindications;
  }

  public void setContraindications(String contraindications) {
    this.contraindications = contraindications;
  }

  public String getSpecialWarnings() {
    return specialWarnings;
  }

  public void setSpecialWarnings(String specialWarnings) {
    this.specialWarnings = specialWarnings;
  }

  public String getOverdose() {
    return overdose;
  }

  public void setOverdose(String overdose) {
    this.overdose = overdose;
  }

  public String getPharmacokineticProperties() {
    return pharmacokineticProperties;
  }

  public void setPharmacokineticProperties(String pharmacokineticProperties) {
    this.pharmacokineticProperties = pharmacokineticProperties;
  }

  public String getExcipients() {
    return excipients;
  }

  public void setExcipients(String excipients) {
    this.excipients = excipients;
  }

  public String getIncompatibilities() {
    return incompatibilities;
  }

  public void setIncompatibilities(String incompatibilities) {
    this.incompatibilities = incompatibilities;
  }

  public String getShelfLife() {
    return shelfLife;
  }

  public void setShelfLife(String shelfLife) {
    this.shelfLife = shelfLife;
  }

  public String getSpecialPrecautionsForStorage() {
    return specialPrecautionsForStorage;
  }

  public void setSpecialPrecautionsForStorage(String specialPrecautionsForStorage) {
    this.specialPrecautionsForStorage = specialPrecautionsForStorage;
  }

  public String getMarketingAuthorisationHolder() {
    return marketingAuthorisationHolder;
  }

  public void setMarketingAuthorisationHolder(String marketingAuthorisationHolder) {
    this.marketingAuthorisationHolder = marketingAuthorisationHolder;
  }

  public List<PrescriptionDrug> getPrescriptionDrugs() {
    return prescriptionDrugs;
  }

  public void setPrescriptionDrugs(List<PrescriptionDrug> prescriptionDrugs) {
    this.prescriptionDrugs = prescriptionDrugs;
  }
}
