package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.commands.SeedServicesCommand;

public interface ServiceCommandService {


    void handle (SeedServicesCommand command) ;
}
