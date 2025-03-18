package com.nahda.ot_services.repository.interfaces;

import com.nahda.ot_services.dao.RelationshipTypeDAO;

import java.util.UUID;

public interface IRelationshipRepository {
    public static final UUID FATHER_UUID = UUID.fromString("6e7a9294-7a36-409f-9bed-a39c313724ab");
    public static final UUID MOTHER_UUID = UUID.fromString("bf2e32b2-064c-46e3-b597-c2ddbb1344fe");
    int addRelationship(RelationshipTypeDAO relationshipTypeDAO);

    UUID getRelationshipUUID(UUID from, UUID to);
}
