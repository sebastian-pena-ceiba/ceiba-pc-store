package com.ceiba.pcstore.controller;

import com.ceiba.pcstore.command.manager.GetComponentManager;
import com.ceiba.pcstore.command.manager.ListComponentManager;
import com.ceiba.pcstore.model.entity.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentController {

    private ListComponentManager listComponentManager;
    private GetComponentManager getComponentManager;

    public ComponentController(ListComponentManager listComponentManager, GetComponentManager getComponentManager) {
        this.listComponentManager = listComponentManager;
        this.getComponentManager = getComponentManager;
    }

    @GetMapping
    public List<Component> listComponents(@RequestParam(name = "type", required = false) String type) {
        return listComponentManager.ejecutar(type);
    }

    @GetMapping("/{idComponent}")
    public Component getComponent(@PathVariable("idComponent") Long id) {
        return getComponentManager.ejecutar(id);
    }

}
