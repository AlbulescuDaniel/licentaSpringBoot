package net.licenta.model.dto;

public class DrugDTO {

  private Long id;
  private String name;
  private String composition;
  private String pharmaceuticalForm;
  private String therapeuticIndications;
  private String administrationMethod;
  private String contraindications;
  private String specialWarnings;
  private String overdose;
  private String pharmacokineticProperties;
  private String excipients;
  private String incompatibilities;
  private String shelfLife;
  private String specialPrecautionsForStorage;
  private String marketingAuthorisationHolder;

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

  @Override
  public String toString() {
    return "DrugDTO [id=" + id + ", name=" + name + ", composition=" + composition + ", pharmaceuticalForm=" + pharmaceuticalForm + ", therapeuticIndications=" + therapeuticIndications
        + ", administrationMethod=" + administrationMethod + ", contraindications=" + contraindications + ", specialWarnings=" + specialWarnings + ", overdose=" + overdose
        + ", pharmacokineticProperties=" + pharmacokineticProperties + ", excipients=" + excipients + ", incompatibilities=" + incompatibilities + ", shelfLife=" + shelfLife
        + ", specialPrecautionsForStorage=" + specialPrecautionsForStorage + ", marketingAuthorisationHolder=" + marketingAuthorisationHolder + "]";
  }
}
