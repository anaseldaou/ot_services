package com.nahda.ot_services.dto;

import jakarta.validation.constraints.NotNull;

public class LoginRequestDTO {

    @NotNull
    String username;

    @NotNull
    String password;

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public @NotNull String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }
}
