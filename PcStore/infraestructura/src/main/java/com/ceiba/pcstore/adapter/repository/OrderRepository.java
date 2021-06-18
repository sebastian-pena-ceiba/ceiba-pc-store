package com.ceiba.pcstore.adapter.repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pcstore.adapter.repository.mapper.OrderDtoMapper;
import com.ceiba.pcstore.adapter.repository.mapper.OrderMapper;
import com.ceiba.pcstore.model.dto.OrderDto;
import com.ceiba.pcstore.model.entity.Order;
import com.ceiba.pcstore.port.repository.IOrderRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository implements IOrderRepository {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="order", value="create")
    private static String sqlCreate;

    @SqlStatement(namespace="order", value="updateStatus")
    private static String sqlUpdateStatus;

    @SqlStatement(namespace="order", value="selectById")
    private static String sqlSelectById;

    @SqlStatement(namespace="order", value="selectByTrackingCode")
    private static String sqlSelectByTrackingCode;

    @SqlStatement(namespace="order", value="existsById")
    private static String sqlExistsById;

    @SqlStatement(namespace="order", value="existsByTrackingCode")
    private static String sqlExistsByTrackingCode;

    public OrderRepository(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Order createOrder(Order order) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("buildService", order.getBuildService());
        paramSource.addValue("placementDate", order.getPlacementDate());
        paramSource.addValue("shippingDate", order.getShippingDate());
        paramSource.addValue("status", order.getStatus());
        paramSource.addValue("trackingCode", order.getTrackingCode());
        paramSource.addValue("buyerDataId", order.getBuyerData().getId());
        paramSource.addValue("orderPrice", order.getOrderPrice());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlCreate, paramSource, keyHolder, new String[] { "id" } ); //this.customNamedParameterJdbcTemplate.crear(order, sqlCreate);
        Long id = keyHolder.getKey().longValue();

        order.setId(id);

        return order;
    }

    @Override
    public Order updateOrderStatusById(Long id, String status) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        paramSource.addValue("status", status);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlUpdateStatus, paramSource);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlSelectById, paramSource, new OrderMapper());
    }

    @Override
    public OrderDto findOrderById(Long id) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlSelectById, paramSource, new OrderDtoMapper());
    }

    @Override
    public OrderDto findOrderByTrackingCode(String trackingCode) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("trackingCode", trackingCode);

        // TODO: get buyer data and order components

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlSelectByTrackingCode, paramSource, new OrderDtoMapper());
    }

    @Override
    public Boolean existOrderById(Long id) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistsById, paramSource, Boolean.class);
    }

    @Override
    public Boolean existOrderByTrackingCode(String trackingCode) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("trackingCode", trackingCode);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistsByTrackingCode, paramSource, Boolean.class);
    }

}
