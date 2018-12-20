package net.licenta.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.licenta.model.dto.PrescriptionDTO;
import net.licenta.model.dto.PrescriptionDetailsDTO;
import net.licenta.model.dto.PrescriptionWithPatientNameDTO;
import net.licenta.service.PrescriptionService;

@Api(value = "Prescription API")
@RestController
@RequestMapping("/prescriptions")
public class PrescriptionControllerImpl implements PrescriptionController {

  private static final Logger log = LoggerFactory.getLogger(PrescriptionControllerImpl.class);

  @Autowired
  PrescriptionService prescriptionService;

  @ApiOperation(value = "Return all the prescriptions.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All prescriptions are returned"), @ApiResponse(code = 404, message = "Prescription list is empty") })
  @GetMapping
  @Override
  public ResponseEntity<Set<PrescriptionDTO>> getAllPrescriptions() {
    Set<PrescriptionDTO> prescriptionDTOs = prescriptionService.getAllPrescriptions();
    log.info("Returned {} prescriptions", prescriptionDTOs.size());
    return !CollectionUtils.isEmpty(prescriptionDTOs) ? new ResponseEntity<>(prescriptionDTOs, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

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

  @ApiOperation(value = "Create new prescription.")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Prescription successfully created!"), @ApiResponse(code = 400, message = "Prescription fields are invalid.", response = Error.class),
      @ApiResponse(code = 409, message = "Creating new prescription failed.", response = Error.class) })
  @PostMapping
  @Override
  public ResponseEntity<PrescriptionDTO> createPrescription(@Valid @RequestBody PrescriptionDTO prescriptionDTO, @RequestParam("firstName") String firstName,
      @RequestParam(value = "lastName") String lastName) {
    return prescriptionService.createPrescription(prescriptionDTO, firstName, lastName).map(entity -> {
      log.info("Created prescription with fields: {}", prescriptionDTO);
      return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }).orElseGet(() -> new ResponseEntity<PrescriptionDTO>(HttpStatus.CONFLICT));
  }

  @ApiOperation(value = "Update prescription with the required id with the new event fields.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Prescription with the required id was updated"),
      @ApiResponse(code = 404, message = "The prescription ID you entered does not exist or version is too old.", response = Error.class),
      @ApiResponse(code = 400, message = "Prescription fields are invalid.", response = Error.class) })
  @PutMapping("/{id}")
  @Override
  public ResponseEntity<PrescriptionDTO> updatePrescription(@PathVariable Long id, @Valid @RequestBody PrescriptionDTO prescriptionDTO) {
    return prescriptionService.updatePrescription(id, prescriptionDTO).map(entity -> {
      log.info("Updated prescription with id {} with fields: {}", id, prescriptionDTO);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<PrescriptionDTO>(HttpStatus.NOT_FOUND));
  }

  @ApiOperation(value = "Delete prescription by id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "The prescription was succesfully deleted!"),
      @ApiResponse(code = 404, message = "The prescription was not found or the registrations are open for the specified event.", response = Error.class) })
  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<HttpStatus> deletePrescriptionById(@PathVariable Long id) {
    log.info("Delete prescription with id {}", id);
    HttpStatus http = prescriptionService.deletePrescriptionById(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(http);
  }

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

  @GetMapping("/patient")
  @Override
  public ResponseEntity<Set<PrescriptionWithPatientNameDTO>> getPatientPrescriptionsByPatientNameAndDateBetwwen(@RequestParam("firstName") String firstName,
      @RequestParam(value = "lastName", required = false) String lastName, @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
      @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
    Set<PrescriptionWithPatientNameDTO> prescriptionDTOs = result(firstName, lastName, startDate, endDate);

    log.info("Returned {} prescriptions from patient with firstname = {} and lastname = {}", prescriptionDTOs.size(), firstName, lastName);
    return new ResponseEntity<>(prescriptionDTOs, HttpStatus.OK);
  }

  private Set<PrescriptionWithPatientNameDTO> result(String firstName, String lastName, LocalDate startDate, LocalDate endDate) {

    if (lastName != null) {
      if (startDate != null && endDate != null) {
        return prescriptionService.getPatientPrescriptionsByPatientFirstNameAndLastName(firstName, lastName, startDate, endDate);
      }
      else {
        if (startDate == null && endDate != null) {
          return prescriptionService.getPatientPrescriptionsByPatientFirstNameAndLastName(firstName, lastName, LocalDate.now().minusYears(1), endDate);
        }
        if (startDate != null && endDate == null) {
          return prescriptionService.getPatientPrescriptionsByPatientFirstNameAndLastName(firstName, lastName, startDate, LocalDate.now());
        }
        if (startDate == null && endDate == null) {
          return prescriptionService.getPatientPrescriptionsByPatientFirstNameAndLastName(firstName, lastName, LocalDate.now().minusYears(1), LocalDate.now());
        }
      }
    }
    else {
      if (startDate != null && endDate != null) {
        return prescriptionService.getPatientPrescriptionsByPatientFirstName(firstName, startDate, endDate);
      }
      else {
        if (startDate == null && endDate != null) {
          return prescriptionService.getPatientPrescriptionsByPatientFirstName(firstName, LocalDate.now().minusYears(1), endDate);
        }
        if (startDate != null && endDate == null) {
          return prescriptionService.getPatientPrescriptionsByPatientFirstName(firstName, startDate, LocalDate.now());
        }
        if (startDate == null && endDate == null) {
          return prescriptionService.getPatientPrescriptionsByPatientFirstName(firstName, LocalDate.now().minusYears(1), LocalDate.now());
        }
      }
    }
    return new HashSet<>();
  }

  @GetMapping("/details/{id}")
  @Override
  public ResponseEntity<PrescriptionDetailsDTO> getPrescriptionDetails(@PathVariable Long id) {
    return prescriptionService.getPrescriptionDetails(id).map(entity -> {
      log.info("Prescription with id {} returned", id);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<PrescriptionDetailsDTO>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/patient/{userName}")
  @Override
  public ResponseEntity<Set<PrescriptionDTO>> getPatientPrescriptionsByPatientUserNameAndDateBetwwen(@PathVariable String userName,
      @RequestParam(name = "startDate") @DateTimeFormat(iso = ISO.DATE) LocalDate startDate, @RequestParam(name = "endDate") @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
    Set<PrescriptionDTO> prescriptionDTOs = (startDate != null || endDate != null) ? prescriptionService.getPatientPrescriptionsByPatientUserNameAndDateBetwwen(userName, startDate, endDate)
        : prescriptionService.getPatientPrescriptionsByPatientUserNameAndDateBetwwen(userName, LocalDate.now().minusYears(1), LocalDate.now());

    log.info("Returned {} prescriptions from patient with userName = {} }", prescriptionDTOs.size(), userName);
    return new ResponseEntity<>(prescriptionDTOs, HttpStatus.OK);
  }
}
