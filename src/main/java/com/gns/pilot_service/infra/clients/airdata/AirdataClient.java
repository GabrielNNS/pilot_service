package com.gns.pilot_service.infra.clients.airdata;

import com.gns.pilot_service.infra.clients.airdata.dto.AircraftDTO;
import com.gns.pilot_service.infra.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@TODO url exposta!!!!!!!!!!!!!!!!!!
@FeignClient(name = "airdata-client",
        url = "http://localhost:8080/api/v1",
        configuration = FeignConfig.class
)
public interface AirdataClient {

    @GetMapping("/aircrafts/{id}")
    AircraftDTO getAircraftClientById(@PathVariable Long id);
}
