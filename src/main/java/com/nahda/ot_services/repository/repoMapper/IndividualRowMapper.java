package com.nahda.ot_services.repository.repoMapper;
import com.nahda.ot_services.dao.IndividualDAO;

import com.nahda.ot_services.dao.LookupDAO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class IndividualRowMapper implements RowMapper<IndividualDAO>  {

    @Override
    public IndividualDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            UUID id = UUID.fromString(rs.getString("uuid"));
            IndividualDAO result = new IndividualDAO();
            result.setUuid(id);
            result.setCreatedUserUuid(UUID.fromString(rs.getString("created_user_id")));
            result.setDob(rs.getDate("dob").toLocalDate());
            result.setFirstName(rs.getString("first_name"));
            result.setLastName(rs.getString("last_name"));
            result.setFatherName(rs.getString("father_name"));
            result.setEmail(rs.getString("email"));
            result.setPhoneNumber(rs.getString("phone_number"));
            result.setGender(new LookupDAO(UUID.fromString(rs.getString("gender_uuid")),rs.getString("gender_name")));
            String nationalityUUID = rs.getString("nationality_id");
            if(nationalityUUID != null) {
                result.setNationality(new LookupDAO(UUID.fromString(nationalityUUID),rs.getString("nationality_name")));
            }
            String educationalLevelUUId = rs.getString("educational_level_id");
            if(educationalLevelUUId != null) {
                result.setEducationalLevel(new LookupDAO(UUID.fromString(educationalLevelUUId),rs.getString("educational_level_name")));

            }
            String maritalStatusUUID = rs.getString("marital_status_id");
            if (maritalStatusUUID != null){
                result.setMaritalStatus(new LookupDAO(UUID.fromString(maritalStatusUUID),rs.getString("marital_status_name")));
            }
            String jobUUID = rs.getString("job_name");
            if (jobUUID != null) {
                result.setJob(rs.getString("job_name"));
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
