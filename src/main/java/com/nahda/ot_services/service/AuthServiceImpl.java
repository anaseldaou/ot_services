package com.nahda.ot_services.service;

import com.nahda.ot_services.dto.LoginRequestDTO;
import com.nahda.ot_services.dto.LoginResponseDTO;
import com.nahda.ot_services.mappers.IAuthMapper;
import com.nahda.ot_services.model.UserInfoDetails;
import com.nahda.ot_services.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component("authService")
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IAuthMapper authMapper;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        if(!loginRequestDTO.getUsername().isEmpty() && !loginRequestDTO.getPassword().isEmpty()) {
            UserInfoDetails userInfoDetails = userInfoService.loadUserByUsername(loginRequestDTO.getUsername());
            LoginResponseDTO loginResponse = this.authMapper.fromUserInfoDetailsToLoginResponseDTO(userInfoDetails);
            Map<String, Object> claims = new HashMap<>();
            claims.put("GROUP",userInfoDetails.getGroups());
            claims.put("ROLES",userInfoDetails.getRoles());
            claims.put("PERMISSIONS",userInfoDetails.getPermissions());
            loginResponse.setToken(jwtService.generateToken(userInfoDetails.getUsername(),userInfoDetails.getUuid(),claims));
            return loginResponse;
        }
        return null;
    }

    private String getTokenFromHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring(7);
    }
    public UUID getUserUuid(){
        return jwtService.extractUserUuid(getTokenFromHeader());
    }

    public UUID getGroupUUID(){
        return UUID.fromString(jwtService.extractGroupUUID(getTokenFromHeader()).get(0).getAuthority());
    }

    @Override
    public boolean hasPermission(String name) {
        List<GrantedAuthority> permissions = jwtService.extractPermissions(getTokenFromHeader()).stream().toList();
        for(GrantedAuthority permission: permissions){
            if((permission.getAuthority()).equals(name)){
                return true;
            }
        }
        return false;
    }
}