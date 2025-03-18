package com.nahda.ot_services.dto;

public class OccupationalTherapistResponseDTO {

    IndividualResponseDTO father;
    IndividualResponseDTO mother;
    GuardianResponseDTO guardian;
    PatientDTO patient;

    public IndividualResponseDTO getFather() {
        return father;
    }

    public void setFather(IndividualResponseDTO father) {
        this.father = father;
    }

    public IndividualResponseDTO getMother() {
        return mother;
    }

    public void setMother(IndividualResponseDTO mother) {
        this.mother = mother;
    }

    public GuardianResponseDTO getGuardian() {
        return guardian;
    }

    public void setGuardian(GuardianResponseDTO guardian) {
        this.guardian = guardian;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }
}
