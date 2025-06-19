package com.acme.tallerazo.iam.interfaces.rest.resources;

public record AuthenticatedUserByEmailResource(Long id, String email, String token) {
}
