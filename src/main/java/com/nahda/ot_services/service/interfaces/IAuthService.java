package com.nahda.ot_services.service.interfaces;

import com.nahda.ot_services.dto.LoginRequestDTO;
import com.nahda.ot_services.dto.LoginResponseDTO;
import jakarta.validation.Valid;

import java.util.UUID;

public interface IAuthService {

    LoginResponseDTO login(@Valid LoginRequestDTO loginRequestDTO);
    public UUID getUserUuid();
    public UUID getGroupUUID();
    public boolean hasPermission(String permission);
}
