package net.licenta.model.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HOSPITAL")
public class Hospital {

  @Id
  @Column(name = "ID_HOSPITAL")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "URC")
  private String urc;

  @Column(name = "PHONE")
  private String phone;
  
  @Column
  private String email;
  
  @Column
  private String webSite;

  @Embedded
  Address address;

  @OneToMany(mappedBy = "hospital", cascade = CascadeType.DETACH)
  private Set<UserDoctor> doctors;
  
  @ManyToMany(cascade = CascadeType.DETACH , fetch = FetchType.EAGER)
  @JoinTable(name = "hospital_specialization", joinColumns = @JoinColumn(name = "id_hospital", referencedColumnName = "id_hospital"), inverseJoinColumns = @JoinColumn(name = "id_specialization", referencedColumnName = "id_specialization"))
  private List<Specialization> specializations = new ArrayList<>();

  public Hospital() {
    super();
    doctors = new HashSet<>();
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

  public String getUrc() {
    return urc;
  }

  public void setUrc(String urc) {
    this.urc = urc;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public Set<UserDoctor> getDoctors() {
    return doctors;
  }

  public void setDoctors(Set<UserDoctor> doctors) {
    this.doctors = doctors;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Specialization> getSpecializations() {
    return specializations;
  }

  public void setSpecializations(List<Specialization> specializations) {
    this.specializations = specializations;
  }
}
