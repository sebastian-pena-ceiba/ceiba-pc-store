package com.ceiba.pcstore.adapter.repository;

import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.port.repository.IOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRespository implements IOrderRepository {

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public Order updateOrderStatusById(Long id, String status) {
        return null;
    }

    @Override
    public Order findOrderById(Long id) {
        return null;
    }

    @Override
    public Order findOrderByTrackingCode(String trackingCode) {
        return null;
    }

    @Override
    public Boolean existOrderById(Long id) {
        return null;
    }

    @Override
    public Boolean existOrderByTrackingCode(String trackingCode) {
        return null;
    }

}
