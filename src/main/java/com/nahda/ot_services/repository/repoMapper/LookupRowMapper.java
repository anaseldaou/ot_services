package com.nahda.ot_services.repository.repoMapper;

import com.nahda.ot_services.dao.LookupDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LookupRowMapper implements ResultSetExtractor<List<LookupDAO>> {
    @Override
    public List<LookupDAO> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<LookupDAO> result = new ArrayList<>();
        while (rs.next()) {
            LookupDAO option = new LookupDAO();
            option.setUuid(UUID.fromString(rs.getString("uuid")));
            option.setName(rs.getString("name"));
            result.add(option);
        }
        return result;
    }
}
