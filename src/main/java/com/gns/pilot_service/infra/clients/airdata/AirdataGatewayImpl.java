package com.gns.pilot_service.infra.clients.airdata;

import com.gns.pilot_service.infra.clients.airdata.dto.AircraftDTO;
import com.gns.pilot_service.modules.pilot.gateway.AirdataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AirdataGatewayImpl implements AirdataGateway {

    private final AirdataClient client;

    @Override
    public AircraftDTO getAircraftClientById(Long id) {
        return client.getAircraftClientById(id);
    }
}
