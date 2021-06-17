package com.ceiba.pcstore.service;

import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.port.repository.IComponentRepository;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

public class GetComponentService {

    private static final String MESSAGE_ID_REQUIRED = "The id must be present";

    private IComponentRepository componentRepository;

    public GetComponentService(IComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public Component execute(Long id) {

        validarObligatorio(id, MESSAGE_ID_REQUIRED);

        return componentRepository.findComponentById(id);
    }

}
