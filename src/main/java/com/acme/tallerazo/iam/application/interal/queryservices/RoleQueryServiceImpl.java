package com.acme.tallerazo.iam.application.interal.queryservices;

import com.acme.tallerazo.iam.domain.model.entities.Role;
import com.acme.tallerazo.iam.domain.model.queries.GetRoleByNameQuery;
import com.acme.tallerazo.iam.domain.services.RoleQueryService;
import com.acme.tallerazo.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;
    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }

}
