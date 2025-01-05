package com.nahda.ot_services.repository.repoMapper;

import com.nahda.ot_services.dao.UserInfoDAO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserInfoRowMapper  implements RowMapper<UserInfoDAO> {
    @Override
    public UserInfoDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        String username = rs.getString("username");
        return new UserInfoDAO(id, username);
    }
}