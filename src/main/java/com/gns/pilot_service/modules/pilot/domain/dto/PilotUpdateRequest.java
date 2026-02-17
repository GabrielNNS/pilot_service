package com.gns.pilot_service.modules.pilot.domain.dto;

import lombok.Builder;

@Builder
public record PilotUpdateRequest(
        Long id,
        String name,
        String document,
        String licenseType
) {
}
