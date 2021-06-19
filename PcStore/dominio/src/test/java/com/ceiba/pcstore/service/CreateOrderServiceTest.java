package com.ceiba.pcstore.service;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pcstore.model.dto.OrderDto;
import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.port.repository.IOrderRepository;
import com.ceiba.pcstore.testdatabuilder.OrderDtoTestDataBuilder;
import com.ceiba.pcstore.testdatabuilder.OrderTestDataBuilder;
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
     * Validate create an order with one component
     */
    @Test
    public void validCreateOrder_WithOneComponentTest() {

        // arrange
        Order order = new OrderTestDataBuilder()
                .withId(null)
                .build();
        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);
        Mockito.when(orderRepository.createOrder(order)).thenReturn(new OrderDtoTestDataBuilder().build());
        CreateOrderService createOrderService = new CreateOrderService(orderRepository);

        // act
        OrderDto createdOrder = createOrderService.execute(order);

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
     * Validate create an order with building service and all required components.
     */
    @Test
    public void validCreateOrder_WithBuildingServiceAndAllComponentsTest() {

        // create a builder with all components and build service option
        Order order = new OrderTestDataBuilder()
                .withBuildService(true)
                .withAllDefaultComponents()
                .build();

        OrderDto orderDto = new OrderDtoTestDataBuilder()
                .withBuildService(order.getBuildService())
                .withAllDefaultComponents()
                .build();

        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);
        Mockito.when(orderRepository.createOrder(order)).thenReturn(orderDto);
        CreateOrderService createOrderService = new CreateOrderService(orderRepository);

        // act
        OrderDto createdOrder = createOrderService.execute(order);

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

        OrderDto orderDto = new OrderDtoTestDataBuilder()
                .withBuildService(order.getBuildService())
                .withAllDefaultComponents()
                .withOrderPrice(order.getOrderPrice())
                .build();

        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);
        Mockito.when(orderRepository.createOrder(order)).thenReturn(orderDto);
        CreateOrderService createOrderService = new CreateOrderService(orderRepository);

        double componentsTotalPrice = 1123.5;
        BigDecimal buildingAdditionalPrice = BigDecimal.valueOf(componentsTotalPrice).multiply(BigDecimal.valueOf(0.1));
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(componentsTotalPrice).add(buildingAdditionalPrice);

        // act
        OrderDto createdOrder = createOrderService.execute(order);

        // assert
        assertEquals(expectedTotalPrice, createdOrder.getOrderPrice());
    }

}
