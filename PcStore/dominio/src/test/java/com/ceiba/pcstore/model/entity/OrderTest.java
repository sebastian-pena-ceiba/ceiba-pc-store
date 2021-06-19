package com.ceiba.pcstore.model.entity;

import com.ceiba.pcstore.testdatabuilder.OrderTestDataBuilder;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

    /**
     * Valid order creation with null component list
     */
    @Test
    public void validCreateOrder_WithNullComponentListTest() {

        // arrange
        String exceptionExpectedMessage = "The component list is required";
        OrderTestDataBuilder orderBuilder = new OrderTestDataBuilder()
                .withOrderComponents(null);

        // act - assert
        Exception exception = assertThrows(Exception.class, orderBuilder::build);
        assertEquals(exceptionExpectedMessage, exception.getMessage());
    }

    /**
     * Valid order creation with empty component list
     */
    @Test
    public void validCreateOrder_WithEmptyComponentListTest() {

        // arrange
        String exceptionExpectedMessage = "The component list is required";
        OrderTestDataBuilder orderBuilder = new OrderTestDataBuilder()
                .withOrderComponents(new ArrayList<>());

        // act - assert
        Exception exception = assertThrows(Exception.class, orderBuilder::build);
        assertEquals(exceptionExpectedMessage, exception.getMessage());
    }

    /**
     * Validate create an order with building service but not all components
     */
    @Test
    public void validCreateOrder_WithBuildingServiceAndNotAllComponentsTest() {

        // one component and build service
        OrderTestDataBuilder orderBuilder = new OrderTestDataBuilder()
                .withBuildService(true);

        String exceptionExpectedMessage = "The component list requires all component types for building";

        Exception exception = assertThrows(Exception.class, orderBuilder::build);
        assertEquals(exceptionExpectedMessage, exception.getMessage());
    }

    /**
     * Validate the shipping date when create a normal order.
     */
    //@Test
    public void validCreateOrderShippingDate_WithNoBuildingServiceTest() {

        // default order
        OrderTestDataBuilder orderBuilder = new OrderTestDataBuilder();
        // the desired datetime
        LocalDate imposedLocalDate = LocalDate.of(2021, 6, 4);
        // the right shipping date according the desired datetime
        LocalDate expectedSippingDate = LocalDate.of(2021, 6, 8);

        try (MockedStatic<LocalDate> utilities = Mockito.mockStatic(LocalDate.class)) {

            // mock the function LocalDate.now() with custom datetime
            utilities.when(LocalDate::now).thenReturn(imposedLocalDate);

            Order order = orderBuilder.build();

            assertEquals(expectedSippingDate, order.getShippingDate());
        }
    }

    /**
     * Validate the shipping date when create an order with build service.
     */
    //@Test
    public void validCreateOrderShippingDate_WithBuildingServiceTest() {

        // order with build service option (and all components to work OK)
        OrderTestDataBuilder orderBuilder = new OrderTestDataBuilder()
                .withBuildService(true)
                .withAllDefaultComponents();

        // the desired datetime
        LocalDate imposedLocalDate = LocalDate.of(2021, 6, 4);
        // the right shipping date according the desired datetime
        LocalDate expectedSippingDate = LocalDate.of(2021, 6, 11);

        try (MockedStatic<LocalDate> utilities = Mockito.mockStatic(LocalDate.class)) {

            // mock the function LocalDate.now() with custom datetime
            utilities.when(LocalDate::now).thenReturn(imposedLocalDate);

            Order order = orderBuilder.build();

            //assertEquals(imposedLocalDateTime, order.getPlacementDate());
            assertEquals(expectedSippingDate, order.getShippingDate());
        }
    }

}
