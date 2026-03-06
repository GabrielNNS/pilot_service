package com.gns.pilot_service.infra.clients.airdata.dto;

public record AircraftClientResponse(
        Long id,
        String model,
        OperatorClientResponse operatorClientResponse
) {
}
