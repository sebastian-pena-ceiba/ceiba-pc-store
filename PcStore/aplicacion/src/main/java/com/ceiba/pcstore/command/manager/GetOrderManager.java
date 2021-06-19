package com.ceiba.pcstore.command.manager;

import com.ceiba.pcstore.model.dto.OrderDto;
import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.service.GetOrderComponentsService;
import com.ceiba.pcstore.service.GetOrderService;

import java.util.List;

@org.springframework.stereotype.Component
public class GetOrderManager {

    private GetOrderService getOrderService;
    private GetOrderComponentsService getOrderComponentsService;

    public GetOrderManager(GetOrderService getOrderService, GetOrderComponentsService getOrderComponentsService) {
        this.getOrderService = getOrderService;
        this.getOrderComponentsService = getOrderComponentsService;
    }

    public OrderDto ejecutar(String trackingCode) {

        // get the order data
        OrderDto orderDto = getOrderService.execute(trackingCode);

        // get the order component list
        List<Component> componentList = getOrderComponentsService.execute(orderDto.getId());

        orderDto.setOrderComponents(componentList);

        return orderDto;
    }

}
