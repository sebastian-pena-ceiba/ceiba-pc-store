package com.ceiba.pcstore.controller;

import com.ceiba.ComandoRespuesta;
import com.ceiba.pcstore.command.OrderCommand;
import com.ceiba.pcstore.command.manager.CreateOrderManager;
import com.ceiba.pcstore.command.manager.GetOrderManager;
import com.ceiba.pcstore.model.dto.OrderDto;
import com.ceiba.pcstore.model.entity.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private CreateOrderManager createOrderManager;
    private GetOrderManager getOrderManager;

    public OrderController(CreateOrderManager createOrderManager, GetOrderManager getOrderManager) {
        this.createOrderManager = createOrderManager;
        this.getOrderManager = getOrderManager;
    }

    @PostMapping
    public ComandoRespuesta<OrderDto> createOrder(@RequestBody OrderCommand orderCommand) {
        return createOrderManager.ejecutar(orderCommand);
    }

    @GetMapping("/{code}")
    public OrderDto getOrder(@PathVariable("code") String trackingCode) {
        return getOrderManager.ejecutar(trackingCode);
    }

}
