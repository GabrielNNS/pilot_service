package com.gns.pilot_service.infra.config.feign;

import feign.auth.BasicAuthRequestInterceptor;

public interface BasicAuth {

    BasicAuthRequestInterceptor basicAuthRequestInterceptor(String user, String password);
}
