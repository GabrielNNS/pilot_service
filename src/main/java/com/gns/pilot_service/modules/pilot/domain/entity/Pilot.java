package com.gns.pilot_service.modules.pilot.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String document;
    private String licenseType;

    @ElementCollection
    @CollectionTable(name = "pilot_aircraft", joinColumns = @JoinColumn(name = "pilot_id"))
    @Column(name = "aircraft_id")
    private List<Long> aircraftId;

    public void addAircraft(Long aircraftId) {
        this.aircraftId.add(aircraftId);
    }
}
