package net.licenta.controller;

import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.licenta.model.dto.PrescriptionDTO;
import net.licenta.service.PrescriptionService;

@Api(value = "Prescription API")
@RestController
@RequestMapping("/prescriptions")
public class PrescriptionControllerImpl implements PrescriptionController {

  private static final Logger log = LoggerFactory.getLogger(PrescriptionControllerImpl.class);

  @Autowired
  PrescriptionService prescriptionService;

  @Timed(millis = 10000)
  @ApiOperation(value = "Return all the prescriptions.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All prescriptions are returned"), @ApiResponse(code = 404, message = "Prescription list is empty") })
  @GetMapping
  @Override
  public ResponseEntity<Set<PrescriptionDTO>> getAllPrescriptions() {
    Set<PrescriptionDTO> prescriptionDTOs = prescriptionService.getAllPrescriptions();
    log.info("Returned {} prescriptions", prescriptionDTOs.size());
    return !CollectionUtils.isEmpty(prescriptionDTOs) ? new ResponseEntity<>(prescriptionDTOs, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Timed(millis = 10000)
  @ApiOperation(value = "Return prescription with the required id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Prescription returned successfully!"),
      @ApiResponse(code = 404, message = "The prescriptions with the required id does not exist.", response = Error.class) })
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<PrescriptionDTO> getPrescriptionById(@PathVariable Long id) {
    return prescriptionService.getPrescriptionById(id).map(entity -> {
      log.info("Prescription with id {} returned", id);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<PrescriptionDTO>(HttpStatus.NOT_FOUND));
  }

  @Timed(millis = 10000)
  @ApiOperation(value = "Create new prescription.")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Prescription successfully created!"), @ApiResponse(code = 400, message = "Prescription fields are invalid.", response = Error.class),
      @ApiResponse(code = 409, message = "Creating new prescription failed.", response = Error.class) })
  @PostMapping
  @Override
  public ResponseEntity<PrescriptionDTO> createPrescription(@Valid @RequestBody PrescriptionDTO prescriptionDTO) {
    return prescriptionService.createPrescription(prescriptionDTO).map(entity -> {
      log.info("Created prescription with fields: {}", prescriptionDTO);
      return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }).orElseGet(() -> new ResponseEntity<PrescriptionDTO>(HttpStatus.CONFLICT));
  }

  @Timed(millis = 10000)
  @ApiOperation(value = "Update prescription with the required id with the new event fields.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Prescription with the required id was updated"),
      @ApiResponse(code = 404, message = "The prescription ID you entered does not exist or version is too old.", response = Error.class),
      @ApiResponse(code = 400, message = "Prescription fields are invalid.", response = Error.class) })
  @PutMapping("/{id}")
  @Override
  public ResponseEntity<PrescriptionDTO> updatePrescription(@Valid Long id, @Valid @RequestBody PrescriptionDTO prescriptionDTO) {
    return prescriptionService.updatePrescription(id, prescriptionDTO).map(entity -> {
      log.info("Updated prescription with id {} with fields: {}", id, prescriptionDTO);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<PrescriptionDTO>(HttpStatus.NOT_FOUND));
  }

  @Timed(millis = 10000)
  @ApiOperation(value = "Delete prescription by id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "The prescription was succesfully deleted!"),
      @ApiResponse(code = 404, message = "The prescription was not found or the registrations are open for the specified event.", response = Error.class) })
  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<HttpStatus> deletePrescriptionById(@Valid Long id) {
    log.info("Delete prescription with id {}", id);
    HttpStatus http = prescriptionService.deletePrescriptionById(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(http);
  }

  @Timed(millis = 10000)
  @ApiOperation(value = "Delete all the prescriptions.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All the prescriptions were succesfully deleted!"),
      @ApiResponse(code = 404, message = "There are no prescriptions.", response = Error.class) })
  @DeleteMapping
  @Override
  public ResponseEntity<HttpStatus> deleteAllPrescriptions() {
    log.info("Delete all prescriptions");
    HttpStatus http = prescriptionService.deleteAllPrescriptions() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(http);
  }

  @GetMapping("/prescriptions")
  @Override
  public ResponseEntity<Set<PrescriptionDTO>> getPatientPrescriptionsByPatientName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
    Set<PrescriptionDTO> prescriptionDTOs = prescriptionService.getPatientPrescriptionsByPatientName(firstName, lastName);
    log.info("Returned {} prescriptions from patient with firstname = {} and lastname = {}", prescriptionDTOs.size(), firstName, lastName);
    return !CollectionUtils.isEmpty(prescriptionDTOs) ? new ResponseEntity<>(prescriptionDTOs, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
