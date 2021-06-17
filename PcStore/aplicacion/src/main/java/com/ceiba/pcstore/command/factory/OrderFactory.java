package com.ceiba.pcstore.command.factory;

import com.ceiba.pcstore.command.OrderCommand;
import com.ceiba.pcstore.model.entity.BuyerData;
import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Component
public class OrderFactory {

    @Autowired
    private ComponentFactory componentFactory;

    public Order create(OrderCommand orderCommand) {

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
                orderCommand.getTrackingCode(),
                buyerData,
                componentList
        );
    }

}
