package com.gns.pilot_service.modules.pilot.service;

import com.gns.pilot_service.modules.pilot.domain.dto.PilotRequest;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotResponse;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotUpdateRequest;
import com.gns.pilot_service.modules.pilot.domain.entity.Pilot;
import com.gns.pilot_service.modules.pilot.mapper.PilotMapper;
import com.gns.pilot_service.modules.pilot.repository.PilotRepository;
import com.gns.pilot_service.modules.pilot.service.PilotServiceImpl;
import com.gns.pilot_service.utils.PilotFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("unit")
@ExtendWith(MockitoExtension.class)
class PilotServiceTest {

    @Mock
    private PilotRepository repository;

    @Mock
    private PilotMapper mapper;

    @InjectMocks
    private PilotServiceImpl service;

    private Pilot pilot;
    private PilotResponse response;

    @BeforeEach
    void setUp() {
        pilot = PilotFactory.buildDefault();
        response = PilotFactory.buildPilotResponseDTO();
    }

    @Test
    void shouldSavePilotWhenValidRequest() {
        PilotRequest request = PilotFactory.buildPilotRequestDTO();

        when(mapper.toEntity(any(PilotRequest.class))).thenReturn(pilot);
        when(repository.save(any(Pilot.class))).thenReturn(pilot);
        when(mapper.toResponse(any(Pilot.class))).thenReturn(response);

        PilotResponse result = service.create(request);

        verify(repository).save(pilot);
        assertNotNull(result);
        assertEquals(request.name(), result.name());
        assertEquals(request.document(), result.document());
        assertEquals(request.licenseType(), result.licenseType());
    }

    @Test
    void shouldReturnPilotWhenValidId() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(pilot));
        when(mapper.toResponse(any(Pilot.class))).thenReturn(response);

        PilotResponse result = service.getById(pilot.getId());

        verify(repository).findById(pilot.getId());
        assertNotNull(result);
        assertEquals(pilot.getId(), result.id());
        assertEquals(pilot.getName(), result.name());
        assertEquals(pilot.getDocument(), result.document());
        assertEquals(pilot.getLicenseType(), result.licenseType());
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenInvalidId() {
        Long invalidId = 99L;
        when(repository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getById(invalidId));
    }

    @Test
    void shouldReturnListOfPilots() {
        List<Pilot> pilots = new ArrayList<>(List.of(pilot));
        List<PilotResponse> responses = new ArrayList<>(List.of(response));

        when(repository.findAll()).thenReturn(pilots);
        when(mapper.toResponse(pilots)).thenReturn(responses);

        List<PilotResponse> result = service.getAll();

        verify(repository).findAll();
        assertNotNull(result);
        assertEquals(responses.size(), result.size());
    }

    @Test
    void shouldUpdatePilotWhenValidRequest() {
        PilotUpdateRequest updateRequest = PilotUpdateRequest.builder()
                .name("UPDATED NAME")
                .build();

        PilotResponse updatedResponse = PilotResponse.builder()
                .id(pilot.getId())
                .name("UPDATED NAME")
                .document(pilot.getDocument())
                .licenseType(pilot.getLicenseType())
                .aircraftId(pilot.getAircraftId())
                .build();

        when(repository.findById(anyLong())).thenReturn(Optional.of(pilot));
        when(repository.save(any(Pilot.class))).thenReturn(pilot);
        when(mapper.toResponse(any(Pilot.class))).thenReturn(updatedResponse);

        PilotResponse result = service.update(pilot.getId(), updateRequest);

        verify(repository).findById(pilot.getId());
        verify(repository).save(pilot);
        assertNotNull(result);
        assertEquals(updateRequest.name(), result.name());
    }

    @Test
    void shouldDeletePilotWhenValidId() {
        when(repository.findById(pilot.getId())).thenReturn(Optional.of(pilot));

        service.delete(pilot.getId());

        verify(repository).findById(pilot.getId());
        verify(repository).delete(pilot);
    }
}