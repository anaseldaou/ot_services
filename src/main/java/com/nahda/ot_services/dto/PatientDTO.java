package com.nahda.ot_services.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PatientDTO {
    private UUID uuid;
    private String code;
    private UUID individualUUID;
    private String firstName;
    private UUID guardianRelationshipUUID;
    private LocalDate dob; // Use LocalDate if needed
    private LookupDTO classe;
    private LookupDTO gender;
    private LookupDTO generalHealthStatus;
    private NoteLookupDTO earlyGrowth;
    private NoteLookupDTO earlyFacedDiseases;
    private List<PhysicalAccidentDTO> physicalAccident;
    private NoteLookupDTO hearing;
    private NoteLookupDTO sight;
    private List<LackOfAcademicAchievementDTO> lackOfAcademicAchievement;
    private List<RepeatedClassDTO> repeatedClasses;
    private List<OldSchoolDTO> oldSchools;
    private LookupDTO motivationToSchool;
    private LocalDateTime creationDatetime;
    private UUID createdUserUuid;

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

    public UUID getIndividualUUID() {
        return individualUUID;
    }

    public void setIndividualUUID(UUID individualUUID) {
        this.individualUUID = individualUUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UUID getGuardianRelationshipUUID() {
        return guardianRelationshipUUID;
    }

    public void setGuardianRelationshipUUID(UUID guardianRelationshipUUID) {
        this.guardianRelationshipUUID = guardianRelationshipUUID;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LookupDTO getGender() {
        return gender;
    }

    public void setGender(LookupDTO gender) {
        this.gender = gender;
    }

    public LookupDTO getGeneralHealthStatus() {
        return generalHealthStatus;
    }

    public void setGeneralHealthStatus(LookupDTO generalHealthStatus) {
        this.generalHealthStatus = generalHealthStatus;
    }

    public NoteLookupDTO getEarlyGrowth() {
        return earlyGrowth;
    }

    public void setEarlyGrowth(NoteLookupDTO earlyGrowth) {
        this.earlyGrowth = earlyGrowth;
    }

    public NoteLookupDTO getEarlyFacedDiseases() {
        return earlyFacedDiseases;
    }

    public void setEarlyFacedDiseases(NoteLookupDTO earlyFacedDiseases) {
        this.earlyFacedDiseases = earlyFacedDiseases;
    }

    public List<PhysicalAccidentDTO> getPhysicalAccident() {
        return physicalAccident;
    }

    public void setPhysicalAccident(List<PhysicalAccidentDTO> physicalAccident) {
        this.physicalAccident = physicalAccident;
    }

    public NoteLookupDTO getHearing() {
        return hearing;
    }

    public void setHearing(NoteLookupDTO hearing) {
        this.hearing = hearing;
    }

    public NoteLookupDTO getSight() {
        return sight;
    }

    public void setSight(NoteLookupDTO sight) {
        this.sight = sight;
    }

    public List<LackOfAcademicAchievementDTO> getLackOfAcademicAchievement() {
        return lackOfAcademicAchievement;
    }

    public void setLackOfAcademicAchievement(List<LackOfAcademicAchievementDTO> lackOfAcademicAchievement) {
        this.lackOfAcademicAchievement = lackOfAcademicAchievement;
    }
    public LookupDTO getClasse() {
        return classe;
    }

    public void setClasse(LookupDTO classe) {
        this.classe = classe;
    }
    public List<RepeatedClassDTO> getRepeatedClasses() {
        return repeatedClasses;
    }

    public void setRepeatedClasses(List<RepeatedClassDTO> repeatedClasses) {
        this.repeatedClasses = repeatedClasses;
    }

    public List<OldSchoolDTO> getOldSchools() {
        return oldSchools;
    }

    public void setOldSchools(List<OldSchoolDTO> oldSchools) {
        this.oldSchools = oldSchools;
    }

    public LookupDTO getMotivationToSchool() {
        return motivationToSchool;
    }

    public void setMotivationToSchool(LookupDTO motivationToSchool) {
        this.motivationToSchool = motivationToSchool;
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