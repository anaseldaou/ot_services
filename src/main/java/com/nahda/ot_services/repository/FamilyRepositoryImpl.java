package com.nahda.ot_services.repository;

import com.nahda.ot_services.repository.interfaces.IFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FamilyRepositoryImpl implements IFamilyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UUID addFamily(UUID uuid) {
        String sqlQuery = "INSERT INTO public.family" +
                "(uuid)" +
                " VALUES (?)";
        jdbcTemplate.update(sqlQuery, uuid);
        return uuid;
    }
}
