package net.licenta.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.licenta.model.dto.UserDoctorDTO;

public interface DoctorController {

  public ResponseEntity<Set<UserDoctorDTO>> getAllDoctors();
  
  public ResponseEntity<UserDoctorDTO> getDoctorById(Long id);

  public ResponseEntity<UserDoctorDTO> createDoctor(UserDoctorDTO userDTO);

  public ResponseEntity<UserDoctorDTO> updateDoctorById(Long id, UserDoctorDTO userDTO);
  
  public ResponseEntity<HttpStatus> deleteDoctorById(Long id);

  public ResponseEntity<HttpStatus> deleteAllDoctors();
}