package com.gns.pilot_service.modules.pilot.domain.dto;

import jakarta.validation.constraints.NotNull;

public record PilotBindAircraft(
        @NotNull
        Long id
) {
}
