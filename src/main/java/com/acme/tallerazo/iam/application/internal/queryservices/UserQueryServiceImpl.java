package com.acme.tallerazo.iam.application.internal.queryservices;

import com.acme.tallerazo.iam.domain.model.aggregates.User;
import com.acme.tallerazo.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.tallerazo.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.tallerazo.iam.domain.model.queries.GetUserByUsernameQuery;
import com.acme.tallerazo.iam.domain.services.UserQueryService;
import com.acme.tallerazo.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Delegates read-only user queries to {@link UserRepository}.
 * <p>
 *  Exposes three query handlers:
 * <ul>
 *   <li>{@link #handle(GetAllUsersQuery)} – obtiene la lista completa de usuarios.</li>
 *   <li>{@link #handle(GetUserByIdQuery)} – busca un usuario por su ID.</li>
 *   <li>{@link #handle(GetUserByUsernameQuery)} – busca un usuario por <em>username</em>.</li>
 * </ul>
 * <p>
 *  Cada método devuelve el resultado tal cual lo provee {@code userRepository}.
 */

@Service
public class UserQueryServiceImpl implements UserQueryService {
private final UserRepository userRepository;
public UserQueryServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
}
 // Obtiene la lista completa de todos los usuarios del sistema
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }
   // Busca un usuario específico por su ID único
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
         return userRepository.findById(query.id());
    }

   // Busca un usuario por su nombre de usuario (username)
    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
