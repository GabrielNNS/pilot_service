package com.gns.pilot_service.utils;

import com.gns.pilot_service.modules.pilot.domain.dto.PilotRequest;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotResponse;
import com.gns.pilot_service.modules.pilot.domain.entity.Pilot;

import java.util.ArrayList;
import java.util.List;

public class PilotFactory {

    private static final Long DEFAULT_ID = 1L;
    private static final String NAME = "John Doe";
    private static final String DOCUMENT = "123.456.789-00";
    private static final String LICENSE_TYPE = "COMMERCIAL_PILOT";
    private static final List<Long> AIRCRAFT_IDS = new ArrayList<>(List.of(1L, 2L));

    public static Pilot buildDefault() {
        return Pilot.builder()
                .id(DEFAULT_ID)
                .name(NAME)
                .document(DOCUMENT)
                .licenseType(LICENSE_TYPE)
                .aircraftId(AIRCRAFT_IDS)
                .build();
    }

    public static Pilot buildDefaultNoId() {
        return Pilot.builder()
                .name(NAME)
                .document(DOCUMENT)
                .licenseType(LICENSE_TYPE)
                .aircraftId(AIRCRAFT_IDS)
                .build();
    }

    public static PilotRequest buildPilotRequestDTO() {
        return PilotRequest.builder()
                .name(NAME)
                .document(DOCUMENT)
                .licenseType(LICENSE_TYPE)
                .build();
    }

    public static PilotResponse buildPilotResponseDTO() {
        return PilotResponse.builder()
                .id(DEFAULT_ID)
                .name(NAME)
                .document(DOCUMENT)
                .licenseType(LICENSE_TYPE)
                .aircraftId(AIRCRAFT_IDS)
                .build();
    }
}
