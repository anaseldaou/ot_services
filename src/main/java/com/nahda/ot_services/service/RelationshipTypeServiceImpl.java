package com.nahda.ot_services.service;

import com.nahda.ot_services.dto.RelationshipTypeDTO;
import com.nahda.ot_services.mappers.IRelationshipMapper;
import com.nahda.ot_services.repository.interfaces.IRelationshipRepository;
import com.nahda.ot_services.service.interfaces.IRelationshipTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RelationshipTypeServiceImpl implements IRelationshipTypeService {

    @Autowired
    IRelationshipMapper relationshipMapper;

    @Autowired
    IRelationshipRepository relationshipRepository;

    @Override
    public void addRelationshipType(RelationshipTypeDTO relationshipTypeDTO) {
        relationshipRepository.addRelationship(relationshipMapper.fromRelationshipTypeDTOtoRelationshipTypeDAO(relationshipTypeDTO));
    }

    @Override
    public UUID getRelationshipUUID(UUID from, UUID to)  {
       return relationshipRepository.getRelationshipUUID(from,to);
    }
}
