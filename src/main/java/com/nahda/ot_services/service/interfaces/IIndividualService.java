package com.nahda.ot_services.service.interfaces;

import com.nahda.ot_services.dao.IndividualDAO;
import com.nahda.ot_services.dto.IndividualRequestDTO;
import com.nahda.ot_services.dto.IndividualResponseDTO;

import java.util.UUID;

public interface IIndividualService {

    IndividualResponseDTO addIndividual(IndividualRequestDTO individualRequestDTO);
    IndividualResponseDTO updateIndividual(IndividualRequestDTO individualRequestDTO);
    IndividualResponseDTO getIndividualByUUID(UUID individualUUID);

    IndividualDAO getFatherUUID(UUID individualUUID);

    IndividualDAO getMotherUUID(UUID individualUUID);
}
