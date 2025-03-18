package com.nahda.ot_services.dao;

import com.nahda.ot_services.dto.LookupDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

public class IndividualRequestDAO {

    UUID uuid;

    @NotNull
    String firstName;

    @NotNull
    String fatherName;

    @NotNull
    String lastName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dob;

    @NotNull
    LookupDTO gender;

//    @NotNull
    LookupDTO maritalStatus;

//    @NotNull
    LookupDTO educationalLevel;

//    @NotNull
    String job;

    String email;

    String phoneNumber;

    LookupDTO nationality;

    public LookupDTO getNationality() {
        return nationality;
    }

    public void setNationality(LookupDTO nationality) {
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

    public @NotNull LookupDTO getGender() {
        return gender;
    }

    public void setGender(@NotNull LookupDTO gender) {
        this.gender = gender;
    }

    public LookupDTO getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(LookupDTO maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public LookupDTO getEducationalLevel() {
        return educationalLevel;
    }

    public void setEducationalLevel(LookupDTO educationalLevel) {
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
}
