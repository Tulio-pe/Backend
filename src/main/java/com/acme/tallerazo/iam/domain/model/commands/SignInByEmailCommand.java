package com.acme.tallerazo.iam.domain.model.commands;



public record SignInByEmailCommand(String email, String password) {
}
