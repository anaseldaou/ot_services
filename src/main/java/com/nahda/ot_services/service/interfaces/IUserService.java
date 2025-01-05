package com.nahda.ot_services.service.interfaces;

import com.nahda.ot_services.dto.UserResponseDTO;
import com.nahda.ot_services.dto.UserRequestDTO;

public interface IUserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
}
