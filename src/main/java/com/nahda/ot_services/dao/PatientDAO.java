package com.nahda.ot_services.dao;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PatientDAO {
    private UUID uuid;
    private String code;
    private UUID individualUUID;
    private UUID fatherUUID;
    private UUID motherUUID;
    private IndividualDAO individual;
    private UUID guardianRelationshipUUID;
    private String dob; // Use LocalDate if needed
    private LookupDAO classe;
    private LookupDAO room;
    private LookupDAO generalHealthStatus;
    private NoteLookupDAO earlyGrowth;
    private NoteLookupDAO earlyFacedDiseases;
    private List<PhysicalAccidentDAO> physicalAccident;
    private NoteLookupDAO hearing;
    private NoteLookupDAO sight;
    private List<LackOfAcademicAchievementDAO> lackOfAcademicAchievement;
    private List<RepeatedClassDAO> repeatedClasses;
    private List<OldSchoolDAO> oldSchools;
    private LookupDAO motivationToSchool;
    private UUID createdUserUUID;
    private UUID createdGroupUUID;
    private LocalDateTime creationDateTime;
    private UUID updatedUserUuid;
    private UUID updatedGroupUuid;
    private LocalDateTime lastUpdateDatetime;

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

    public UUID getFatherUUID() {
        return fatherUUID;
    }

    public void setFatherUUID(UUID fatherUUID) {
        this.fatherUUID = fatherUUID;
    }

    public UUID getMotherUUID() {
        return motherUUID;
    }

    public void setMotherUUID(UUID motherUUID) {
        this.motherUUID = motherUUID;
    }

    public IndividualDAO getIndividual() {
        return individual;
    }

    public void setIndividual(IndividualDAO individual) {
        this.individual = individual;
    }

    public UUID getGuardianRelationshipUUID() {
        return guardianRelationshipUUID;
    }

    public void setGuardianRelationshipUUID(UUID guardianRelationshipUUID) {
        this.guardianRelationshipUUID = guardianRelationshipUUID;
    }
    public LookupDAO getClasse() {
        return classe;
    }

    public void setClasse(LookupDAO classe) {
        this.classe = classe;
    }
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public LookupDAO getRoom() {
        return room;
    }

    public void setRoom(LookupDAO room) {
        this.room = room;
    }

    public LookupDAO getGeneralHealthStatus() {
        return generalHealthStatus;
    }

    public void setGeneralHealthStatus(LookupDAO generalHealthStatus) {
        this.generalHealthStatus = generalHealthStatus;
    }

    public NoteLookupDAO getEarlyGrowth() {
        return earlyGrowth;
    }

    public void setEarlyGrowth(NoteLookupDAO earlyGrowth) {
        this.earlyGrowth = earlyGrowth;
    }

    public NoteLookupDAO getEarlyFacedDiseases() {
        return earlyFacedDiseases;
    }

    public void setEarlyFacedDiseases(NoteLookupDAO earlyFacedDiseases) {
        this.earlyFacedDiseases = earlyFacedDiseases;
    }

    public List<PhysicalAccidentDAO> getPhysicalAccident() {
        return physicalAccident;
    }

    public void setPhysicalAccident(List<PhysicalAccidentDAO> physicalAccident) {
        this.physicalAccident = physicalAccident;
    }

    public NoteLookupDAO getHearing() {
        return hearing;
    }

    public void setHearing(NoteLookupDAO hearing) {
        this.hearing = hearing;
    }

    public NoteLookupDAO getSight() {
        return sight;
    }

    public void setSight(NoteLookupDAO sight) {
        this.sight = sight;
    }

    public List<LackOfAcademicAchievementDAO> getLackOfAcademicAchievement() {
        return lackOfAcademicAchievement;
    }

    public void setLackOfAcademicAchievement(List<LackOfAcademicAchievementDAO> lackOfAcademicAchievement) {
        this.lackOfAcademicAchievement = lackOfAcademicAchievement;
    }

    public List<RepeatedClassDAO> getRepeatedClasses() {
        return repeatedClasses;
    }

    public void setRepeatedClasses(List<RepeatedClassDAO> repeatedClasses) {
        this.repeatedClasses = repeatedClasses;
    }

    public List<OldSchoolDAO> getOldSchools() {
        return oldSchools;
    }

    public void setOldSchools(List<OldSchoolDAO> oldSchools) {
        this.oldSchools = oldSchools;
    }

    public LookupDAO getMotivationToSchool() {
        return motivationToSchool;
    }

    public void setMotivationToSchool(LookupDAO motivationToSchool) {
        this.motivationToSchool = motivationToSchool;
    }

    public UUID getCreatedUserUUID() {
        return createdUserUUID;
    }

    public void setCreatedUserUUID(UUID createdUserUUID) {
        this.createdUserUUID = createdUserUUID;
    }

    public UUID getCreatedGroupUUID() {
        return createdGroupUUID;
    }

    public void setCreatedGroupUUID(UUID createdGroupUUID) {
        this.createdGroupUUID = createdGroupUUID;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public UUID getUpdatedUserUuid() {
        return updatedUserUuid;
    }

    public void setUpdatedUserUuid(UUID updatedUserUuid) {
        this.updatedUserUuid = updatedUserUuid;
    }

    public UUID getUpdatedGroupUuid() {
        return updatedGroupUuid;
    }

    public void setUpdatedGroupUuid(UUID updatedGroupUuid) {
        this.updatedGroupUuid = updatedGroupUuid;
    }

    public LocalDateTime getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(LocalDateTime lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }
}