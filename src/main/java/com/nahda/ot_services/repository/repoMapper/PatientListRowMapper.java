package com.nahda.ot_services.repository.repoMapper;

import com.nahda.ot_services.dao.PatientListDAO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.util.UUID;

public class PatientListRowMapper  implements RowMapper<PatientListDAO> {

    @Override
    public PatientListDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PatientListDAO patient = new PatientListDAO();

        patient.setUuid(UUID.fromString(rs.getString("patient_uuid")));
        patient.setCreationDateTime(rs.getTimestamp("creation_date_time").toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime());
        patient.setCode(rs.getString("code"));
        patient.setFirstName(rs.getString("patient_first_name"));
        patient.setFatherName(rs.getString("father_name"));
        patient.setLastName(rs.getString("last_name"));
        patient.setGuardianRelationshipTypeName(rs.getString("guardian_relation_type_name"));
        patient.setDob(rs.getDate("patient_dob").toLocalDate());
        patient.setClassName(rs.getString("class_info"));

        return patient;
    }
}
