package com.nahda.ot_services.repository;

import com.nahda.ot_services.dao.UserInfoDAO;
import com.nahda.ot_services.repository.repoMapper.UserInfoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserInfoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public String findMessageById(int id) {
//        String sql = "SELECT message FROM hello_world WHERE id = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
//    }
//
//    public void insertMessage(String message) {
//        String sql = "INSERT INTO hello_world (message) VALUES (?)";
//        jdbcTemplate.update(sql, message);
//    }
    public Optional<UserInfoDAO> findByUsername(String username){
        String sqlQuery = "SELECT id,username FROM users WHERE username = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sqlQuery,new Object[]{username}, new UserInfoRowMapper()));
    } // Use 'email' if that is the correct field for login

    public int insertUser(UserInfoDAO userInfoDAO){
        String sqlQuery = "INSERT INTO users (username,id,hash,creation_datetime) VALUES (?,?,?,timezone('utc', now()))";
        return jdbcTemplate.update(sqlQuery, userInfoDAO.getUsername(), userInfoDAO.getUuid(), userInfoDAO.getPassword());
    }
}