package com.nahda.ot_services.dao;

import java.util.List;
import java.util.UUID;

public class LackOfAcademicAchievementDAO {

    private LookupDAO relationshipType;
    private LookupDAO academicCycle;
    private List<LookupDAO> subject;
    private UUID uuid;
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public LookupDAO getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(LookupDAO relationshipType) {
        this.relationshipType = relationshipType;
    }

    public LookupDAO getAcademicCycle() {
        return academicCycle;
    }

    public void setAcademicCycle(LookupDAO academicCycle) {
        this.academicCycle = academicCycle;
    }

    public List<LookupDAO> getSubject() {
        return subject;
    }

    public void setSubject(List<LookupDAO> subject) {
        this.subject = subject;
    }
}
