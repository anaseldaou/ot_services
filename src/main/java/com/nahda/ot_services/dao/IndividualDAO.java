package com.nahda.ot_services.dao;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class IndividualDAO {


    UUID uuid;

    @NotNull
    String firstName;

    @NotNull
    String fatherName;

    @NotNull
    String lastName;

    @NotNull
    LocalDate dob;

    @NotNull
    LookupDAO gender;

    //    @NotNull
    LookupDAO maritalStatus;

    //    @NotNull
    LookupDAO educationalLevel;

    //    @NotNull
    String job;

    LookupDAO nationality;

    String email;

    String phoneNumber;

    LocalDateTime creationDatetime;

    UUID createdUserUuid;

    public UUID getUpdatedUserUuid() {
        return updatedUserUuid;
    }

    public void setUpdatedUserUuid(UUID updatedUserUuid) {
        this.updatedUserUuid = updatedUserUuid;
    }

    public LocalDateTime getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(LocalDateTime lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    UUID updatedUserUuid;
    LocalDateTime lastUpdateDatetime;

    public LookupDAO getNationality() {
        return nationality;
    }

    public void setNationality(LookupDAO nationality) {
        this.nationality = nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getFatherName() {
        return fatherName;
    }

    public void setFatherName(@NotNull String fatherName) {
        this.fatherName = fatherName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    public @NotNull LocalDate getDob() {
        return dob;
    }

    public void setDob(@NotNull LocalDate dob) {
        this.dob = dob;
    }

    public @NotNull LookupDAO getGender() {
        return gender;
    }

    public void setGender(@NotNull LookupDAO gender) {
        this.gender = gender;
    }

    public LookupDAO getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(LookupDAO maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public LookupDAO getEducationalLevel() {
        return educationalLevel;
    }


    public void setEducationalLevel(LookupDAO educationalLevel) {
        this.educationalLevel = educationalLevel;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(LocalDateTime creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public UUID getCreatedUserUuid() {
        return createdUserUuid;
    }

    public void setCreatedUserUuid(UUID createdUserUuid) {
        this.createdUserUuid = createdUserUuid;
    }
}
