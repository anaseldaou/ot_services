package com.nahda.ot_services.dto;

import java.util.UUID;

public class RelationshipTypeDTO {
    UUID uuid;
    UUID from;
    UUID to;
    UUID relationshipTypeUUID;
    UUID familyUUID;

    public RelationshipTypeDTO(UUID uuid, UUID from, UUID to, UUID relationshipTypeUUID, UUID familyUUID) {
        this.uuid = uuid;
        this.from = from;
        this.to = to;
        this.relationshipTypeUUID = relationshipTypeUUID;
        this.familyUUID = familyUUID;
    }

    public UUID getUuid() {
        return uuid;
    }

    public RelationshipTypeDTO setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public UUID getFrom() {
        return from;
    }

    public RelationshipTypeDTO setFrom(UUID from) {
        this.from = from;
        return this;
    }

    public UUID getTo() {
        return to;
    }

    public RelationshipTypeDTO setTo(UUID to) {
        this.to = to;
        return this;
    }

    public UUID getRelationshipTypeUUID() {
        return relationshipTypeUUID;
    }

    public RelationshipTypeDTO setRelationshipTypeUUID(UUID relationshipTypeUUID) {
        this.relationshipTypeUUID = relationshipTypeUUID;
        return this;
    }

    public UUID getFamilyUUID() {
        return familyUUID;
    }

    public RelationshipTypeDTO setFamilyUUID(UUID familyUUID) {
        this.familyUUID = familyUUID;
        return this;
    }
}
