package com.ceiba.pcstore.command.manager;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.pcstore.command.OrderCommand;
import com.ceiba.pcstore.command.factory.OrderFactory;
import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.service.CreateOrderService;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderManager implements ManejadorComandoRespuesta<OrderCommand, ComandoRespuesta<OrderCommand>> {

    private OrderFactory orderFactory;
    private CreateOrderService createOrderService;

    public CreateOrderManager(OrderFactory orderFactory, CreateOrderService createOrderService) {
        this.orderFactory = orderFactory;
        this.createOrderService = createOrderService;
    }

    @Override
    public ComandoRespuesta<OrderCommand> ejecutar(OrderCommand orderCommand) {

        Order order = orderFactory.create(orderCommand);
        Order createdOrder = createOrderService.execute(order);

        orderCommand.setId(createdOrder.getId());

        return new ComandoRespuesta<>(orderCommand);
    }

}
