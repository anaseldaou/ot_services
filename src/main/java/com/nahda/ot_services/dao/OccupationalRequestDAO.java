package com.nahda.ot_services.dao;


public class OccupationalRequestDAO {
    private IndividualRequestDAO father;
    private IndividualRequestDAO mother;
    private GuardianRequestDAO guardian;
    private PatientDAO patient;

    public IndividualRequestDAO getFather() {
        return father;
    }

    public void setFather(IndividualRequestDAO father) {
        this.father = father;
    }

    public IndividualRequestDAO getMother() {
        return mother;
    }

    public void setMother(IndividualRequestDAO mother) {
        this.mother = mother;
    }

    public GuardianRequestDAO getGuardian() {
        return guardian;
    }

    public void setGuardian(GuardianRequestDAO guardian) {
        this.guardian = guardian;
    }

    public PatientDAO getPatient() {
        return patient;
    }

    public void setPatient(PatientDAO patient) {
        this.patient = patient;
    }
}
