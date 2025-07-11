package com.acme.tallerazo.iam.interfaces.rest.transform;

import com.acme.tallerazo.iam.domain.model.aggregates.User;
import com.acme.tallerazo.iam.interfaces.rest.resources.AuthenticatedUserByEmailResource;
import com.acme.tallerazo.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }

}