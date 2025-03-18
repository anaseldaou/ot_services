package com.nahda.ot_services.dto;


import java.util.UUID;

public class OccupationalTherapistRequestDTO {

    private IndividualRequestDTO father;
    private IndividualRequestDTO mother;
    private GuardianRequestDTO guardian;
    private PatientDTO patient;

    private UUID familyUUID;

    public OccupationalTherapistRequestDTO(IndividualRequestDTO father, IndividualRequestDTO mother, GuardianRequestDTO guardian, PatientDTO patient) {
        this.father = father;
        this.mother = mother;
        this.guardian = guardian;
        this.patient = patient;
    }


    public IndividualRequestDTO getFather() {
        return father;
    }

    public void setFather(IndividualRequestDTO father) {
        this.father = father;
    }

    public IndividualRequestDTO getMother() {
        return mother;
    }

    public void setMother(IndividualRequestDTO mother) {
        this.mother = mother;
    }

    public GuardianRequestDTO getGuardian() {
        return guardian;
    }

    public void setGuardian(GuardianRequestDTO guardian) {
        this.guardian = guardian;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public UUID getFamilyUUID() {
        return familyUUID;
    }

    public void setFamilyUUID(UUID familyUUID) {
        this.familyUUID = familyUUID;
    }
}
