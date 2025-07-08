package com.acme.tallerazo.workShopManagment.infrastructure.external.api;

import com.acme.tallerazo.workShopManagment.infrastructure.external.api.resources.ExternalGeoResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class ExternalGeoApiClient {

    private final RestTemplate restTemplate;

    public ExternalGeoApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ExternalGeoResource> fetchDistricts() {
        ResponseEntity<ExternalGeoResource[]> response = restTemplate.getForEntity(
                "/", ExternalGeoResource[].class
        );

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
