package com.gns.pilot_service.infra.clients.airdata;

import com.gns.pilot_service.infra.clients.airdata.dto.AircraftClientResponse;
import com.gns.pilot_service.infra.config.feign.BasicAuth;
import com.gns.pilot_service.infra.config.feign.FullLogger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "airdata-client",
        url = "${clients.airdata.url}",
        configuration = {FullLogger.class, BasicAuth.class}
)
public interface AirdataClient {

    @GetMapping("/aircrafts/{id}")
    AircraftClientResponse getAircraftClientById(@PathVariable Long id);
}
