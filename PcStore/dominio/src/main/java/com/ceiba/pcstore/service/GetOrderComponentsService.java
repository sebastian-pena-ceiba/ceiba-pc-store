package com.ceiba.pcstore.service;

import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.port.repository.IComponentRepository;

import java.util.List;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

public class GetOrderComponentsService {

    private static final String MESSAGE_ID_REQUIRED = "The order id is required";

    private IComponentRepository componentRepository;

    public GetOrderComponentsService(IComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    /**
     * Get all components related to an order.
     *
     * @param orderId
     * @return
     */
    public List<Component> execute(Long orderId) {

        validarObligatorio(orderId, MESSAGE_ID_REQUIRED);

        return componentRepository.findAllComponentsByOrder(orderId);
    }

}
