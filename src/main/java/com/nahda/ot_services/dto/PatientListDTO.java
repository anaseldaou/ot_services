package com.nahda.ot_services.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class PatientListDTO {
    UUID uuid;
    String code;
    String firstName;
    String fatherName;
    String lastName;
    String className;
    LocalDate dob;
    LocalDateTime creationDateTime;
    String guardianRelationshipTypeName;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getGuardianRelationshipTypeName() {
        return guardianRelationshipTypeName;
    }

    public void setGuardianRelationshipTypeName(String guardianRelationshipTypeName) {
        this.guardianRelationshipTypeName = guardianRelationshipTypeName;
    }
}
