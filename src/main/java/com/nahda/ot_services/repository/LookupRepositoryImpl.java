package com.nahda.ot_services.repository;

import com.nahda.ot_services.dao.LookupDAO;
import com.nahda.ot_services.repository.interfaces.ILookupRepository;
import com.nahda.ot_services.repository.repoMapper.LookupRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LookupRepositoryImpl implements ILookupRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<LookupDAO> getLookup(String tableName) {
        String sqlQuery = "select uuid, name from public." + tableName;
        return jdbcTemplate.query(sqlQuery, new LookupRowMapper());

    }
}
