package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dao.PhysicalAccidentDAO;
import com.nahda.ot_services.dto.PhysicalAccidentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPhysicalAccidentMapper {
    PhysicalAccidentDAO fromPhysicalAccidentDTOtoPhysicalAccidentDAO(PhysicalAccidentDTO physicalAccidentDTO);
    PhysicalAccidentDTO fromPhysicalAccidentDAOtoPhysicalAccidentDTO(PhysicalAccidentDAO physicalAccidentDTO);
    List<PhysicalAccidentDAO> fromListPhysicalAccidentDTOtoListPhysicalAccidentDAO(List<PhysicalAccidentDTO> physicalAccidentDTO);
    List<PhysicalAccidentDTO> fromListPhysicalAccidentDAOtoListPhysicalAccidentDTO(List<PhysicalAccidentDAO> physicalAccidentDTO);
}
