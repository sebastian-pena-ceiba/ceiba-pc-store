package com.ceiba.pcstore.service;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.port.repository.IOrderRepository;
import com.ceiba.pcstore.service.testdatabuilder.OrderTestDataBuilder;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateOrderServiceTest {

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
     * Validate create an order with one component
     */
    @Test
    public void validCreateOrder_WithOneComponentTest() {

        // arrange
        Order order = new OrderTestDataBuilder()
                .withId(null)
                .build();
        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);
        Mockito.when(orderRepository.createOrder(order)).thenReturn(new OrderTestDataBuilder().build());
        CreateOrderService createOrderService = new CreateOrderService(orderRepository);

        // act
        Order createdOrder = createOrderService.execute(order);

        // assert
        assertEquals(Long.valueOf(1), createdOrder.getId());
    }

    /**
     * Valid the method {@code validateIfOrderExists} when the order exists
     */
    @Test
    public void validValidateIfOrderExistsTest() {

        // arrange
        Order order = new OrderTestDataBuilder().build();
        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);
        Mockito.when(orderRepository.existOrderById(Mockito.anyLong())).thenReturn(true);
        CreateOrderService createOrderService = new CreateOrderService(orderRepository);

        // act - assert
        BasePrueba.assertThrows(() -> createOrderService.execute(order), ExcepcionDuplicidad.class,"This order is already registered");
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
     * Validate create an order with building service and all required components.
     */
    @Test
    public void validCreateOrder_WithBuildingServiceAndAllComponentsTest() {

        // create a builder with all components and build service option
        Order order = new OrderTestDataBuilder()
                .withBuildService(true)
                .withAllDefaultComponents()
                .build();

        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);
        Mockito.when(orderRepository.createOrder(order)).thenReturn(order);
        CreateOrderService createOrderService = new CreateOrderService(orderRepository);

        // act
        Order createdOrder = createOrderService.execute(order);

        // assert
        assertEquals(Long.valueOf(1), createdOrder.getId());
    }

    /**
     * Validate the total price when create an order with building service.
     */
    @Test
    public void validCreateOrderPrice_WithBuildingServiceAndAllComponentsTest() {

        // create a builder with all components and build service option
        Order order = new OrderTestDataBuilder()
                .withBuildService(true)
                .withAllDefaultComponents()
                .build();

        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);
        Mockito.when(orderRepository.createOrder(order)).thenReturn(order);
        CreateOrderService createOrderService = new CreateOrderService(orderRepository);

        double componentsTotalPrice = 1123.5;
        BigDecimal buildingAdditionalPrice = BigDecimal.valueOf(componentsTotalPrice).multiply(BigDecimal.valueOf(0.1));
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(componentsTotalPrice).add(buildingAdditionalPrice);

        // act
        Order createdOrder = createOrderService.execute(order);

        // assert
        assertEquals(expectedTotalPrice, createdOrder.getOrderPrice());
    }

    /**
     * Validate the shipping date when create a normal order.
     */
    @Test
    public void validPlaceOrderShippingDate_WithNoBuildingServiceTest() {

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
    @Test
    public void validPlaceOrderShippingDate_WithBuildingServiceTest() {

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
