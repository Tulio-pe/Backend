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

/**
* Servicio de consultas para el módulo IAM que implementa el patrón CQRS.
* Maneja todas las operaciones de solo lectura relacionadas con usuarios.
*/


}
