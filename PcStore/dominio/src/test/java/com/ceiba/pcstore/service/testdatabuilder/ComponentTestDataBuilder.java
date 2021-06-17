package com.ceiba.pcstore.service.testdatabuilder;

import com.ceiba.pcstore.model.entity.Component;

import java.math.BigDecimal;

public class ComponentTestDataBuilder {

    private Long id;
    private String type;
    private String name;
    private BigDecimal price;

    public ComponentTestDataBuilder() {
        this.id = 1L;
        this.type = Component.TYPE_BOARD;
        this.name = "B550 Tomahawk";
        this.price = BigDecimal.valueOf(160.5);
    }

    public ComponentTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ComponentTestDataBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ComponentTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ComponentTestDataBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Component build() {
        return new Component(this.id, this.type, this.name, this.price);
    }

}
