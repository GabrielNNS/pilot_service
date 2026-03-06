package com.gns.pilot_service.modules.pilot.gateway;

import com.gns.pilot_service.infra.clients.airdata.dto.AircraftClientResponse;

public interface AirdataGateway {

    AircraftClientResponse getAircraftClientById(Long id);
}
