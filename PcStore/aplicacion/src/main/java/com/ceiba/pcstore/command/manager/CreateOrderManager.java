package com.ceiba.pcstore.command.manager;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.pcstore.command.OrderCommand;
import com.ceiba.pcstore.command.factory.OrderFactory;
import com.ceiba.pcstore.model.dto.OrderDto;
import com.ceiba.pcstore.model.entity.BuyerData;
import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.service.CreateBuyerDataService;
import com.ceiba.pcstore.service.CreateOrderService;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderManager implements ManejadorComandoRespuesta<OrderCommand, ComandoRespuesta<OrderDto>> {

    private OrderFactory orderFactory;
    private CreateOrderService createOrderService;
    private CreateBuyerDataService createBuyerDataService;

    public CreateOrderManager(OrderFactory orderFactory, CreateOrderService createOrderService, CreateBuyerDataService createBuyerDataService) {
        this.orderFactory = orderFactory;
        this.createOrderService = createOrderService;
        this.createBuyerDataService = createBuyerDataService;
    }

    @Override
    public ComandoRespuesta<OrderDto> ejecutar(OrderCommand orderCommand) {

        // get the order from the command data
        Order order = orderFactory.create(orderCommand);

        // save buyer data
        BuyerData createBuyerData = createBuyerDataService.execute(order.getBuyerData());

        // update buyerData id
        order.getBuyerData().setId(createBuyerData.getId());

        OrderDto createdOrder = createOrderService.execute(order);

        return new ComandoRespuesta<>(createdOrder);

//        orderCommand.setId(createdOrder.getId());
//
//        return new ComandoRespuesta<>(orderCommand);
    }

}
