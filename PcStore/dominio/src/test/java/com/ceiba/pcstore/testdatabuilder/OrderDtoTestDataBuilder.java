package com.ceiba.pcstore.testdatabuilder;

import com.ceiba.pcstore.model.dto.OrderDto;
import com.ceiba.pcstore.model.entity.BuyerData;
import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.model.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderDtoTestDataBuilder {

    private Long id;
    private Boolean buildService;
    private LocalDate placementDate;
    private LocalDate shippingDate;
    private String status;
    private String trackingCode;
    private String buyerName;
    private Integer buyerIdNumber;
    private String buyerEmail;
    private String buyerPhoneNumber;
    private String buyerAddress;
    private List<Component> orderComponents;
    private BigDecimal orderPrice;

    public OrderDtoTestDataBuilder() {

        this.id = 1L;
        this.buildService = false;
        this.placementDate = LocalDate.now();
        this.shippingDate = LocalDate.now();
        this.status = Order.STATUS_PROCESSING;
        this.trackingCode = "asd123ads";
        this.buyerName = "Joseph Smith";
        this.buyerIdNumber = 1098765432;
        this.buyerEmail = "example@example.com";
        this.buyerPhoneNumber = "3334445566";
        this.buyerAddress = "Av 1 dg 2";

        Component exampleComponent = new ComponentTestDataBuilder().build();

        this.orderComponents = Collections.singletonList(exampleComponent);

        this.orderPrice = exampleComponent.getPrice();
    }

    public OrderDtoTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public OrderDtoTestDataBuilder withBuildService(Boolean buildService) {
        this.buildService = buildService;
        return this;
    }

    public OrderDtoTestDataBuilder withPlacementDate(LocalDate placementDate) {
        this.placementDate = placementDate;
        return this;
    }

    public OrderDtoTestDataBuilder withShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public OrderDtoTestDataBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderDtoTestDataBuilder withTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
        return this;
    }

    public OrderDtoTestDataBuilder withBuyerData(BuyerData buyerData) {
        this.buyerName = buyerData.getName();
        this.buyerIdNumber = buyerData.getIdNumber();
        this.buyerEmail = buyerData.getEmail();
        this.buyerPhoneNumber = buyerData.getPhoneNumber();
        this.buyerAddress = buyerData.getAddress();

        return this;
    }

    public OrderDtoTestDataBuilder withOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    public OrderDtoTestDataBuilder withOrderComponents(List<Component> orderComponents) {

        if (orderComponents == null || orderComponents.isEmpty()) {
            this.orderComponents = orderComponents;
        } else {
            this.orderComponents = new ArrayList<>();
            this.orderComponents.addAll(orderComponents);
        }

        return this;
    }

    public OrderDtoTestDataBuilder withAllDefaultComponents() {

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

    public OrderDto build() {
        return new OrderDto(
                this.id,
                this.buildService,
                this.placementDate,
                this.shippingDate,
                this.status,
                this.trackingCode,
                this.buyerName,
                this.buyerIdNumber,
                this.buyerEmail,
                this.buyerPhoneNumber,
                this.buyerAddress,
                this.orderComponents,
                this.orderPrice
        );
    }

}
