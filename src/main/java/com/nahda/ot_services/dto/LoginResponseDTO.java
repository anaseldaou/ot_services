package com.nahda.ot_services.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

public class LoginResponseDTO {

    UUID uuid;
    String username;
    String token;

    List<GrantedAuthority> permissions;

    public List<GrantedAuthority> getGroups() {
        return groups;
    }

    public void setGroups(List<GrantedAuthority> groups) {
        this.groups = groups;
    }

    public List<GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(List<GrantedAuthority> roles) {
        this.roles = roles;
    }

    List<GrantedAuthority> groups;
    List<GrantedAuthority> roles;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<GrantedAuthority> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<GrantedAuthority> permissions) {
        this.permissions = permissions;
    }
}
