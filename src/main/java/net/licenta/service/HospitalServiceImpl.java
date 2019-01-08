package net.licenta.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.licenta.model.dto.HospitalWithSpecializationDTO;
import net.licenta.model.entity.Hospital;
import net.licenta.model.entity.Specialization;
import net.licenta.model.util.DataModelTransformer;
import net.licenta.repository.HospitalRepository;

@Service
public class HospitalServiceImpl implements HospitalService {

  @Autowired
  HospitalRepository hospitalRepository;

  @Override
  public Set<HospitalWithSpecializationDTO> findHospitalByFilters(String city, String specialization) {
    Set<Hospital> hospitals = hospitalRepository.findByAddressCityIgnoreCase(city);
    return hospitals.stream().filter(s -> filterHospitals(s.getSpecializations(), specialization)).map(DataModelTransformer::fromHospitalToHospitalWithSpecializationDTO).collect(Collectors.toSet());
  }

  private Boolean filterHospitals(List<Specialization> specializations, String specialization) {
    if (specialization == null) {
      return true;
    }

    for (Specialization s : specializations) {
      if (StringUtils.containsIgnoreCase(s.getName(), specialization))
        return true;
    }
    return false;
  }
}
