package com.nahda.ot_services.controller;

import com.nahda.ot_services.dto.OccupationalTherapistRequestDTO;
import com.nahda.ot_services.dto.OccupationalTherapistResponseDTO;
import com.nahda.ot_services.dto.PatientListDTO;
import com.nahda.ot_services.service.interfaces.IPatientService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService){
        this.patientService = patientService;
    }

    @PreAuthorize("@authService.hasPermission('ADD_PATIENT')")
    @PostMapping("add")
    public ResponseEntity<OccupationalTherapistResponseDTO> addIndividual(@RequestBody OccupationalTherapistRequestDTO occupationalTherapistRequestDTO) {
        return ResponseEntity.ok(patientService.addPatient(occupationalTherapistRequestDTO));
    }
    @PreAuthorize("@authService.hasPermission('READ_PATIENT')")
    @GetMapping("/{uuid}")
    public ResponseEntity<OccupationalTherapistResponseDTO> getPatientForOccupationalTherapist(@PathVariable UUID uuid) {
        return ResponseEntity.ok(patientService.getPatientByUUID(uuid));
    }

    @PreAuthorize("@authService.hasPermission('READ_PATIENT')")
    @GetMapping("/list")
    public ResponseEntity<List<PatientListDTO>> getPatientListForOccupationalTherapist() {
        return ResponseEntity.ok(patientService.getPatientList());
    }
    @GetMapping("/generate-report/{uuid}")
    public ResponseEntity<byte[]> generatOTeReport(@PathVariable UUID uuid) throws IOException, JRException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(patientService.generateOTReport(uuid));
    }
}
