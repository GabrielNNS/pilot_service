package com.gns.pilot_service.modules.pilot.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PilotRequest(
        @NotBlank
        String name,
        @NotBlank
        String document,
        @NotBlank
        String licenseType
) {
}
