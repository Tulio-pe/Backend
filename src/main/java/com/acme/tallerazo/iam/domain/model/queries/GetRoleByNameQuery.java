package com.acme.tallerazo.iam.domain.model.queries;

import com.acme.tallerazo.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
