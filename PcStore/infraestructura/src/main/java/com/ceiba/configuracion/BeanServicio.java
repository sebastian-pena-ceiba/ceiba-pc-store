package com.ceiba.configuracion;

import com.ceiba.pcstore.port.repository.IBuyerDataRepository;
import com.ceiba.pcstore.port.repository.IComponentRepository;
import com.ceiba.pcstore.port.repository.IOrderRepository;
import com.ceiba.pcstore.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public CreateOrderService createOrderService(IOrderRepository orderRepository) {
        return new CreateOrderService(orderRepository);
    }

    @Bean
    public CreateBuyerDataService createBuyerDataService(IBuyerDataRepository buyerDataRepository) {
        return new CreateBuyerDataService(buyerDataRepository);
    }

    @Bean
    public GetOrderService getOrderService(IOrderRepository orderRepository) {
        return new GetOrderService(orderRepository);
    }

    @Bean
    public GetOrderComponentsService getOrderComponentsService(IComponentRepository componentRepository) {
        return new GetOrderComponentsService(componentRepository);
    }

    @Bean
    public GetComponentListService getComponentListService(IComponentRepository componentRepository) {
        return new GetComponentListService(componentRepository);
    }

    @Bean
    public GetComponentService getComponentService(IComponentRepository componentRepository) {
        return new GetComponentService(componentRepository);
    }

}
