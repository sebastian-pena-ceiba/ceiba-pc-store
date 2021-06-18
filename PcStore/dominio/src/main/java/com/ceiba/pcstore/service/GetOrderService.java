package com.ceiba.pcstore.service;

import com.ceiba.pcstore.model.dto.OrderDto;
import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.port.repository.IOrderRepository;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

public class GetOrderService {

    private static final String MESSAGE_TRACKING_CODE_REQUIRED = "The tracking code is required";

    private IOrderRepository orderRepository;

    public GetOrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto execute(String trackingCode) {

        validarObligatorio(trackingCode, MESSAGE_TRACKING_CODE_REQUIRED);

        return orderRepository.findOrderByTrackingCode(trackingCode);
    }

}
