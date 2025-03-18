package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dao.RelationshipTypeDAO;
import com.nahda.ot_services.dto.RelationshipTypeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRelationshipMapper {
    RelationshipTypeDAO fromRelationshipTypeDTOtoRelationshipTypeDAO(RelationshipTypeDTO relationshipTypeDTO);
    RelationshipTypeDTO fromRelationshipTypeDAOtoRelationshipTypeDTO(RelationshipTypeDAO relationshipTypeDAO);
}
