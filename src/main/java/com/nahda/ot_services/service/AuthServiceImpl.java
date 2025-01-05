package com.nahda.ot_services.service;

import com.nahda.ot_services.dto.LoginRequestDTO;
import com.nahda.ot_services.dto.LoginResponseDTO;
import com.nahda.ot_services.model.UserInfoDetails;
import com.nahda.ot_services.service.interfaces.IAuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        if(!loginRequestDTO.getUsername().isEmpty() && !loginRequestDTO.getPassword().isEmpty()) {
            UserInfoDetails userInfoDetails = userInfoService.loadUserByUsername(loginRequestDTO.getUsername());
            LoginResponseDTO loginResponse = this.modelMapper.map(userInfoDetails, LoginResponseDTO.class);
            loginResponse.setToken(jwtService.generateToken(userInfoDetails.getUsername()));
            return loginResponse;
        }
        return null;

    }
}