package com.ceiba.pcstore.service;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pcstore.model.dto.OrderDto;
import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.port.repository.IOrderRepository;

public class CreateOrderService {

    private static final String MESSAGE_ORDER_EXISTS = "This order is already registered";

    private IOrderRepository orderRepository;

    public CreateOrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto execute(Order order) {

        validateIfOrderExists(order);
        return orderRepository.createOrder(order);
    }

    private void validateIfOrderExists(Order order) {

        boolean exists = orderRepository.existOrderById(order.getId()) || orderRepository.existOrderByTrackingCode(order.getTrackingCode());

        if (exists) {
            throw new ExcepcionDuplicidad(MESSAGE_ORDER_EXISTS);
        }
    }

}
