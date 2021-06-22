package com.ceiba.pcstore.service.testdatabuilder;

import com.ceiba.pcstore.command.ComponentCommand;
import com.ceiba.pcstore.model.entity.Component;

import java.math.BigDecimal;

public class ComponentCommandTestDataBuilder {

    private Long id;
    private String type;
    private String name;
    private BigDecimal price;

    public ComponentCommandTestDataBuilder() {
        this.id = 1L;
        this.type = Component.TYPE_BOARD;
        this.name = "B550 Tomahawk";
        this.price = BigDecimal.valueOf(137.5);
    }

    public ComponentCommandTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ComponentCommandTestDataBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ComponentCommand build() {
        return new ComponentCommand(id, type, name, price);
    }

}
