package com.ceiba.pcstore.command.manager;

import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.service.GetComponentService;

@org.springframework.stereotype.Component
public class GetComponentManager {

    private GetComponentService getComponentService;

    public GetComponentManager(GetComponentService getComponentService) {
        this.getComponentService = getComponentService;
    }

    public Component ejecutar(Long id) {
        return getComponentService.execute(id);
    }
}
