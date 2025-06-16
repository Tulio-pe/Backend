package com.acme.tallerazo.iam.application.interal.queryservices;

import com.acme.tallerazo.iam.domain.model.aggregates.User;
import com.acme.tallerazo.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.tallerazo.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.tallerazo.iam.domain.model.queries.GetUserByUsernameQuery;
import com.acme.tallerazo.iam.domain.services.UserQueryService;
import com.acme.tallerazo.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UserQueryServiceImpl implements UserQueryService {
private final UserRepository userRepository;
public UserQueryServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
}

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
         return userRepository.findById(query.id());
    }


    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
