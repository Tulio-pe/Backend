package com.acme.tallerazo.iam.interfaces.rest.transform;

import com.acme.tallerazo.iam.domain.model.aggregates.User;
import com.acme.tallerazo.iam.interfaces.rest.resources.AuthenticatedUserByEmailResource;

public class AuthenticateUserByEmailResourceFromEntityAssembler {
    public static AuthenticatedUserByEmailResource toResourceFromEntity(User user, String token){
        return new AuthenticatedUserByEmailResource(user.getId(),user.getEmailAddress().value(),token);
    }
}
