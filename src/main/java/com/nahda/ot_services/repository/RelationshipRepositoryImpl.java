package com.nahda.ot_services.repository;

import com.nahda.ot_services.dao.RelationshipTypeDAO;
import com.nahda.ot_services.repository.interfaces.IRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RelationshipRepositoryImpl implements IRelationshipRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addRelationship(RelationshipTypeDAO relationshipTypeDAO) {
        try{
            StringBuilder sqlQuery = new StringBuilder();

            sqlQuery.append("INSERT INTO public.individual_relationship ")
                    .append("(\"uuid\",")
                    .append("\"from\",")
                    .append("\"to\",")
                    .append("\"relationship_type_uuid\",")
                    .append("\"family_uuid\")")
                    .append(" VALUES (?, ?, ?, ?, ?)");
            return jdbcTemplate.update(sqlQuery.toString(),
                    relationshipTypeDAO.getUuid(),
                    relationshipTypeDAO.getFrom(),
                    relationshipTypeDAO.getTo(),
                    relationshipTypeDAO.getRelationshipTypeUUID(),
                    relationshipTypeDAO.getFamilyUUID()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UUID getRelationshipUUID(UUID from, UUID to) {
        try {
            String query = "SELECT uuid FROM individual_relationship WHERE \"from\" = ? AND \"to\" = ?";
            return jdbcTemplate.queryForObject(query, new Object[]{from, to}, UUID.class);
        } catch (EmptyResultDataAccessException e) {
            return null; // No relationship found
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred while fetching relationship UUID", e);
        }
    }

}
