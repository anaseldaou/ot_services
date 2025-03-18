package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dao.LookupDAO;
import com.nahda.ot_services.dao.NoteLookupDAO;
import com.nahda.ot_services.dto.LookupDTO;
import com.nahda.ot_services.dto.NoteLookupDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ILookupMapper {
    List<LookupDTO> fromListLookupDAOToListLookupDTO(List<LookupDAO> source);
    List<LookupDAO> fromListLookupDTOToListLookupDAO(List<LookupDTO> source);
    List<NoteLookupDTO> fromListNoteLookupDAOToListNoteLookupDTO(List<NoteLookupDAO> source);
    List<NoteLookupDAO> fromListNoteLookupDTOToListNoteLookupDAO(List<NoteLookupDTO> source);
}
