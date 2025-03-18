package com.nahda.ot_services.repository.repoMapper;

import com.nahda.ot_services.dao.IndividualDAO;
import com.nahda.ot_services.dao.LookupDAO;
import com.nahda.ot_services.dao.NoteLookupDAO;
import com.nahda.ot_services.dao.PatientDAO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.util.UUID;

public class PatientDAORowMapper  implements RowMapper<PatientDAO> {

    @Override
    public PatientDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PatientDAO patient = new PatientDAO();
        patient.setUuid(rs.getObject("patient_uuid", UUID.class));
        patient.setIndividualUUID(rs.getObject("individual_uuid", UUID.class));
        patient.setCreatedUserUUID(rs.getObject("created_user_uuid", UUID.class));
        patient.setCreatedGroupUUID(rs.getObject("created_group_uuid", UUID.class));
        patient.setCreationDateTime(rs.getTimestamp("creation_date_time").toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime());
        patient.setCode(rs.getString("code"));
        patient.setGuardianRelationshipUUID(rs.getObject("guardian_relation_type_uuid", UUID.class));
        if(rs.getObject("class_info_uuid", UUID.class) != null){
            LookupDAO classInfo = new LookupDAO();
            classInfo.setUuid(rs.getObject("class_info_uuid", UUID.class));
            classInfo.setName(rs.getString("class_info_name"));
            patient.setClasse(classInfo);
        }
        if(rs.getObject("room", UUID.class) != null){
            LookupDAO room = new LookupDAO();
            room.setUuid(rs.getObject("room", UUID.class));
            patient.setRoom(room);
        }
        if(rs.getObject("general_health_status_uuid", UUID.class) != null){
            LookupDAO generalHealthStatus = new LookupDAO();
            generalHealthStatus.setUuid(rs.getObject("general_health_status_uuid", UUID.class));
            generalHealthStatus.setName(rs.getString("general_health_status_name"));
            patient.setGeneralHealthStatus(generalHealthStatus);
        }
        if (rs.getObject("early_growth_uuid", UUID.class) != null) {
            NoteLookupDAO earlyGrowth = new NoteLookupDAO();
            earlyGrowth.setUuid(rs.getObject("early_growth_uuid", UUID.class));
            earlyGrowth.setName(rs.getString("early_growth_name"));
            earlyGrowth.setNote(rs.getString("early_growth_note"));
            patient.setEarlyGrowth(earlyGrowth);
        }

        if (rs.getObject("early_faced_diseases_uuid", UUID.class) != null) {
            NoteLookupDAO earlyFacedDiseases = new NoteLookupDAO();
            earlyFacedDiseases.setUuid(rs.getObject("early_faced_diseases_uuid", UUID.class));
            earlyFacedDiseases.setName(rs.getString("early_faced_diseases_name"));
            earlyFacedDiseases.setNote(rs.getString("early_faced_diseases_note"));
            patient.setEarlyFacedDiseases(earlyFacedDiseases);
        }

        if (rs.getObject("hearing_uuid", UUID.class) != null) {
            NoteLookupDAO hearing = new NoteLookupDAO();
            hearing.setUuid(rs.getObject("hearing_uuid", UUID.class));
            hearing.setName(rs.getString("hearing_name"));
            hearing.setNote(rs.getString("hearing_note"));
            patient.setHearing(hearing);
        }

        if (rs.getObject("sight_uuid", UUID.class) != null) {
            NoteLookupDAO sight = new NoteLookupDAO();
            sight.setUuid(rs.getObject("sight_uuid", UUID.class));
            sight.setName(rs.getString("sight_name"));
            sight.setNote(rs.getString("sights_note"));
            patient.setSight(sight);
        }

        if (rs.getObject("motivation_to_school_uuid", UUID.class) != null) {
            LookupDAO motivationToSchool = new LookupDAO();
            motivationToSchool.setUuid(rs.getObject("motivation_to_school_uuid", UUID.class));
            motivationToSchool.setName(rs.getString("motivation_to_school_name"));
            patient.setMotivationToSchool(motivationToSchool);
        }

        IndividualDAO patientIndividual = new IndividualDAO();
        patientIndividual.setFirstName(rs.getString("patient_first_name"));
        patientIndividual.setDob(rs.getTimestamp("patient_dob").toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDate());
        patientIndividual.setGender(new LookupDAO(UUID.fromString(rs.getString("gender_uuid")),rs.getString("gender_name")));
        patient.setIndividual(patientIndividual);
        return patient;
}
}
