package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dao.RepeatedClassDAO;
import com.nahda.ot_services.dto.RepeatedClassDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRepeatedClasseMapper {
    RepeatedClassDAO fromRepeatedClassDTOtoRepeatedClassDAO(RepeatedClassDTO repeatedClassDTO);
    List<RepeatedClassDAO> fromListRepeatedClassDTOtoListRepeatedClassDAO(List<RepeatedClassDTO> repeatedClassDTO);
    RepeatedClassDTO fromRepeatedClassDAOtoRepeatedClassDTO(RepeatedClassDAO repeatedClassDAO);
    List<RepeatedClassDTO> fromListRepeatedClassDAOtoListRepeatedClassDTO(List<RepeatedClassDAO> repeatedClassDAO);
}
