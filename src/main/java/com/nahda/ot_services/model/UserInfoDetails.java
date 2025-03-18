package com.nahda.ot_services.model;
import com.nahda.ot_services.dao.UserInfoDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails {

    private String username; // Changed from 'name' to 'username' for clarity
    private String password;


    List<GrantedAuthority> permissions;
    List<GrantedAuthority> roles;
    List<GrantedAuthority> groups;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private UUID uuid;

    private List<GrantedAuthority> authorities;


    public UserInfoDetails(UserInfoDAO userInfoDAO) {
        this.username = userInfoDAO.getUsername(); // Assuming 'name' is used as 'username'
        this.password = userInfoDAO.getPassword();
        this.uuid = userInfoDAO.getUuid();
        this.permissions = userInfoDAO.getPermissions()
                .stream()
                .map( data -> new SimpleGrantedAuthority(data.getName()))
                .collect(Collectors.toList());
        this.roles = userInfoDAO.getRoles()
                .stream()
                .map( data -> new SimpleGrantedAuthority(data.getName()))
                .collect(Collectors.toList());
        this.groups = userInfoDAO.getGroups()
                .stream()
                .map( data -> new SimpleGrantedAuthority(data.getUuid().toString()))
                .collect(Collectors.toList());
        this.authorities = new ArrayList<>();
        this.authorities.addAll(this.roles);
        this.authorities.addAll(this.groups);
        this.authorities.addAll(this.permissions);
        this.authorities = userInfoDAO.getRoles()
                .stream()
                .map( data -> new SimpleGrantedAuthority(data.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement your logic if you need this
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement your logic if you need this
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement your logic if you need this
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement your logic if you need this
    }

    public List<GrantedAuthority> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<GrantedAuthority> permissions) {
        this.permissions = permissions;
    }

    public List<GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(List<GrantedAuthority> roles) {
        this.roles = roles;
    }

    public List<GrantedAuthority> getGroups() {
        return groups;
    }

    public void setGroups(List<GrantedAuthority> groups) {
        this.groups = groups;
    }
}
