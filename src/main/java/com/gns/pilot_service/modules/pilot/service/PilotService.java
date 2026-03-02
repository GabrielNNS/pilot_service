package com.gns.pilot_service.modules.pilot.service;

import com.gns.pilot_service.modules.pilot.domain.dto.PilotBindAircraft;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotRequest;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotResponse;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotUpdateRequest;

import java.util.List;

public interface PilotService {

    PilotResponse create(PilotRequest request);
    PilotResponse getById(Long id);
    List<PilotResponse> getAll();
    PilotResponse update(Long id, PilotUpdateRequest request);
    void delete(Long id);
    PilotResponse bindAircraft(Long id, PilotBindAircraft aircraftId);
}
