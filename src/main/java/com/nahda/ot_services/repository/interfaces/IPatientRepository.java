package com.nahda.ot_services.repository.interfaces;

import com.nahda.ot_services.dao.PatientDAO;
import com.nahda.ot_services.dao.PatientListDAO;

import java.util.List;
import java.util.UUID;

public interface IPatientRepository {

    PatientDAO addPatient(PatientDAO patientDAO);
    int updatePatient(PatientDAO patient);

    int getPatientCount();
    PatientDAO getPatientByUUID(UUID uuid);

    List<PatientListDAO> getPatientList();
}
