package com.nahda.ot_services.repository;

import com.nahda.ot_services.dao.IndividualDAO;
import com.nahda.ot_services.repository.interfaces.IIndividualRepository;
import com.nahda.ot_services.repository.interfaces.IRelationshipRepository;
import com.nahda.ot_services.repository.repoMapper.IndividualRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

import java.util.Optional;
import java.util.UUID;

@Repository
public class IndividualRepository implements IIndividualRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertIndividual(IndividualDAO individualDAO){
        try{
            StringBuilder sqlQuery = new StringBuilder();
            sqlQuery.append("INSERT INTO public.individuals ")
                    .append("(\"uuid\",")
                    .append("\"first_name\",")
                    .append("\"last_name\",")
                    .append("\"father_name\",")
                    .append("\"dob\",")
                    .append("\"gender_uuid\",")
                    .append("\"marital_status_id\",")
                    .append("\"email\",")
                    .append("\"phone_number\",")
                    .append("\"nationality_id\",")
                    .append("\"educational_level_id\",")
                    .append("\"job_name\",")
                    .append("\"creation_datetime\",")
                    .append("\"created_user_id\") ")
                    .append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            return jdbcTemplate.update(sqlQuery.toString(),
                    individualDAO.getUuid(),
                    individualDAO.getFirstName(),
                    individualDAO.getLastName(),
                    individualDAO.getFatherName(),
                    individualDAO.getDob(),
                    individualDAO.getGender().getUuid(),
                    individualDAO.getMaritalStatus() != null ? individualDAO.getMaritalStatus().getUuid() : null,
                    individualDAO.getEmail(),
                    individualDAO.getPhoneNumber(),
                    individualDAO.getNationality() != null ? individualDAO.getNationality().getUuid() : null,
                    individualDAO.getEducationalLevel() != null ? individualDAO.getEducationalLevel().getUuid() : null,
                    individualDAO.getJob(),
                    individualDAO.getCreationDatetime(),
                    individualDAO.getCreatedUserUuid()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<IndividualDAO> getIndividualByID(UUID uuid){
        try{
            String sqlQuery = "SELECT indiv.\"uuid\", first_name, last_name, father_name, dob, gender_uuid, marital_status_id, email, phone_number, nationality_id, educational_level_id, job_name, creation_datetime, created_user_id , g.\"name\" as gender_name, n.\"name\" as nationality_name, el.\"name\" as educational_level_name, ms.\"name\" as marital_status_name\n" +
                    "FROM public.individuals as indiv \n" +
                    "left join public.\"gender\" as G on G.\"uuid\" = indiv.gender_uuid \n" +
                    "left join public.\"nationality\" N on N.\"uuid\" = indiv.nationality_id \n" +
                    "left join public.\"educational_level\" el on el.\"uuid\" = indiv.educational_level_id \n" +
                    "left join public.\"marital_status\" ms on ms.\"uuid\"  = indiv.marital_status_id \n" +
                    "WHERE indiv.\"uuid\"=?;";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sqlQuery,new Object[]{uuid}, new IndividualRowMapper()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public IndividualDAO getFromIndividualByRelationshipType(UUID toIndividualUUID, UUID relationshipTypeUUID){
        try{

            String sqlQuery = "SELECT indiv.\"uuid\", first_name, last_name, father_name, dob, gender_uuid, marital_status_id, email, phone_number, nationality_id, educational_level_id, job_name, creation_datetime, created_user_id , g.\"name\" as gender_name, n.\"name\" as nationality_name, el.\"name\" as educational_level_name, ms.\"name\" as marital_status_name \n" +
                    "FROM public.individual_relationship ir " +
                    "LEFT JOIN public.individuals as indiv on indiv.\"uuid\" = ir.\"from\" " +
                    "left join public.\"gender\" as G on G.\"uuid\" = indiv.gender_uuid \n" +
                    "left join public.\"nationality\" N on N.\"uuid\" = indiv.nationality_id \n" +
                    "left join public.\"educational_level\" el on el.\"uuid\" = indiv.educational_level_id \n" +
                    "left join public.\"marital_status\" ms on ms.\"uuid\"  = indiv.marital_status_id \n" +
                    "WHERE ir.\"to\"=? and ir.relationship_type_uuid = ?";
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sqlQuery,new Object[]{toIndividualUUID,relationshipTypeUUID}, new IndividualRowMapper()))
            .orElseThrow(() -> new NotFoundException("No father exists for individual UUID = " + toIndividualUUID));

        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No father exists for individual UUID = " + toIndividualUUID);
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    @Override
    public int updateIndividual(IndividualDAO individualDAO) {
        try {
            StringBuilder sqlQuery = new StringBuilder();
            sqlQuery.append("UPDATE public.individuals SET ")
                    .append("\"first_name\" = ?, ")
                    .append("\"last_name\" = ?, ")
                    .append("\"father_name\" = ?, ")
                    .append("\"dob\" = ?, ")
                    .append("\"gender_uuid\" = ?, ")
                    .append("\"marital_status_id\" = ?, ")
                    .append("\"email\" = ?, ")
                    .append("\"phone_number\" = ?, ")
                    .append("\"nationality_id\" = ?, ")
                    .append("\"educational_level_id\" = ?, ")
                    .append("\"job_name\" = ?, ")
                    .append("\"last_update_datetime\" = ?, ")
                    .append("\"last_update_user_uuid\" = ? ")
                    .append("WHERE \"uuid\" = ?");

            return jdbcTemplate.update(sqlQuery.toString(),
                    individualDAO.getFirstName(),
                    individualDAO.getLastName(),
                    individualDAO.getFatherName(),
                    individualDAO.getDob(),
                    individualDAO.getGender().getUuid(),
                    individualDAO.getMaritalStatus() != null ? individualDAO.getMaritalStatus().getUuid() : null,
                    individualDAO.getEmail(),
                    individualDAO.getPhoneNumber(),
                    individualDAO.getNationality() != null ? individualDAO.getNationality().getUuid() : null,
                    individualDAO.getEducationalLevel() != null ? individualDAO.getEducationalLevel().getUuid() : null,
                    individualDAO.getJob(),
                    individualDAO.getLastUpdateDatetime(),
                    individualDAO.getUpdatedUserUuid(),
                    individualDAO.getUuid() // WHERE condition
            );
        } catch (Exception e) {
            throw new RuntimeException("Error updating individual with UUID: " + individualDAO.getUuid(), e);
        }
    }

}
