package net.licenta.controller;

import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import net.licenta.model.dto.UserPharmacyDTO;
import net.licenta.service.PharmacyService;

@Api(value = "Pharmacy API")
@RestController
@RequestMapping("/pharmacies")
public class PharmacyControllerImpl implements PharmacyController {

  private static final Logger log = LoggerFactory.getLogger(PharmacyControllerImpl.class);

  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  @Autowired
  PharmacyService pharmacyService;

  @ApiOperation(value = "Return all pharmacies.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All pharmacies are returned.") })
  @GetMapping
  @Override
  public ResponseEntity<Set<UserPharmacyDTO>> getAllPharmacies() {
    Set<UserPharmacyDTO> pharmacyDTOs = pharmacyService.getAllPharmacies();
    HttpStatus http = !CollectionUtils.isEmpty(pharmacyDTOs) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
    log.info("Returned all pharmacies");
    return new ResponseEntity<>(pharmacyDTOs, http);
  }

  @ApiOperation(value = "Return pharmacy with the required id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Pharmacy returned successfully!"),
      @ApiResponse(code = 404, message = "The pharmacy with the required ID does not exist.", response = Error.class) })
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<UserPharmacyDTO> getPharmacyById(@PathVariable Long id) {
    return pharmacyService.getPharmacyById(id).map(entity -> {
      log.info("Pharmacy with id {} returned", id);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<UserPharmacyDTO>(HttpStatus.NOT_FOUND));
  }

  @ApiOperation(value = "Create new pharmacy.")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Pharmacy successfully created!"),
      @ApiResponse(code = 400, message = "Pharmacy fields are invalid.", response = Error.class),
      @ApiResponse(code = 409, message = "Creating new pharmacy failed.", response = Error.class) })
  @PostMapping
  @Override
  public ResponseEntity<UserPharmacyDTO> createPharmacy(@Valid @RequestBody UserPharmacyDTO userPharmacyDTO) {
    userPharmacyDTO.setPassword(bCryptPasswordEncoder.encode(userPharmacyDTO.getPassword()));
    return pharmacyService.createPharmacy(userPharmacyDTO).map(entity -> {
      log.info("Created patient with fields: {}", userPharmacyDTO);
      return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }).orElseGet(() -> new ResponseEntity<UserPharmacyDTO>(HttpStatus.CONFLICT));
  }

  @ApiOperation(value = "Update pharmacy with the required id with the new patient fields.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Pharmacy with the required id was updated"),
      @ApiResponse(code = 404, message = "The pharmacy with tre required ID does not exist.", response = Error.class),
      @ApiResponse(code = 400, message = "Pharmacy fields are invalid.", response = Error.class) })
  @PutMapping("/{id}")
  @Override
  public ResponseEntity<UserPharmacyDTO> updatePharmacyById(@PathVariable Long id, @Valid @RequestBody UserPharmacyDTO userPharmacyDTO) {
    return pharmacyService.updatePharmacy(id, userPharmacyDTO).map(entity -> {
      log.info("Updated pharmacy with id {} with fields: {}", id, userPharmacyDTO);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<UserPharmacyDTO>(HttpStatus.NOT_FOUND));
  }

  @ApiOperation(value = "Delete pharmacy with the required id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Pharmacy with the required id was deleted."),
      @ApiResponse(code = 404, message = "The pharmacy with the required ID does not exist.", response = Error.class) })
  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<HttpStatus> deletePharmacyById(@PathVariable Long id) {
    HttpStatus http = pharmacyService.deletePharmacyById(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    log.info("Delete pharmacies with id {}", id);
    return new ResponseEntity<>(http);
  }

  @ApiOperation(value = "Delete all the pharmacies from the database.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All the pharmacies were succesfully deleted."),
      @ApiResponse(code = 404, message = "The pharmacies were not deleted deleted.", response = Error.class) })
  @DeleteMapping
  @Override
  public ResponseEntity<HttpStatus> deleteAllPharmacies() {
    log.info("Delete all pharmacies");
    HttpStatus http = pharmacyService.deleteAllPharmacies() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(http);
  }
}
