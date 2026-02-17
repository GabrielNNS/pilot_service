package com.gns.pilot_service.modules.pilot.mapper;

import com.gns.pilot_service.modules.pilot.domain.dto.PilotRequest;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotResponse;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotUpdateRequest;
import com.gns.pilot_service.modules.pilot.domain.entity.Pilot;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PilotMapper {

    @Mapping(target = "id", ignore = true)
    Pilot toEntity(PilotRequest pilotRequest);

    PilotResponse toResponse(Pilot pilot);
    List<PilotResponse> toResponse(List<Pilot> pilots);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateFromDto(PilotUpdateRequest pilotUpdateRequest, @MappingTarget Pilot pilot);
}
