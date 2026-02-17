package com.gns.pilot_service.modules.pilot.service;

import com.gns.pilot_service.modules.pilot.domain.dto.PilotRequest;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotResponse;
import com.gns.pilot_service.modules.pilot.domain.dto.PilotUpdateRequest;
import com.gns.pilot_service.modules.pilot.domain.entity.Pilot;
import com.gns.pilot_service.modules.pilot.mapper.PilotMapper;
import com.gns.pilot_service.modules.pilot.repository.PilotRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PilotServiceImpl implements PilotService {

    private final PilotRepository repository;
    private final PilotMapper mapper;

    @Override
    @Transactional
    public PilotResponse create(PilotRequest request) {
        Pilot pilot = mapper.toEntity(request);
        return mapper.toResponse(repository.save(pilot));
    }

    @Override
    public PilotResponse getById(Long id) {
        Pilot pilot = getByIdOrThrow(id);
        return mapper.toResponse(pilot);
    }

    @Override
    public List<PilotResponse> getAll() {
        return mapper.toResponse(repository.findAll());
    }

    @Override
    @Transactional
    public PilotResponse update(Long id, PilotUpdateRequest request) {
        Pilot pilotToUpdate = getByIdOrThrow(id);

        mapper.updateFromDto(request, pilotToUpdate);

        repository.save(pilotToUpdate);
        return mapper.toResponse(pilotToUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Pilot pilotToDelete = getByIdOrThrow(id);
        repository.delete(pilotToDelete);
    }

    private Pilot getByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pilot with ID " + id + " not found."));
    }
}
