package com.acme.tallerazo.iam.interfaces.rest.transform;

import com.acme.tallerazo.iam.domain.model.commands.SignUpCommand;
import com.acme.tallerazo.iam.interfaces.rest.resources.SignUpResource;


public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.username(), resource.firstName(),resource.lastName(), resource.email(), resource.password());
    }

}