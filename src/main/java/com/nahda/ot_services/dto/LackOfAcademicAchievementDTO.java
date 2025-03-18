package com.nahda.ot_services.dto;

import java.util.List;
import java.util.UUID;

public class LackOfAcademicAchievementDTO {

    private LookupDTO relationshipType;
    private LookupDTO academicCycle;
    private List<LookupDTO> subject;
    private UUID uuid;
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public LookupDTO getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(LookupDTO relationshipType) {
        this.relationshipType = relationshipType;
    }

    public LookupDTO getAcademicCycle() {
        return academicCycle;
    }

    public void setAcademicCycle(LookupDTO academicCycle) {
        this.academicCycle = academicCycle;
    }

    public List<LookupDTO> getSubject() {
        return subject;
    }

    public void setSubject(List<LookupDTO> subject) {
        this.subject = subject;
    }
}
