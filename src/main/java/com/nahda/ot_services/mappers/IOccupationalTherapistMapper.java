package com.nahda.ot_services.mappers;

import com.nahda.ot_services.dto.OccupationalTherapistRequestDTO;
import com.nahda.ot_services.dto.OccupationalTherapistResponseDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IOccupationalTherapistMapper {

    OccupationalTherapistResponseDTO fromOccupationalTherapistRequestDTOToOccupationalTherapistResponseDTO(OccupationalTherapistRequestDTO source);
    OccupationalTherapistRequestDTO fromOccupationalTherapistResponseDTOToOccupationalTherapistRequestDTO(OccupationalTherapistResponseDTO source);
}
