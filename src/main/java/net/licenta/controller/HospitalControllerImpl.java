package net.licenta.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.licenta.model.dto.HospitalWithSpecializationDTO;
import net.licenta.service.HospitalService;

@Api(value = "Patient API")
@RestController
@RequestMapping("/hospitals")
public class HospitalControllerImpl implements HospitalController{

  private static final Logger log = LoggerFactory.getLogger(HospitalControllerImpl.class);
  
  @Autowired
  HospitalService hospitalService;
  
  @ApiOperation(value = "Return hospitals by filter")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Hospitals were returned."),
      @ApiResponse(code = 404, message = "There are no hospitals.", response = Error.class) })
  @GetMapping("/filter")
  @Override
  public ResponseEntity<Set<HospitalWithSpecializationDTO>> findHospitalByFilters(@RequestParam("city") String city, @RequestParam(name = "specialization", required = false) String specialization) {
    Set<HospitalWithSpecializationDTO> hospitalWithSpecialization = hospitalService.findHospitalByFilters(city, specialization);
    HttpStatus http = !CollectionUtils.isEmpty(hospitalWithSpecialization) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
    log.info("Returned hospitals by filtering");
    return new ResponseEntity<>(hospitalWithSpecialization, http);
  }
}
