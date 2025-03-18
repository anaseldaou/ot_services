package com.nahda.ot_services.dto;

import java.util.List;
import java.util.UUID;

public class LackOfAcademicAchievementReportDTO {

    private String relationship;
    private String cycle;
    private String subject;

    public LackOfAcademicAchievementReportDTO(String relationship, String cycle, String subject) {
        this.relationship = relationship;
        this.cycle = cycle;
        this.subject = subject;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
