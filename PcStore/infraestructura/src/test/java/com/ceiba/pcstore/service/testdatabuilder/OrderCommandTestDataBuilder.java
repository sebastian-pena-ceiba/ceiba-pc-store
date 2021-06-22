package com.ceiba.pcstore.service.testdatabuilder;

import com.ceiba.pcstore.command.ComponentCommand;
import com.ceiba.pcstore.command.OrderCommand;
import com.ceiba.pcstore.model.entity.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderCommandTestDataBuilder {

    private Long id;
    private Boolean buildService;
    private String buyerName;
    private Integer buyerIdNumber;
    private String buyerEmail;
    private String buyerPhoneNumber;
    private String buyerAddress;
    private List<ComponentCommand> orderComponents;

    public OrderCommandTestDataBuilder() {

        this.buildService = false;
        this.buyerName = "Pedro Perez";
        this.buyerIdNumber = 1098765432;
        this.buyerEmail = "pp@example.com";
        this.buyerPhoneNumber = "3210987654";
        this.buyerAddress = "cra 1 # 2-3";
        this.orderComponents = Collections.singletonList(new ComponentCommandTestDataBuilder().build());
    }

    public OrderCommandTestDataBuilder withBuildService(Boolean buildService) {
        this.buildService = buildService;
        return this;
    }

    public OrderCommandTestDataBuilder withAllComponents() {

        ComponentCommand componentCommand1 = new ComponentCommandTestDataBuilder()
                .withType(Component.TYPE_BOARD)
                .withName("B550 Steel")
                .build();
        ComponentCommand componentCommand2 = new ComponentCommandTestDataBuilder()
                .withType(Component.TYPE_CPU)
                .withName("Ryzen 2500x")
                .build();
        ComponentCommand componentCommand3 = new ComponentCommandTestDataBuilder()
                .withType(Component.TYPE_RAM)
                .withName("2x8 DDR4")
                .build();
        ComponentCommand componentCommand4 = new ComponentCommandTestDataBuilder()
                .withType(Component.TYPE_STORAGE)
                .withName("WD Black 1TB")
                .build();
        ComponentCommand componentCommand5 = new ComponentCommandTestDataBuilder()
                .withType(Component.TYPE_CASE)
                .withName("Q500L")
                .build();
        ComponentCommand componentCommand6 = new ComponentCommandTestDataBuilder()
                .withType(Component.TYPE_PSU)
                .withName("500+ bronze")
                .build();
        ComponentCommand componentCommand7 = new ComponentCommandTestDataBuilder()
                .withType(Component.TYPE_GRAPHIC_CARD)
                .withName("RTX 3060")
                .build();

        this.orderComponents = Arrays.asList(
                componentCommand1,
                componentCommand2,
                componentCommand3,
                componentCommand4,
                componentCommand5,
                componentCommand6,
                componentCommand7
        );

        return this;
    }


    public OrderCommand build() {
        return new OrderCommand(
                id,
                buildService,
                buyerName,
                buyerIdNumber,
                buyerEmail,
                buyerPhoneNumber,
                buyerAddress,
                orderComponents
        );
    }

}
