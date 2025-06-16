package com.acme.tallerazo.iam.domain.model.commands;

import com.acme.tallerazo.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String firstName, String lastName,String email, String password, List<Role> roles) {
}
