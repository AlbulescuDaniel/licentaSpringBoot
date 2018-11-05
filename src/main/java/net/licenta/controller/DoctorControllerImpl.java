package net.licenta.controller;

import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Timed;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.licenta.model.dto.UserDoctorDTO;
import net.licenta.service.DoctorService;

@Api(value = "Doctor API")
@RestController
@RequestMapping("/doctors")
public class DoctorControllerImpl implements DoctorController {

  private static final Logger log = LoggerFactory.getLogger(DoctorControllerImpl.class);

  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  @Autowired
  DoctorService doctorService;

  @Timed(millis = 10000)
  @ApiOperation(value = "Return all doctors.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All doctors are returned.") })
  @GetMapping
  @Override
  public ResponseEntity<Set<UserDoctorDTO>> getAllDoctors() {
    Set<UserDoctorDTO> doctors = doctorService.getAllDoctors();
    HttpStatus http = !CollectionUtils.isEmpty(doctors) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
    log.info("Returned all doctors");
    return new ResponseEntity<>(doctors, http);
  }

  @Timed(millis = 10000)
  @ApiOperation(value = "Return doctor with the required id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Doctor returned successfully!"),
      @ApiResponse(code = 404, message = "The doctor with the required ID does not exist.", response = Error.class) })
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<UserDoctorDTO> getDoctorById(@PathVariable Long id) {
    return doctorService.getDoctorById(id).map(entity -> {
      log.info("Doctor with id {} returned", id);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<UserDoctorDTO>(HttpStatus.NOT_FOUND));
  }

  @Timed(millis = 10000)
  @ApiOperation(value = "Create new doctor.")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Doctor successfully created!"), @ApiResponse(code = 400, message = "Doctor fields are invalid.", response = Error.class),
      @ApiResponse(code = 409, message = "Creating new doctor failed.", response = Error.class) })
  @PostMapping
  @Override
  public ResponseEntity<UserDoctorDTO> createDoctor(@Valid @RequestBody UserDoctorDTO userDoctorDTO) {
    userDoctorDTO.setPassword(bCryptPasswordEncoder.encode(userDoctorDTO.getPassword()));
    return doctorService.createDoctor(userDoctorDTO).map(entity -> {
      log.info("Created doctor with fields: {}", userDoctorDTO);
      return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }).orElseGet(() -> new ResponseEntity<UserDoctorDTO>(HttpStatus.CONFLICT));
  }

  @Timed(millis = 10000)
  @ApiOperation(value = "Update doctor with the required id with the new doctor fields.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Doctor with the required id was updated"),
      @ApiResponse(code = 404, message = "The doctor with tre required ID does not exist.", response = Error.class),
      @ApiResponse(code = 400, message = "Doctor fields are invalid.", response = Error.class) })
  @PutMapping("/{id}")
  @Override
  public ResponseEntity<UserDoctorDTO> updateDoctorById(@PathVariable Long id, @Valid @RequestBody UserDoctorDTO userDoctorDTO) {
    return doctorService.updateDoctor(id, userDoctorDTO).map(entity -> {
      log.info("Updated doctor with id {} with fields: {}", id, userDoctorDTO);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<UserDoctorDTO>(HttpStatus.NOT_FOUND));
  }

  @Timed(millis = 10000)
  @ApiOperation(value = "Delete doctor with the required id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Doctor with the required id was deleted."),
      @ApiResponse(code = 404, message = "The doctor with the required ID does not exist.", response = Error.class) })
  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<HttpStatus> deleteDoctorById(@PathVariable Long id) {
    HttpStatus http = doctorService.deleteDoctorById(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    log.info("Delete doctor with id {}", id);
    return new ResponseEntity<>(http);
  }

  @Timed(millis = 10000)
  @ApiOperation(value = "Delete all the patients from the database.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All the patients were succesfully deleted."),
      @ApiResponse(code = 404, message = "All the patients were not deleted deleted.", response = Error.class) })
  @DeleteMapping
  @Override
  public ResponseEntity<HttpStatus> deleteAllDoctors() {
    log.info("Delete all doctors");
    HttpStatus http = doctorService.deleteAllDoctors() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(http);
  }
}
