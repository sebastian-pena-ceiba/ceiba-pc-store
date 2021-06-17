package com.ceiba.configuracion;

import com.ceiba.pcstore.port.repository.IBuyerDataRepository;
import com.ceiba.pcstore.port.repository.IComponentRepository;
import com.ceiba.pcstore.port.repository.IOrderRepository;
import com.ceiba.pcstore.service.*;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
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
    public GetComponentListService getComponentListService(IComponentRepository componentRepository) {
        return new GetComponentListService(componentRepository);
    }

    @Bean
    public GetComponentService getComponentService(IComponentRepository componentRepository) {
        return new GetComponentService(componentRepository);
    }
    

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }
	

}
