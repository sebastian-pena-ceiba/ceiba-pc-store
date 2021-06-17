package com.ceiba.pcstore.command.manager;

import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.service.GetOrderService;
import org.springframework.stereotype.Component;

@Component
public class GetOrderManager {

    private GetOrderService getOrderService;

    public GetOrderManager(GetOrderService getOrderService) {
        this.getOrderService = getOrderService;
    }

    public Order ejecutar(String trackingCode) {
        return getOrderService.execute(trackingCode);
    }

}
