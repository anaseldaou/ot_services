package com.nahda.ot_services.service.interfaces;

import com.nahda.ot_services.dto.RelationshipTypeDTO;

import java.util.UUID;

public interface IRelationshipTypeService {
    public static final UUID RELATIONSHIP_FATHER_TYPE = UUID.fromString("6e7a9294-7a36-409f-9bed-a39c313724ab");
    public static final UUID RELATIONSHIP_MOTHER_TYPE = UUID.fromString("bf2e32b2-064c-46e3-b597-c2ddbb1344fe");
    void addRelationshipType(RelationshipTypeDTO relationshipTypeDTO);
    UUID getRelationshipUUID(UUID from, UUID to);
}
