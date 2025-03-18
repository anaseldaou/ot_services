package com.nahda.ot_services.dao;

import java.util.UUID;

public class RelationshipTypeDAO {
    UUID uuid;
    UUID from;
    UUID to;
    UUID relationshipTypeUUID;
    UUID familyUUID;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getFrom() {
        return from;
    }

    public void setFrom(UUID from) {
        this.from = from;
    }

    public UUID getTo() {
        return to;
    }

    public void setTo(UUID to) {
        this.to = to;
    }

    public UUID getRelationshipTypeUUID() {
        return relationshipTypeUUID;
    }

    public void setRelationshipTypeUUID(UUID relationshipTypeUUID) {
        this.relationshipTypeUUID = relationshipTypeUUID;
    }

    public UUID getFamilyUUID() {
        return familyUUID;
    }

    public void setFamilyUUID(UUID familyUUID) {
        this.familyUUID = familyUUID;
    }
}
