package com.nahda.ot_services.service.interfaces;

import com.nahda.ot_services.dto.OccupationalTherapistRequestDTO;
import com.nahda.ot_services.dto.OccupationalTherapistResponseDTO;
import com.nahda.ot_services.dto.PatientListDTO;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IPatientService {

    OccupationalTherapistResponseDTO addPatient(OccupationalTherapistRequestDTO patient);
    OccupationalTherapistResponseDTO getPatientByUUID(UUID patientUUID);

    List<PatientListDTO> getPatientList();

    byte[] generateOTReport(UUID patientUuid) throws IOException, JRException;
}
