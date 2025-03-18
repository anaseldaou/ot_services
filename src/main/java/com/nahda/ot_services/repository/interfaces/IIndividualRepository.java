package com.nahda.ot_services.repository.interfaces;

import com.nahda.ot_services.dao.IndividualDAO;

import java.util.Optional;
import java.util.UUID;

public interface IIndividualRepository {

    int insertIndividual(IndividualDAO individualDAO);
    Optional<IndividualDAO> getIndividualByID(UUID uuid);
    IndividualDAO getFromIndividualByRelationshipType(UUID toIndividualUUID, UUID relationshipTypeUUID);

    int updateIndividual(IndividualDAO individualDAO);
}
