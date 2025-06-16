package com.acme.tallerazo.iam.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.iam.domain.model.aggregates.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findByUsername(String username);
boolean existsByUsername (String username);


}
