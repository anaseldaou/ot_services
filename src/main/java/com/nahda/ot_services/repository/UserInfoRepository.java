package com.nahda.ot_services.repository;

import com.nahda.ot_services.dao.UserInfoDAO;
import com.nahda.ot_services.repository.repoMapper.UserInfoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserInfoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<UserInfoDAO> findByUsername(String username){
        String sqlQuery = "select u.uuid, u.username, g.\"uuid\" as group_id, g.\"name\" as group_name, r.\"uuid\" as role_id ,r.\"name\" as role_name, p.\"uuid\" as permission_id,p.\"name\" as permission_name\n" +
                "from public.users u\n" +
                "left join public.users_groups ug on ug.user_uuid = u.\"uuid\" \n" +
                "left join public.\"groups\" g on g.\"uuid\" = ug.group_uuid \n" +
                "left join public.groups_roles gr on gr.group_uuid = g.\"uuid\" \n" +
                "left join public.roles r on r.\"uuid\" = gr.role_uuid \n" +
                "left join public.roles_permissions rp on rp.role_uuid = r.\"uuid\" \n" +
                "left join public.permissions p on p.\"uuid\" = rp.permission_uuid \n" +
                "where u.username = ?";
        return Optional.ofNullable(jdbcTemplate.query(sqlQuery,new Object[]{username}, new UserInfoRowMapper()));
    } // Use 'email' if that is the correct field for login

    public UUID insertUser(UserInfoDAO userInfoDAO){
        String sql = "SELECT create_user_with_group_procedure(?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, UUID.class,userInfoDAO.getUsername(), userInfoDAO.getPassword(), userInfoDAO.getGroups().stream().findFirst().get().getUuid());
    }

    public Optional<UserInfoDAO> findByUUID(UUID uuid) {
        String sqlQuery = "select u.uuid, u.username, g.\"uuid\" as group_id, g.\"name\" as group_name, r.\"uuid\" as role_id ,r.\"name\" as role_name, p.\"uuid\" as permission_id,p.\"name\" as permission_name\n" +
                "from public.users u\n" +
                "left join public.users_groups ug on ug.user_uuid = u.\"uuid\" \n" +
                "left join public.\"groups\" g on g.\"uuid\" = ug.group_uuid \n" +
                "left join public.groups_roles gr on gr.group_uuid = g.\"uuid\" \n" +
                "left join public.roles r on r.\"uuid\" = gr.role_uuid \n" +
                "left join public.roles_permissions rp on rp.role_uuid = r.\"uuid\" \n" +
                "left join public.permissions p on p.\"uuid\" = rp.permission_uuid \n" +
                "where u.uuid = ?";
        return Optional.ofNullable(jdbcTemplate.query(sqlQuery,new Object[]{uuid}, new UserInfoRowMapper()));
    }
}