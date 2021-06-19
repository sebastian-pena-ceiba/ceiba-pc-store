package com.ceiba.pcstore.testdatabuilder;

import com.ceiba.pcstore.model.entity.BuyerData;
import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.model.entity.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderTestDataBuilder {

    private Long id;
    private Boolean buildService;
    private BuyerData buyerData;
    private List<Component> orderComponents;

    public OrderTestDataBuilder() {

        this.id = 1L;
        this.buildService = false;
        this.buyerData = new BuyerData(
                1L,
                "Fulanito",
                1098765432,
                "example@example.com",
                "3334445566",
                "Av 1 dg 2"
        );

        Component exampleComponent = new ComponentTestDataBuilder().build();

        this.orderComponents = Collections.singletonList(exampleComponent);
    }

    public OrderTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public OrderTestDataBuilder withBuildService(Boolean buildService) {
        this.buildService = buildService;
        return this;
    }

    public OrderTestDataBuilder withTrackingCode(String trackingCode) {
        return this;
    }

    public OrderTestDataBuilder withBuyerData(BuyerData buyerData) {
        this.buyerData = buyerData;
        return this;
    }

    public OrderTestDataBuilder withOrderComponents(List<Component> orderComponents) {

        if (orderComponents == null || orderComponents.isEmpty()) {
            this.orderComponents = orderComponents;
        } else {
            this.orderComponents = new ArrayList<>();
            this.orderComponents.addAll(orderComponents);
        }

        return this;
    }

    public OrderTestDataBuilder withAllDefaultComponents() {

        // same object with different id and type (total price = 160.5*7 = 1123.5)
        Component exampleComponent1 = new ComponentTestDataBuilder().build(); // board
        Component exampleComponent2 = new ComponentTestDataBuilder()
                .withId(2L)
                .withType(Component.TYPE_CPU)
                .build();
        Component exampleComponent3 = new ComponentTestDataBuilder()
                .withId(3L)
                .withType(Component.TYPE_RAM)
                .build();
        Component exampleComponent4 = new ComponentTestDataBuilder()
                .withId(4L)
                .withType(Component.TYPE_STORAGE)
                .build();
        Component exampleComponent5 = new ComponentTestDataBuilder()
                .withId(5L)
                .withType(Component.TYPE_PSU)
                .build();
        Component exampleComponent6 = new ComponentTestDataBuilder()
                .withId(6L)
                .withType(Component.TYPE_CASE)
                .build();
        Component exampleComponent7 = new ComponentTestDataBuilder()
                .withId(7L)
                .withType(Component.TYPE_GRAPHIC_CARD)
                .build();

        this.orderComponents = Arrays.asList(exampleComponent1, exampleComponent2, exampleComponent3,
                exampleComponent4, exampleComponent5, exampleComponent6, exampleComponent7);

        return this;
    }

    public Order build() {
        return new Order(this.id, this.buildService, this.buyerData, this.orderComponents);
    }

}
