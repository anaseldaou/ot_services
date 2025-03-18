package com.nahda.ot_services.dao;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserInfoDAO {


    private UUID uuid;

    @NotNull(message = "username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotNull(message = "username is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;

    private List<LookupDAO> roles;
    private List<LookupDAO> groups;
    private List<LookupDAO> permissions;

    public UserInfoDAO(){}
    public UserInfoDAO(UUID uuid, String username) {
        this.username = username;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<LookupDAO> getRoles() {
        return roles;
    }

    public void setRoles(List<LookupDAO> roles) {
        this.roles = roles;
    }

    public List<LookupDAO> getGroups() {
        return groups;
    }

    public void setGroups(List<LookupDAO> groups) {

        this.groups = groups;
    }

    public List<LookupDAO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<LookupDAO> permissions) {
        this.permissions = permissions;
    }
}