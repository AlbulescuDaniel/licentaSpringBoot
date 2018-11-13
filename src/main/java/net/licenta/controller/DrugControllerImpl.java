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
import net.licenta.model.dto.DrugDTO;
import net.licenta.service.DrugService;

@Api(value = "Drug API")
@RestController
@RequestMapping("/drugs")
public class DrugControllerImpl implements DrugController {

  private static final Logger log = LoggerFactory.getLogger(DrugControllerImpl.class);

  @Autowired
  DrugService drugService;

  @ApiOperation(value = "Return all drugs.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All drugs are returned.") })
  @GetMapping
  @Override
  public ResponseEntity<Set<DrugDTO>> getAllDrugs() {
    Set<DrugDTO> drugs = drugService.getAllDrugs();
    HttpStatus http = !CollectionUtils.isEmpty(drugs) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
    log.info("Returned all drugs");
    return new ResponseEntity<>(drugs, http);
  }

  @ApiOperation(value = "Return drugs with the required id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Drug returned successfully!"),
      @ApiResponse(code = 404, message = "The drugs with the required ID does not exist.", response = Error.class) })
  @GetMapping("/{id}")
  @Override
  public ResponseEntity<DrugDTO> getDrugById(@PathVariable Long id) {
    return drugService.getDrugById(id).map(entity -> {
      log.info("Drug with id {} returned", id);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<DrugDTO>(HttpStatus.NOT_FOUND));
  }

  @ApiOperation(value = "Create new drugs.")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Drug successfully created!"), @ApiResponse(code = 400, message = "Drug fields are invalid.", response = Error.class),
      @ApiResponse(code = 409, message = "Creating new drugs failed.", response = Error.class) })
  @PostMapping
  @Override
  public ResponseEntity<DrugDTO> createDrug(@Valid @RequestBody DrugDTO drugDTO) {
    return drugService.createDrug(drugDTO).map(entity -> {
      log.info("Created drug with fields: {}", drugDTO);
      return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }).orElseGet(() -> new ResponseEntity<DrugDTO>(HttpStatus.CONFLICT));
  }

  @ApiOperation(value = "Update drug with the required id with the new doctor fields.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Drug with the required id was updated"),
      @ApiResponse(code = 404, message = "The drugs with tre required ID does not exist.", response = Error.class),
      @ApiResponse(code = 400, message = "Drug fields are invalid.", response = Error.class) })
  @PutMapping("/{id}")
  @Override
  public ResponseEntity<DrugDTO> updateDrugById(@PathVariable Long id, @Valid @RequestBody DrugDTO userDoctorDTO) {
    return drugService.updateDrug(id, userDoctorDTO).map(entity -> {
      log.info("Updated drug with id {} with fields: {}", id, userDoctorDTO);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    }).orElseGet(() -> new ResponseEntity<DrugDTO>(HttpStatus.NOT_FOUND));
  }

  @ApiOperation(value = "Delete drug with the required id.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Drug with the required id was deleted."),
      @ApiResponse(code = 404, message = "The drug with the required ID does not exist.", response = Error.class) })
  @DeleteMapping("/{id}")
  @Override
  public ResponseEntity<HttpStatus> deleteDrugById(@PathVariable Long id) {
    HttpStatus http = drugService.deleteDrugById(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    log.info("Delete drug with id {}", id);
    return new ResponseEntity<>(http);
  }

  @ApiOperation(value = "Delete all the drugs from the database.")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "All the drugs were succesfully deleted."),
      @ApiResponse(code = 404, message = "All the drugs were not deleted deleted.", response = Error.class) })
  @DeleteMapping
  @Override
  public ResponseEntity<HttpStatus> deleteAllDrugs() {
    log.info("Delete all drugs");
    HttpStatus http = drugService.deleteAllDrugs() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(http);
  }
}
