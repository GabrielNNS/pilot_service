package com.gns.pilot_service.infra.config;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    //@TODO tentar exportar properties!
    @Bean
    public Logger.Level feignLoggerLevel() {

        return Logger.Level.FULL;
    }


    //@TODO entender melhor para explicar!
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
            @Value("${clients.airdata.username}") String username,
            @Value("${clients.airdata.password}") String password) {

        return new BasicAuthRequestInterceptor(username, password);
    }
}
