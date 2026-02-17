package com.gns.pilot_service.infra.handlers;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(Integer errorCode,
                            String errorMessage,
                            LocalDateTime timestamp) {
}
