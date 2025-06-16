package com.acme.tallerazo.iam.domain.services;

import com.acme.tallerazo.iam.domain.model.commands.SeedRolesCommand;

;

public interface RoleCommandService {
 void handle(SeedRolesCommand seedRolesCommand);
}
