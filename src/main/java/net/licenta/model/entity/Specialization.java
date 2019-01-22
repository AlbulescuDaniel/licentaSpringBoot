package net.licenta.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Specialization {

  @Id
  @Column(name = "ID_SPECIALIZATION")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 256)
  private String name;

  @JsonIgnore
  @ManyToMany(mappedBy = "specializations", fetch = FetchType.EAGER)
  private List<Hospital> hospitals = new ArrayList<>();
  
  public Specialization() {
    super();
  }

  public Specialization(String name) {
    super();
    this.name = name;
  }

  public Specialization(Long id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public List<Hospital> getHospitals() {
    return hospitals;
  }

  public void setHospitals(List<Hospital> hospitals) {
    this.hospitals = hospitals;
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
}
