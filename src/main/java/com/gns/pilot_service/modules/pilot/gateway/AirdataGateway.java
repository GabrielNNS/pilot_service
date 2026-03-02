package com.gns.pilot_service.modules.pilot.gateway;

import com.gns.pilot_service.infra.clients.airdata.dto.AircraftDTO;

public interface AirdataGateway {

    AircraftDTO getAircraftClientById(Long id);
}
