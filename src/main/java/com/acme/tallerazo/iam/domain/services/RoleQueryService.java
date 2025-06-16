package com.acme.tallerazo.iam.domain.services;

import com.acme.tallerazo.iam.domain.model.entities.Role;
import com.acme.tallerazo.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.Optional;

public interface RoleQueryService {
    Optional<Role> handle(GetRoleByNameQuery query);
}
