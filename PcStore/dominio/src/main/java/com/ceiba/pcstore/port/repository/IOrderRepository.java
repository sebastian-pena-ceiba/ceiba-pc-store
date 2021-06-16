package com.ceiba.pcstore.port.repository;

import com.ceiba.pcstore.model.entity.Order;

public interface IOrderRepository {

    /**
     * Create an order.
     *
     * @param order
     * @return
     */
    Order createOrder(Order order);

    /**
     * Update the order status by its id.
     *
     * @param id
     * @param status
     * @return
     */
    Order updateOrderStatusById(Long id, String status);

    /**
     * Find an order by its id.
     *
     * @param id
     * @return
     */
    Order findOrderById(Long id);

    /**
     * find and order by its tracking code.
     *
     * @param trackingCode
     * @return
     */
    Order findOrderByTrackingCode(String trackingCode);

    /**
     * Validate if an order exists by its id.
     *
     * @param id
     * @return
     */
    Boolean existOrderById(Long id);

    /**
     * Validate if an order exists by its tracking code.
     *
     * @param trackingCode
     * @return
     */
    Boolean existOrderByTrackingCode(String trackingCode);

}
