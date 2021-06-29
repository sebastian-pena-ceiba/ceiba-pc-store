package com.ceiba.pcstore.service;

import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.port.repository.IComponentRepository;
import com.ceiba.pcstore.testdatabuilder.ComponentTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetComponentListServiceTest {

    /**
     * Test that GetComponentListService work with type filter.
     */
    @Test
    public void getComponentsFilteredByTypeTest() {

        // arrange
        String componentType = Component.TYPE_BOARD;
        Component component = new ComponentTestDataBuilder()
                .withType(componentType)
                .build();
        IComponentRepository componentRepository = Mockito.mock(IComponentRepository.class);
        Mockito.when(componentRepository.findAllComponentsByType(componentType)).thenReturn(Collections.singletonList(component));
        GetComponentListService getComponentListService = new GetComponentListService(componentRepository);

        // act
        List<Component> componentList = getComponentListService.execute(componentType);

        // assert
        assertEquals(componentType, componentList.get(0).getType());
    }

    /**
     * Test that GetComponentListService work with no filter.
     */
    @Test
    public void getAllComponentsTest() {

        // arrange
        Component component1 = new ComponentTestDataBuilder().build();
        Component component2 = new ComponentTestDataBuilder().build();
        List<Component> componentList = Arrays.asList(component1, component2);
        IComponentRepository componentRepository = Mockito.mock(IComponentRepository.class);
        Mockito.when(componentRepository.findAllComponents()).thenReturn(componentList);
        GetComponentListService getComponentListService = new GetComponentListService(componentRepository);

        // act
        List<Component> resultComponentList = getComponentListService.execute(null);

        // assert
        assertEquals(2, resultComponentList.size());
    }

}
