package com.ceiba.pcstore.command.factory;

import com.ceiba.pcstore.command.ComponentCommand;
import com.ceiba.pcstore.model.entity.Component;

@org.springframework.stereotype.Component
public class ComponentFactory {

    public Component create(ComponentCommand componentCommand) {
        return new Component(
                componentCommand.getId(),
                componentCommand.getType(),
                componentCommand.getName(),
                componentCommand.getPrice()
        );
    }

}
