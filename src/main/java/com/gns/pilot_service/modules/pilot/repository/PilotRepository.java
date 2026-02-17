package com.gns.pilot_service.modules.pilot.repository;

import com.gns.pilot_service.modules.pilot.domain.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot, Long> {
}
