package com.acme.tallerazo.workShopManagment.infrastructure.external.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class  ExternalGeoApiConfigClient{

    @Value("${external.api.districts-url}")
    private String districtsApiUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri(districtsApiUrl)
                .build();
    }
}
