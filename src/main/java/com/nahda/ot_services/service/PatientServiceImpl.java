package com.nahda.ot_services.service;

import com.nahda.ot_services.dao.IndividualDAO;
import com.nahda.ot_services.dao.PatientDAO;
import com.nahda.ot_services.dto.*;
import com.nahda.ot_services.mappers.IIndividualMapper;
import com.nahda.ot_services.mappers.IOccupationalTherapistMapper;
import com.nahda.ot_services.mappers.IPatientMapper;
import com.nahda.ot_services.repository.interfaces.IPatientRepository;
import com.nahda.ot_services.service.interfaces.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IIndividualService individualService;

    @Autowired
    private IPatientMapper patientMapper;

    @Autowired
    private IIndividualMapper individualMapper;

    @Autowired
    private IOccupationalTherapistMapper occupationalTherapistMapper;

    @Autowired
    private IRelationshipTypeService relationshipTypeService;

    @Autowired
    private IFamilyService familyService;

    @Autowired
    private JasperReportService jasperReportService;

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    @Transactional
    public OccupationalTherapistResponseDTO addPatient(OccupationalTherapistRequestDTO occupationalTherapistRequestDTO) {
        PatientDTO patientDTO = occupationalTherapistRequestDTO.getPatient();
        if(patientDTO.getUuid() == null){ // adding new patient
            if(occupationalTherapistRequestDTO.getFather().getUuid() == null){ // check if the father already exists
                occupationalTherapistRequestDTO.getFather().setGender(new LookupDTO(UUID.fromString("b7f26a32-8450-43e1-ba90-37c52badb93e")));
                occupationalTherapistRequestDTO.setFather(individualService.addIndividual(occupationalTherapistRequestDTO.getFather()));
            }
            if(occupationalTherapistRequestDTO.getMother().getUuid() == null) { // check if the mother already exists
                occupationalTherapistRequestDTO.getMother().setGender(new LookupDTO(UUID.fromString("72d5dc27-e9e2-448f-a363-375f021504a3")));
                occupationalTherapistRequestDTO.setMother(individualService.addIndividual(occupationalTherapistRequestDTO.getMother()));
            }
            addPatientIndividual(occupationalTherapistRequestDTO);
            addPatientFamily(occupationalTherapistRequestDTO);
            addFamilyRelationship(occupationalTherapistRequestDTO);
            UUID patientUUID = UUID.randomUUID();
            occupationalTherapistRequestDTO.getPatient().setUuid(patientUUID);
            PatientDAO patientDAO = patientMapper.fromPatientDTOtoPatientDAO(occupationalTherapistRequestDTO.getPatient());
            patientDAO.getLackOfAcademicAchievement().forEach( lack -> lack.setUuid(UUID.randomUUID()));
            patientDAO.getRepeatedClasses().forEach( repeatedClassDAO -> repeatedClassDAO.setUuid(UUID.randomUUID()));
            patientDAO.setCreatedUserUUID(authService.getUserUuid());
            patientDAO.setCreatedGroupUUID(authService.getGroupUUID());
            patientDAO.setCreationDateTime(LocalDateTime.now(ZoneId.of("UTC")));
            patientDAO.setCode(generatePatientCode());
            patientRepository.addPatient(patientDAO);
            return  getPatientByUUID(patientDAO.getUuid());
        } else {
            return updatePatient(occupationalTherapistRequestDTO);
        }
    }

    private OccupationalTherapistResponseDTO updatePatient(OccupationalTherapistRequestDTO occupationalTherapistRequestDTO) {
        occupationalTherapistRequestDTO.getMother().setGender(new LookupDTO(UUID.fromString("72d5dc27-e9e2-448f-a363-375f021504a3")));
        occupationalTherapistRequestDTO.getFather().setGender(new LookupDTO(UUID.fromString("b7f26a32-8450-43e1-ba90-37c52badb93e")));
        occupationalTherapistRequestDTO.setFather(individualService.updateIndividual(occupationalTherapistRequestDTO.getFather()));
        occupationalTherapistRequestDTO.setMother(individualService.updateIndividual(occupationalTherapistRequestDTO.getMother()));
        updatePatientIndividual(occupationalTherapistRequestDTO);
        if(occupationalTherapistRequestDTO.getGuardian().getIsMother()){
            occupationalTherapistRequestDTO.getPatient().setGuardianRelationshipUUID(relationshipTypeService.getRelationshipUUID(occupationalTherapistRequestDTO.getMother().getUuid(),occupationalTherapistRequestDTO.getPatient().getIndividualUUID()));
        } else if(occupationalTherapistRequestDTO.getGuardian().getIsFather()){
            occupationalTherapistRequestDTO.getPatient().setGuardianRelationshipUUID(relationshipTypeService.getRelationshipUUID(occupationalTherapistRequestDTO.getFather().getUuid(),occupationalTherapistRequestDTO.getPatient().getIndividualUUID()));
        }
        PatientDAO patientDAO = patientMapper.fromPatientDTOtoPatientDAO(occupationalTherapistRequestDTO.getPatient());
        patientDAO.setUpdatedUserUuid(authService.getUserUuid());
        patientDAO.setUpdatedGroupUuid(authService.getGroupUUID());
        patientDAO.setLastUpdateDatetime(LocalDateTime.now(ZoneId.of("UTC")));
        patientDAO.getLackOfAcademicAchievement().forEach( lack -> lack.setUuid(UUID.randomUUID()));
        patientDAO.getRepeatedClasses().forEach( repeatedClassDAO -> repeatedClassDAO.setUuid(UUID.randomUUID()));
        patientRepository.updatePatient(patientDAO);
        return  getPatientByUUID(patientDAO.getUuid());
    }

    @Override
    public OccupationalTherapistResponseDTO getPatientByUUID(UUID patientUUID) {
        PatientDAO patientDAO = patientRepository.getPatientByUUID(patientUUID);
        IndividualDAO father =  individualService.getFatherUUID(patientDAO.getIndividualUUID());
        IndividualDAO mother = individualService.getMotherUUID(patientDAO.getIndividualUUID());
        OccupationalTherapistResponseDTO response = new OccupationalTherapistResponseDTO();
        response.setGuardian(new GuardianResponseDTO());
        response.getGuardian().setIsFather( patientDAO.getGuardianRelationshipUUID().equals(IRelationshipTypeService.RELATIONSHIP_FATHER_TYPE));
        response.getGuardian().setIsMother(patientDAO.getGuardianRelationshipUUID().equals(IRelationshipTypeService.RELATIONSHIP_MOTHER_TYPE));
        response.setFather(individualMapper.fromIndividualDAOToIndividualResponseDTO(father));
        response.setMother(individualMapper.fromIndividualDAOToIndividualResponseDTO(mother));
        response.setPatient(patientMapper.fromPatientDAOtoPatientDTO(patientDAO));
        return response;
    }

    @Override
    public List<PatientListDTO> getPatientList() {
        return patientMapper.fromPatientListDAOtoPatientListDTO(patientRepository.getPatientList());
    }

    @Override
    public byte[] generateOTReport(UUID patientUuid) throws IOException, JRException {
        // Get the GraphicsEnvironment object
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Get all available font family names
        String[] fonts = ge.getAvailableFontFamilyNames();

        // Print the font family names
        for (String font : fonts) {
            System.out.println(font);
        }

        OccupationalTherapistResponseDTO occupationalTherapistResponseDTO = getPatientByUUID(patientUuid);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("StudentFirstName",occupationalTherapistResponseDTO.getPatient().getFirstName());
        parameters.put("StudentFullName",occupationalTherapistResponseDTO.getPatient().getFirstName()+ " " + occupationalTherapistResponseDTO.getFather().getFirstName() + " " + occupationalTherapistResponseDTO.getFather().getLastName());
        parameters.put("StudentCode",occupationalTherapistResponseDTO.getPatient().getCode());
        parameters.put("StudentClass",occupationalTherapistResponseDTO.getPatient().getClasse().getName());
        parameters.put("StudentDOB", Date.from(occupationalTherapistResponseDTO.getPatient().getDob().atStartOfDay(ZoneId.of("UTC")).toInstant()));
        parameters.put("TodayDate", Date.from(LocalDate.now(ZoneId.of("UTC")).atStartOfDay(ZoneId.of("UTC")).toInstant()));
        parameters.put("StudentAge",calculateAge(occupationalTherapistResponseDTO.getPatient().getDob()));
        parameters.put("StudentPOB",occupationalTherapistResponseDTO.getPatient().getClasse().getName());
        IndividualRequestDTO guardian = occupationalTherapistResponseDTO.getGuardian().getIsFather() ? occupationalTherapistResponseDTO.getFather() : occupationalTherapistResponseDTO.getMother();
        parameters.put("GuardianName",guardian.getFirstName() + " " + guardian.getFatherName() + " " +  guardian.getLastName());
        parameters.put("GuardianJob",guardian.getJob());
        parameters.put("GuardianRelation",occupationalTherapistResponseDTO.getGuardian().getIsFather() ? "الأب" : "الأم");
        parameters.put("GuardianPhone",guardian.getPhoneNumber());
        parameters.put("GuardianJobAddress","");
        parameters.put("MotherJob",occupationalTherapistResponseDTO.getMother().getJob());
        parameters.put("FatherJob",occupationalTherapistResponseDTO.getFather().getJob());
        parameters.put("FatherEducationalLevel",occupationalTherapistResponseDTO.getFather().getEducationalLevel().getName());
        parameters.put("MotherEducationalLevel",occupationalTherapistResponseDTO.getMother().getEducationalLevel().getName());
        parameters.put("sights",occupationalTherapistResponseDTO.getPatient().getSight().getName());
        parameters.put("sightsNote",occupationalTherapistResponseDTO.getPatient().getSight().getNote());
        parameters.put("hearing",occupationalTherapistResponseDTO.getPatient().getHearing().getName());
        parameters.put("hearingNote",occupationalTherapistResponseDTO.getPatient().getHearing().getNote());
        parameters.put("GeneralHealthStatus",occupationalTherapistResponseDTO.getPatient().getGeneralHealthStatus().getName());
        parameters.put("GrowthStatus",occupationalTherapistResponseDTO.getPatient().getEarlyGrowth().getName());
        parameters.put("earlyFacedDiseases", occupationalTherapistResponseDTO.getPatient().getPhysicalAccident());
        parameters.put("lackOfAchievment", occupationalTherapistResponseDTO.getPatient().getLackOfAcademicAchievement()
                .stream().map(lack -> new LackOfAcademicAchievementReportDTO(lack.getRelationshipType().getName(),lack.getAcademicCycle().getName(), lack.getSubject().stream().map(sub -> sub.getName()).collect(Collectors.joining(" , ")))).toList());
        parameters.put("oldSchools", occupationalTherapistResponseDTO.getPatient().getOldSchools().stream().map( school -> new OldSchoolReportDTO(school.getSchool().getName(),school.getLeaveReason())).toList());
        parameters.put("repeatedClasses", occupationalTherapistResponseDTO.getPatient().getRepeatedClasses().stream().map( repeatedClasses -> new RepeatedClassReportDTO(repeatedClasses.getClasse().getName(),repeatedClasses.getTimes())).toList());
        String templatePath = "reports/general_info.jrxml"; // Adjust path as needed
        JasperPrint jasperPrint = jasperReportService.generateReport(templatePath, parameters);
        jasperPrint.getStyles();
        byte[] pdfBytes = jasperReportService.exportReportToPdf(jasperPrint);
        return pdfBytes;
    }

    private String generatePatientCode() {
        // Format as a zero-padded 5-digit number
        return String.format("%05d", patientRepository.getPatientCount()+1);
    }
    public static int calculateAge(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);
        return period.getYears();
    }
    private void addPatientIndividual(OccupationalTherapistRequestDTO occupationalTherapistRequestDTO) {
        IndividualRequestDTO patientIndividualDTO = getInvidualOfPatient(occupationalTherapistRequestDTO);
        IndividualRequestDTO patientIndidivudal=  individualService.addIndividual(patientIndividualDTO);
        occupationalTherapistRequestDTO.getPatient().setIndividualUUID(patientIndidivudal.getUuid());
    }

    private void updatePatientIndividual(OccupationalTherapistRequestDTO occupationalTherapistRequestDTO) {
        IndividualRequestDTO patientIndividualDTO = getInvidualOfPatient(occupationalTherapistRequestDTO);
        IndividualRequestDTO patientIndidivudal=  individualService.updateIndividual(patientIndividualDTO);
    }

    private void addPatientFamily(OccupationalTherapistRequestDTO occupationalTherapistRequestDTO) {
        UUID familyUUId = UUID.randomUUID();
        familyService.addFamily(familyUUId);
        occupationalTherapistRequestDTO.setFamilyUUID(familyUUId);
    }

    private void addFamilyRelationship(OccupationalTherapistRequestDTO occupationalTherapistRequestDTO) {
        UUID fatherRelationship = UUID.randomUUID();
        UUID motherRelationship = UUID.randomUUID();
        relationshipTypeService.addRelationshipType(new RelationshipTypeDTO(
                fatherRelationship,
                occupationalTherapistRequestDTO.getFather().getUuid(),
                occupationalTherapistRequestDTO.getPatient().getIndividualUUID(),
                IRelationshipTypeService.RELATIONSHIP_FATHER_TYPE,
                occupationalTherapistRequestDTO.getFamilyUUID())
                );
        relationshipTypeService.addRelationshipType(new RelationshipTypeDTO(
                motherRelationship,
                occupationalTherapistRequestDTO.getMother().getUuid(),
                occupationalTherapistRequestDTO.getPatient().getIndividualUUID(),
                IRelationshipTypeService.RELATIONSHIP_MOTHER_TYPE,
                occupationalTherapistRequestDTO.getFamilyUUID())
        );

        if(occupationalTherapistRequestDTO.getGuardian().getIsMother()){
            occupationalTherapistRequestDTO.getPatient().setGuardianRelationshipUUID(motherRelationship);
        } else if(occupationalTherapistRequestDTO.getGuardian().getIsFather()){
            occupationalTherapistRequestDTO.getPatient().setGuardianRelationshipUUID(fatherRelationship);
        }
    }

    private IndividualRequestDTO getInvidualOfPatient(OccupationalTherapistRequestDTO occupationalTherapistRequestDTO) {
        PatientDTO patientDTO = occupationalTherapistRequestDTO.getPatient();
        IndividualRequestDTO patientIndividualDTO = new IndividualRequestDTO();
        patientIndividualDTO.setUuid(patientDTO.getIndividualUUID());
        patientIndividualDTO.setFirstName(patientDTO.getFirstName());
        patientIndividualDTO.setGender(new LookupDTO(patientDTO.getGender().getUuid()));
        patientIndividualDTO.setFatherName(occupationalTherapistRequestDTO.getFather().getFirstName());
        patientIndividualDTO.setLastName(occupationalTherapistRequestDTO.getFather().getLastName());
        patientIndividualDTO.setDob(patientDTO.getDob());
        return patientIndividualDTO;
    }
}
