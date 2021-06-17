package com.ceiba.pcstore.service;

import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.port.repository.IComponentRepository;

import java.util.List;

public class GetComponentListService {

    private IComponentRepository componentRepository;

    public GetComponentListService(IComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    /**
     * Get all components.
     *
     * If the {@code componentTypeFilter} param is set then get all components by the specified type
     *
     * @param componentTypeFilter
     * @return
     */
    public List<Component> execute(String componentTypeFilter) {

        if (componentTypeFilter != null) {
            return componentRepository.findAllComponentsByType(componentTypeFilter);
        }

        return componentRepository.findAllComponents();
    }

}
