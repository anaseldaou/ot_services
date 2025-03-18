package com.nahda.ot_services.service;

import com.nahda.ot_services.dao.IndividualDAO;
import com.nahda.ot_services.dto.IndividualRequestDTO;
import com.nahda.ot_services.dto.IndividualResponseDTO;
import com.nahda.ot_services.mappers.IIndividualMapper;
import com.nahda.ot_services.repository.IndividualRepository;
import com.nahda.ot_services.repository.interfaces.IRelationshipRepository;
import com.nahda.ot_services.service.interfaces.IAuthService;
import com.nahda.ot_services.service.interfaces.IIndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
public class IndividualServiceImpl implements IIndividualService {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IIndividualMapper individualMapper;

    @Autowired
    private IndividualRepository individualRepository;

    @Override
    public IndividualResponseDTO addIndividual(IndividualRequestDTO individualRequestDTO) {
        try{
            if(individualRequestDTO.getUuid() == null){
                // setup
                UUID individualUuid = UUID.randomUUID();
                IndividualDAO individualDAO = this.individualMapper.fromIndividualRequestDTOToIndividualDAO(individualRequestDTO);
                individualDAO.setUuid(individualUuid);
                individualDAO.setCreatedUserUuid(authService.getUserUuid());
                individualDAO.setCreationDatetime(LocalDateTime.now(ZoneId.of("UTC")));
                individualRepository.insertIndividual(individualDAO);
                IndividualResponseDTO response = getIndividualByUUID(individualUuid);
                // GET BY ID
                return response;
            }
            return null;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IndividualResponseDTO updateIndividual(IndividualRequestDTO individualRequestDTO) {
        try{
            if(individualRequestDTO.getUuid() != null){
                IndividualDAO individualDAO = this.individualMapper.fromIndividualRequestDTOToIndividualDAO(individualRequestDTO);
                individualDAO.setUpdatedUserUuid(authService.getUserUuid());
                individualDAO.setLastUpdateDatetime(LocalDateTime.now(ZoneId.of("UTC")));
                individualRepository.updateIndividual(individualDAO);
                IndividualResponseDTO response = getIndividualByUUID(individualRequestDTO.getUuid());
                // GET BY ID
                return response;
            }
            return null;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IndividualResponseDTO getIndividualByUUID(UUID individualUUID) {
        Optional<IndividualDAO> individualDAO = individualRepository.getIndividualByID(individualUUID);
        if(individualDAO.isPresent()) {
            return this.individualMapper.fromIndividualDAOToIndividualResponseDTO(individualRepository.getIndividualByID(individualUUID).get());
        }
        return null;
    }

    @Override
    public IndividualDAO getFatherUUID(UUID individualUUID) {
        return individualRepository.getFromIndividualByRelationshipType(individualUUID, IRelationshipRepository.FATHER_UUID);
    }
    @Override
    public IndividualDAO getMotherUUID(UUID individualUUID) {
        return individualRepository.getFromIndividualByRelationshipType(individualUUID, IRelationshipRepository.MOTHER_UUID);
    }
}
