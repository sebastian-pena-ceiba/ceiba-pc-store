package com.ceiba.pcstore.service;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.pcstore.model.dto.OrderDto;
import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.port.repository.IOrderRepository;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

public class GetOrderService {

    private static final String MESSAGE_TRACKING_CODE_REQUIRED = "The tracking code is required";
    private static final String MESSAGE_ORDER_404 = "The order was not found";

    private IOrderRepository orderRepository;

    public GetOrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto execute(String trackingCode) {

        // validate tracking code
        validarObligatorio(trackingCode, MESSAGE_TRACKING_CODE_REQUIRED);

        // validate if the order exists
        if (!orderRepository.existOrderByTrackingCode(trackingCode)) {
            throw new ExcepcionSinDatos(MESSAGE_ORDER_404);
        }

        // get the order
        OrderDto orderDto = orderRepository.findOrderByTrackingCode(trackingCode);

        // validate and update the status
        checkOrderStatus(orderDto);

        // return the final order
        return orderDto;
    }

    /**
     * Validate the order status and update it in the object and in repository.
     *
     * @param orderDto
     */
    private void checkOrderStatus(OrderDto orderDto) {

        LocalDate today = LocalDate.now();

        // valid shipping status
        if ((orderDto.getShippingDate().isEqual(today) || orderDto.getShippingDate().isBefore(today))
                && orderDto.getStatus().equals(Order.STATUS_PROCESSING)) {

            // update to next status
            setNextStatus(orderDto);

            // update with repository
            orderRepository.updateOrderStatusById(orderDto.getId(), orderDto.getStatus());
        }

        // valid delivering status
        if ((orderDto.getDeliveredDate().isEqual(today) || orderDto.getDeliveredDate().isBefore(today)) && orderDto.getStatus().equals(Order.STATUS_SHIPPED)) {

            // update to next status
            setNextStatus(orderDto);

            // update with repository
            orderRepository.updateOrderStatusById(orderDto.getId(), orderDto.getStatus());
        }
    }

    /**
     * Update to next status.
     */
    private void setNextStatus(OrderDto orderDto) {

        if (Order.STATUS_PROCESSING.equals(orderDto.getStatus())) {
            orderDto.setStatus(Order.STATUS_SHIPPED);
        } else if (Order.STATUS_SHIPPED.equals(orderDto.getStatus())) {
            orderDto.setStatus(Order.STATUS_DELIVERED);
        }
    }

}
