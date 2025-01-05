package com.nahda.ot_services.service.interfaces;

import com.nahda.ot_services.dto.LoginRequestDTO;
import com.nahda.ot_services.dto.LoginResponseDTO;
import jakarta.validation.Valid;

public interface IAuthService {

    LoginResponseDTO login(@Valid LoginRequestDTO loginRequestDTO);
}
