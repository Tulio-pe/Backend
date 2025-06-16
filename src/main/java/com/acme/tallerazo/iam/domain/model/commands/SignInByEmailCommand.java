package com.acme.tallerazo.iam.domain.model.commands;

import com.acme.tallerazo.iam.domain.model.entities.Role;

import java.util.List;

public record SignInByEmailCommand(String email, String password, List<Role>roles) {
}
