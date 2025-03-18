package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dao.PatientDAO;
import com.nahda.ot_services.dao.PatientListDAO;
import com.nahda.ot_services.dto.LookupDTO;
import com.nahda.ot_services.dto.PatientDTO;
import com.nahda.ot_services.dto.PatientListDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPatientMapper {

    PatientDTO fromPatientDAOtoPatientDTO(PatientDAO patientDAO);
    @Mapping(target = "individual", ignore = true)
    PatientDAO fromPatientDTOtoPatientDAO(PatientDTO patientDTO);

    @AfterMapping
    default void setDefaultStatus(@MappingTarget PatientDTO patientDTO, PatientDAO patientDAO) {
        patientDTO.setFirstName(patientDAO.getIndividual().getFirstName());
        patientDTO.setDob(patientDAO.getIndividual().getDob());
        patientDTO.setGender(new LookupDTO(patientDAO.getIndividual().getGender().getUuid(),patientDAO.getIndividual().getGender().getName()));
    }

    List<PatientListDTO> fromPatientListDAOtoPatientListDTO(List<PatientListDAO> patientListDAO);
    List<PatientListDAO> fromPatientListDTOtoPatientListDAO(List<PatientListDTO> patientListDTO);


}
