package com.gns.pilot_service.modules.pilot.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PilotResponse(
        Long id,
        String name,
        String document,
        String licenseType,
        List<Long> aircraftId
) {
}
