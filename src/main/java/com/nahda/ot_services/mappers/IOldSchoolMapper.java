package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dao.OldSchoolDAO;
import com.nahda.ot_services.dto.OldSchoolDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOldSchoolMapper {
//    OldSchoolDTO fromOldSchoolDAOtoOldSchoolDTO(OldSchoolDAO oldSchoolDAO);
    List<OldSchoolDAO> fromOldSchoolDTOListtoOldSchoolDAOList(List<OldSchoolDTO> oldSchoolDTOS);
//    OldSchoolDAO fromOldSchoolDTOtoOldSchoolDAO(OldSchoolDTO oldSchoolDTO);
    List<OldSchoolDTO> fromOldSchoolDAOListtoOldSchoolDTOList(List<OldSchoolDAO> oldSchoolDAOS);
}
