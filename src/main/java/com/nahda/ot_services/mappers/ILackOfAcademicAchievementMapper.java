package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dao.LackOfAcademicAchievementDAO;
import com.nahda.ot_services.dto.LackOfAcademicAchievementDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ILackOfAcademicAchievementMapper {
    LackOfAcademicAchievementDTO fromLackOfAcademicAchievementDAOtoLackOfAcademicAchievementDTO(LackOfAcademicAchievementDAO lackOfAcademicAchievementDAO);
    LackOfAcademicAchievementDAO fromLackOfAcademicAchievementDTOtoLackOfAcademicAchievementDAO(LackOfAcademicAchievementDTO lackOfAcademicAchievementDTO);
    List<LackOfAcademicAchievementDTO> fromListLackOfAcademicAchievementDAOtoListLackOfAcademicAchievementDTO(List<LackOfAcademicAchievementDAO> lackOfAcademicAchievementDAO);
    List<LackOfAcademicAchievementDAO> fromListLackOfAcademicAchievementDTOtoListLackOfAcademicAchievementDAO(List<LackOfAcademicAchievementDTO> lackOfAcademicAchievementDTO);

}
