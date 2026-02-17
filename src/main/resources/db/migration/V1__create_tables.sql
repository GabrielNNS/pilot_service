CREATE TABLE pilot (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    document VARCHAR(50) NOT NULL UNIQUE,
    license_type VARCHAR(100) NOT NULL
);

CREATE TABLE pilot_aircraft (
    pilot_id BIGINT NOT NULL,
    aircraft_id BIGINT NOT NULL,
    PRIMARY KEY (pilot_id, aircraft_id),
    CONSTRAINT fk_pilot FOREIGN KEY (pilot_id) REFERENCES pilot (id) ON DELETE CASCADE
);