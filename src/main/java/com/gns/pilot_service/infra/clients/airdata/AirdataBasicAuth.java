package com.gns.pilot_service.infra.clients.airdata;

import com.gns.pilot_service.infra.config.feign.BasicAuth;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class AirdataBasicAuth implements BasicAuth {

    @Override
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
            @Value("${clients.airdata.username}") String user,
            @Value("${clients.airdata.password}") String password) {


        return new BasicAuthRequestInterceptor(user, password);
    }
}
