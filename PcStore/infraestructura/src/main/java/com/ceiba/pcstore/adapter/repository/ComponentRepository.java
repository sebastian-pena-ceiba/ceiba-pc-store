package com.ceiba.pcstore.adapter.repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pcstore.adapter.repository.mapper.ComponentMapper;
import com.ceiba.pcstore.model.entity.Component;
import com.ceiba.pcstore.port.repository.IComponentRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComponentRepository implements IComponentRepository {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="component", value="list")
    private static String sqlList;

    @SqlStatement(namespace="component", value="listByType")
    private static String sqlListByType;

    @SqlStatement(namespace="component", value="listByOrder")
    private static String sqlListByOrder;

    @SqlStatement(namespace = "component", value = "selectById")
    private static String sqlSelectById;

    public ComponentRepository(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<Component> findAllComponents() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlList, new ComponentMapper());
    }

    @Override
    public Component findComponentById(Long id) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlSelectById, paramSource, new ComponentMapper());
    }

    @Override
    public List<Component> findAllComponentsByOrder(Long orderId) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("orderId", orderId);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListByOrder, paramSource, new ComponentMapper());
    }

    @Override
    public List<Component> findAllComponentsByType(String type) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("type", type);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlList, paramSource, new ComponentMapper());
    }

}
