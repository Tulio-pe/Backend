package com.acme.tallerazo.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String username , String firstName, String lastName, String email, String password, List<String> roles) {
}
