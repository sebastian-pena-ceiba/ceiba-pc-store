package com.ceiba.pcstore.service;

import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.port.repository.IComponentRepository;
import com.ceiba.pcstore.testdatabuilder.ComponentTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GetOrderComponentsServiceTest {

    @Test
    public void executeTest() {

        // arrange
        Component component = new ComponentTestDataBuilder().build();
        List<Component> componentList = Collections.singletonList(component);
        IComponentRepository componentRepository = Mockito.mock(IComponentRepository.class);

        Mockito.when(componentRepository.findAllComponentsByOrder(Mockito.anyLong())).thenReturn(componentList);

        GetOrderComponentsService getOrderComponentsService = new GetOrderComponentsService(componentRepository);

        // act
        List<Component> resultComponentList = getOrderComponentsService.execute(1L);

        // assert
        assertTrue(resultComponentList.size() > 0);
    }

}
