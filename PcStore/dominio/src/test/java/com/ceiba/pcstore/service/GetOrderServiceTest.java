package com.ceiba.pcstore.service;

import com.ceiba.pcstore.model.dto.OrderDto;
import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.port.repository.IOrderRepository;
import com.ceiba.pcstore.testdatabuilder.OrderDtoTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class GetOrderServiceTest {

    /**
     * Get an order by tacking code that doesn't exist
     */
    @Test
    public void execute_WithInvalidTrackingCodeTest() {

        // arrange
        String trackingCode = UUID.randomUUID().toString();
        String expectedMessage = "The order was not found";
        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);

        Mockito.when(orderRepository.existOrderByTrackingCode(trackingCode)).thenReturn(false);

        GetOrderService getOrderService = new GetOrderService(orderRepository);

        // act - assert
        Exception exception = assertThrows(Exception.class, () -> {
            getOrderService.execute(trackingCode);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Get an order by tracking code that exists.
     */
    @Test
    public void execute_WithValidTrackingCodeTest() {

        // arrange
        String trackingCode = UUID.randomUUID().toString();
        OrderDto orderDto = new OrderDtoTestDataBuilder()
                .withId(1L)
                .withTrackingCode(trackingCode)
                .build();
        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);

        Mockito.when(orderRepository.existOrderByTrackingCode(trackingCode)).thenReturn(true);
        Mockito.when(orderRepository.findOrderByTrackingCode(trackingCode)).thenReturn(orderDto);

        GetOrderService getOrderService = new GetOrderService(orderRepository);

        // act
        OrderDto expectedOrderDto = getOrderService.execute(trackingCode);

        // assert
        assertEquals(expectedOrderDto.getId(), orderDto.getId());
        assertEquals(expectedOrderDto.getTrackingCode(), orderDto.getTrackingCode());
    }

    @Test
    public void execute_withPastOrderToShipTest() {

        // arrange
        String trackingCode = UUID.randomUUID().toString();
        OrderDto orderDto = new OrderDtoTestDataBuilder()
                .withId(1L)
                .withShippingDate(LocalDate.now())
                .withDeliveredDate(LocalDate.now().plusDays(2))
                .withTrackingCode(trackingCode)
                .build();
        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);

        Mockito.when(orderRepository.existOrderByTrackingCode(trackingCode)).thenReturn(true);
        Mockito.when(orderRepository.findOrderByTrackingCode(trackingCode)).thenReturn(orderDto);

        GetOrderService getOrderService = new GetOrderService(orderRepository);

        // act
        OrderDto expectedOrderDto = getOrderService.execute(trackingCode);

        // assert
        assertEquals(Order.STATUS_SHIPPED, expectedOrderDto.getStatus());
    }

    @Test
    public void execute_withPastOrderToDeliverTest() {

        // arrange
        String trackingCode = UUID.randomUUID().toString();
        OrderDto orderDto = new OrderDtoTestDataBuilder()
                .withId(1L)
                .withShippingDate(LocalDate.now().minusDays(2))
                .withDeliveredDate(LocalDate.now())
                .withTrackingCode(trackingCode)
                .build();
        IOrderRepository orderRepository = Mockito.mock(IOrderRepository.class);

        Mockito.when(orderRepository.existOrderByTrackingCode(trackingCode)).thenReturn(true);
        Mockito.when(orderRepository.findOrderByTrackingCode(trackingCode)).thenReturn(orderDto);

        GetOrderService getOrderService = new GetOrderService(orderRepository);

        // act
        OrderDto expectedOrderDto = getOrderService.execute(trackingCode);

        // assert
        assertEquals(Order.STATUS_DELIVERED, expectedOrderDto.getStatus());
    }
    
}
