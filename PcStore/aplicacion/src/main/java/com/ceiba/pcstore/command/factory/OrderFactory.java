package com.ceiba.pcstore.command.factory;

import com.ceiba.pcstore.command.OrderCommand;
import com.ceiba.pcstore.model.entity.BuyerData;
import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@org.springframework.stereotype.Component
public class OrderFactory {

    private static final String MESSAGE_COMPONENTS_REQUIRED = "Almost one component is required to place an order";

    @Autowired
    private ComponentFactory componentFactory;

    public Order create(OrderCommand orderCommand) {

        // validation
        validarObligatorio(orderCommand.getOrderComponents(), MESSAGE_COMPONENTS_REQUIRED);

        // create BuyerData
        BuyerData buyerData = new BuyerData(
                null,
                orderCommand.getBuyerName(),
                orderCommand.getBuyerIdNumber(),
                orderCommand.getBuyerEmail(),
                orderCommand.getBuyerPhoneNumber(),
                orderCommand.getBuyerAddress()
        );

        // create Component list
        List<Component> componentList = new ArrayList<>();

        orderCommand.getOrderComponents().forEach(componentCommand -> {
            Component component = componentFactory.create(componentCommand);
            componentList.add(component);
        });

        return new Order(
                orderCommand.getId(),
                orderCommand.getBuildService(),
                buyerData,
                componentList
        );
    }

}
