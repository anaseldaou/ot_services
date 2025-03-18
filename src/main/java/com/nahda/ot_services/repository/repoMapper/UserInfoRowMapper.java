package com.nahda.ot_services.repository.repoMapper;

import com.nahda.ot_services.dao.LookupDAO;
import com.nahda.ot_services.dao.UserInfoDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserInfoRowMapper  implements ResultSetExtractor<UserInfoDAO> {

    @Override
    public UserInfoDAO extractData(ResultSet rs) throws SQLException, DataAccessException {
        UserInfoDAO userInfoDAO = null;

        Map<UUID,LookupDAO> groupsMap = new HashMap<>();
        Map<UUID,LookupDAO> rolesMap = new HashMap<>();
        Map<UUID,LookupDAO> permissionsMap = new HashMap<>();
        while (rs.next()) {
            if (userInfoDAO == null) {
                // Initialize the UserInfo object
                userInfoDAO = new UserInfoDAO();
                userInfoDAO.setUuid(UUID.fromString(rs.getString("uuid")));
                userInfoDAO.setUsername(rs.getString("username"));
            }
            // Aggregate groups
            UUID groupId = UUID.fromString(rs.getString("group_id"));
            if (groupId != null && !groupsMap.containsKey(groupId)) {
                LookupDAO group = new LookupDAO();
                group.setUuid(groupId);
                group.setName(rs.getString("group_name"));
                groupsMap.put(groupId, group);
            }

            // Aggregate roles
            UUID roleId = UUID.fromString(rs.getString("role_id"));
            if (roleId != null && !rolesMap.containsKey(roleId)) {
                LookupDAO role = new LookupDAO();
                role.setUuid(roleId);
                role.setName(rs.getString("role_name"));
                rolesMap.put(roleId, role);
            }

            // Aggregate permissions
            UUID permissionId = UUID.fromString(rs.getString("permission_id"));
            if (permissionId != null && !permissionsMap.containsKey(permissionId)) {
                LookupDAO permission = new LookupDAO();
                permission.setUuid(permissionId);
                permission.setName(rs.getString("permission_name"));
                permissionsMap.put(permissionId, permission);
            }
        }

        if (userInfoDAO != null) {
//             Set the aggregated lists to the UserInfo object
            userInfoDAO.setGroups(new ArrayList<>(groupsMap.values()));
            userInfoDAO.setRoles(new ArrayList<>(rolesMap.values()));
            userInfoDAO.setPermissions(new ArrayList<>(permissionsMap.values()));
        }

        return userInfoDAO;
    }
}