package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dto.LoginResponseDTO;
import com.nahda.ot_services.model.UserInfoDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuthMapper {

    LoginResponseDTO fromUserInfoDetailsToLoginResponseDTO(UserInfoDetails userInfoDetails);
}
