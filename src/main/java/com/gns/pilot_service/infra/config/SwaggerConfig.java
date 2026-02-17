package com.gns.pilot_service.infra.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Pilot Service",
                version = "1.0",
                description = "Pilot Service"
        )
)
public class SwaggerConfig {
}
