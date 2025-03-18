package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dao.LookupDAO;
import com.nahda.ot_services.dto.LookupDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICommonMapper {
    List<LookupDTO> fromListLookupDAOToListLookupDTO(List<LookupDAO> source);
    List<LookupDTO> fromLookupDAOToLookupDTO(List<LookupDAO> source);
}
