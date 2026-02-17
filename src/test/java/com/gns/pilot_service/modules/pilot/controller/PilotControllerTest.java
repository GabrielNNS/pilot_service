package com.gns.pilot_service.modules.pilot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotRequest;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotUpdateRequest;
import com.gns.pilot_service.modules.pilot.domain.entity.Pilot;
import com.gns.pilot_service.modules.pilot.repository.PilotRepository;
import com.gns.pilot_service.utils.PilotFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("inte")
@Transactional
public class PilotControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final PilotRepository pilotRepository;

    @Autowired
    public PilotControllerTest(MockMvc mockMvc,
                               ObjectMapper objectMapper,
                               PilotRepository pilotRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.pilotRepository = pilotRepository;
    }

    @Test
    public void shouldSavePilotWhenRequestPOSTIsValid() throws Exception {
        PilotRequest request = PilotFactory.buildPilotRequestDTO();
        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/pilots")
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(request.name()));

        Pilot savedPilot = pilotRepository.findAll().getFirst();
        assertEquals(request.name(), savedPilot.getName());
        assertEquals(request.document(), savedPilot.getDocument());
    }

    @Test
    public void shouldReturnPilotWhenIdExists() throws Exception {
        Pilot pilot = pilotRepository.save(PilotFactory.buildDefaultNoId());

        mockMvc.perform(get("/api/v1/pilots/{id}", pilot.getId())
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pilot.getId()))
                .andExpect(jsonPath("$.name").value(pilot.getName()));
    }

    @Test
    public void shouldReturn404WhenPilotDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/v1/pilots/{id}", 999L)
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnAllPilots() throws Exception {
        pilotRepository.save(PilotFactory.buildDefaultNoId());

        mockMvc.perform(get("/api/v1/pilots")
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void shouldUpdatePilotWhenIdExists() throws Exception {
        Pilot pilot = pilotRepository.save(PilotFactory.buildDefaultNoId());

        PilotUpdateRequest updateRequest = PilotUpdateRequest.builder()
                .name("Pilot Updated Name")
                .licenseType("ATPL")
                .build();

        String json = objectMapper.writeValueAsString(updateRequest);

        mockMvc.perform(put("/api/v1/pilots/{id}", pilot.getId())
                        .with(user("admin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pilot Updated Name"));

        Pilot updated = pilotRepository.findById(pilot.getId()).orElseThrow();
        assertEquals("Pilot Updated Name", updated.getName());
        assertEquals("ATPL", updated.getLicenseType());
    }

    @Test
    public void shouldDeletePilotWhenIdExists() throws Exception {
        Pilot pilot = pilotRepository.save(PilotFactory.buildDefaultNoId());

        mockMvc.perform(delete("/api/v1/pilots/{id}", pilot.getId())
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isNoContent());

        assertFalse(pilotRepository.findById(pilot.getId()).isPresent());
    }

    @Test
    public void shouldReturn404WhenDeletingNonExistingPilot() throws Exception {
        mockMvc.perform(delete("/api/v1/pilots/{id}", 999L)
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isNotFound());
    }
}
