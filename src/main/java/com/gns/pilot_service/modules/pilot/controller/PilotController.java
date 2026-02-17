package com.gns.pilot_service.modules.pilot.controller;

import com.gns.pilot_service.modules.pilot.domain.dto.PilotRequest;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotResponse;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotUpdateRequest;
import com.gns.pilot_service.modules.pilot.service.PilotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pilots")
public class PilotController {

    private final PilotService service;

    @PostMapping
    public ResponseEntity<PilotResponse> create(@RequestBody PilotRequest request) {
        PilotResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PilotResponse> getById(@PathVariable Long id) {
        PilotResponse response = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PilotResponse>> getAllPilots() {
        List<PilotResponse> pilotResponseList = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(pilotResponseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PilotResponse> updateById(@PathVariable Long id,
                                                    @RequestBody PilotUpdateRequest updateRequest) {
        PilotResponse response = service.update(id, updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}