package com.ceiba.pcstore.port.repository;

import com.ceiba.pcstore.model.entity.Component;

import java.util.List;

public interface IComponentRepository {

    /**
     * Find all components.
     *
     * @return
     */
    List<Component> findAllComponents();

    /**
     * Find a component by its id.
     *
     * @param id
     * @return
     */
    Component findComponentById(Long id);

    /**
     * Find all components associate to an order.
     *
     * @param orderId
     * @return
     */
    List<Component> findAllComponentsByOrder(Long orderId);

    /**
     * Get all components by a specific type.
     *
     * @param type
     * @return
     */
    List<Component> findAllComponentsByType(String type);

}
