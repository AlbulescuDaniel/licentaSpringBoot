package net.licenta.controller;

import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import net.licenta.model.dto.CreatePatientDTO;
import net.licenta.model.dto.UserPatientDTO;
import net.licenta.service.PatientService;

@Api(value = "Patient API")
@RestController
@RequestMapping("/patients")
public class PatientControllerImpl implements PatientController {

  private static final Logger log = LoggerFactory.getLogger(PatientControllerImpl.class);

  @Autowired
  PatientService patientService;

  @ApiOperation(value = "Return all patients.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All patients are returned.") })
  @GetMapping
  @Override
  public ResponseEntity<Set<UserPatientDTO>> getAllPatients() {
    Set<UserPatientDTO> patients = patientService.getAllPatients();
    HttpStatus http = !CollectionUtils.isEmpty(patients) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
    log.info("Returned all patients");
    return new ResponseEntity<>(patients, http);
  }

  @ApiOperation(value = "Return patient with the required id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Patient returned successfully!"),
      @ApiResponse(code = 404, message = "The patient with the required ID does not exist.", response = Error.class) })
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<UserPatientDTO> getPatientById(@PathVariable Long id) {
    return patientService.getPatientById(id).map(entity -> {
      log.info("Patient with id {} returned", id);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<UserPatientDTO>(HttpStatus.NOT_FOUND));
  }

  @ApiOperation(value = "Create new patient.")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Patient successfully created!"), @ApiResponse(code = 400, message = "Patient fields are invalid.", response = Error.class),
      @ApiResponse(code = 409, message = "Creating new patient failed.", response = Error.class) })
  @PostMapping
  @Override
  public ResponseEntity<UserPatientDTO> createPatient(@Valid @RequestBody CreatePatientDTO userPatientDTO) {
    return patientService.createPatient(userPatientDTO).map(entity -> {
      log.info("Created patient with fields: {}", userPatientDTO);
      return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }).orElseGet(() -> new ResponseEntity<UserPatientDTO>(HttpStatus.CONFLICT));
  }

  @ApiOperation(value = "Update patient with the required id with the new patient fields.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Patient with the required id was updated"),
      @ApiResponse(code = 404, message = "The patient with tre required ID does not exist.", response = Error.class),
      @ApiResponse(code = 400, message = "Patient fields are invalid.", response = Error.class) })
  @PutMapping("/{id}")
  @Override
  public ResponseEntity<UserPatientDTO> updatePatientById(@PathVariable Long id, @Valid @RequestBody UserPatientDTO userPatientDTO) {
    return patientService.updatePatient(id, userPatientDTO).map(entity -> {
      log.info("Updated patient with id {} with fields: {}", id, userPatientDTO);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<UserPatientDTO>(HttpStatus.NOT_FOUND));
  }

  @ApiOperation(value = "Delete patient with the required id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Patient with the required id was deleted."),
      @ApiResponse(code = 404, message = "The patient with the required ID does not exist.", response = Error.class) })
  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<HttpStatus> deletePatientById(@PathVariable Long id) {
    HttpStatus http = patientService.deletePatientById(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    log.info("Delete patients with id {}", id);
    return new ResponseEntity<>(http);
  }

  @ApiOperation(value = "Delete all the patients from the database.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All the patients were succesfully deleted."),
      @ApiResponse(code = 404, message = "All the patients were not deleted deleted.", response = Error.class) })
  @DeleteMapping
  @Override
  public ResponseEntity<HttpStatus> deleteAllPatients() {
    log.info("Delete all patients");
    HttpStatus http = patientService.deleteAllPatients() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(http);
  }
}
