package com.acme.tallerazo.iam.interfaces.rest.transform;

import com.acme.tallerazo.iam.domain.model.aggregates.User;
import com.acme.tallerazo.iam.domain.model.entities.Role;
import com.acme.tallerazo.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}