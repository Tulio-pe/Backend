package com.acme.tallerazo.iam.interfaces.rest.transform;

import com.acme.tallerazo.iam.domain.model.commands.SignInByEmailCommand;
import com.acme.tallerazo.iam.domain.model.commands.SignInCommand;
import com.acme.tallerazo.iam.interfaces.rest.resources.SignInEmailResource;
import com.acme.tallerazo.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
    public static SignInByEmailCommand toCommandEmailFromResource(SignInEmailResource signinEmailResource){
        return new SignInByEmailCommand(signinEmailResource.email(),signinEmailResource.password());
    }
}