package com.gns.pilot_service.infra.config.feign;

import org.springframework.context.annotation.Bean;

public class FullLogger {

    @Bean
    public feign.Logger.Level feignLoggerLevel() {

        return feign.Logger.Level.FULL;
    }
}
