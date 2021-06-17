package com.ceiba.pcstore.command.manager;

import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.service.GetComponentListService;

import java.util.List;

@org.springframework.stereotype.Component
public class ListComponentManager {

    private GetComponentListService getComponentListService;

    public ListComponentManager(GetComponentListService getComponentListService) {
        this.getComponentListService = getComponentListService;
    }

    public List<Component> ejecutar(String type) {
        return getComponentListService.execute(type);
    }

}
