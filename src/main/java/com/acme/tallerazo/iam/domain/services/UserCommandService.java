package com.acme.tallerazo.iam.domain.services;

import com.acme.tallerazo.iam.domain.model.aggregates.User;
import com.acme.tallerazo.iam.domain.model.commands.SignInByEmailCommand;
import com.acme.tallerazo.iam.domain.model.commands.SignInCommand;
import com.acme.tallerazo.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
* Interfaz del servicio de comandos para operaciones de autenticación y registro de usuarios.
* Define el contrato para todas las operaciones de escritura en el módulo IAM siguiendo CQRS.
*/

public interface UserCommandService {
  Optional<ImmutablePair<User,String>> handle(SignInCommand command);
Optional<User>handle (SignUpCommand command);
Optional <ImmutablePair<User,String>>handle(SignInByEmailCommand command);
}
