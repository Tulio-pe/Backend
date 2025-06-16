package com.acme.tallerazo.iam.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.iam.domain.model.entities.Role;
import com.acme.tallerazo.iam.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName (Roles name);
    boolean existsByName (Roles name);
}
