package com.acme.tallerazo.iam.domain.services;

import com.acme.tallerazo.iam.domain.model.aggregates.User;
import com.acme.tallerazo.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.tallerazo.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.tallerazo.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    /**
     * Handles the {@link GetAllUsersQuery} to retrieve all users.
     *
     * @param query the {@link GetAllUsersQuery} query
     * @return a list of {@link User} entities
     */
    List<User> handle (GetAllUsersQuery query);
    /**
     * Handles the {@link GetUserByIdQuery} to retrieve a user by their ID.
     *
     * @param query the {@link GetUserByIdQuery} query
     * @return an {@link Optional} containing the {@link User} if found, otherwise empty
     */
    Optional<User>handle (GetUserByIdQuery query);



    /**
     * Handles the {@link GetUserByUsernameQuery} to retrieve a user by their username.
     *
     * @param query the {@link GetUserByUsernameQuery} query
     * @return an {@link Optional} containing the {@link User} if found, otherwise empty
     */
    Optional<User>handle (GetUserByUsernameQuery query);

}
