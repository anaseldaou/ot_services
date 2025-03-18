package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dao.IndividualDAO;
import com.nahda.ot_services.dao.IndividualRequestDAO;
import com.nahda.ot_services.dao.IndividualResponseDAO;
import com.nahda.ot_services.dto.IndividualRequestDTO;
import com.nahda.ot_services.dto.IndividualResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IIndividualMapper {

    IndividualDAO fromIndividualRequestDTOToIndividualDAO(IndividualRequestDTO source);
    IndividualResponseDTO fromIndividualDAOToIndividualResponseDTO(IndividualDAO source);
    IndividualRequestDAO fromIndividualRequestDAOToIndividualRequestDTO(IndividualRequestDTO source);
    IndividualRequestDTO fromIndividualRequestDTOToIndividualRequestDAO(IndividualRequestDAO source);
    IndividualResponseDAO fromIndividualResponseDTOToIndividualResponseDAO(IndividualResponseDTO source);
    IndividualResponseDTO fromIndividualResponseDAOToIndividualResponseDTO(IndividualResponseDAO source);
}