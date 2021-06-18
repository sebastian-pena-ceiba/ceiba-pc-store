package com.ceiba.pcstore.controller;

import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.service.GetComponentListService;
import com.ceiba.pcstore.service.GetComponentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentController {

    private GetComponentListService getComponentListService;
    private GetComponentService getComponentService;

    public ComponentController(GetComponentListService getComponentListService, GetComponentService getComponentService) {
        this.getComponentListService = getComponentListService;
        this.getComponentService = getComponentService;
    }

    @GetMapping
    public List<Component> listComponents(@RequestParam(name = "type", required = false) String type) {
        return getComponentListService.execute(type);
    }

    @GetMapping("/{idComponent}")
    public Component getComponent(@PathVariable("idComponent") Long id) {
        return getComponentService.execute(id);
    }

}
