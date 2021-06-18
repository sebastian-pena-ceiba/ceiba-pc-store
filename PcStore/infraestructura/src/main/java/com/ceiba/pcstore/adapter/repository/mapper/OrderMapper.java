package com.ceiba.pcstore.adapter.repository.mapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pcstore.model.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order>, MapperResult {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        Boolean buildService = rs.getBoolean("type");

        // TODO: order dto
        return new Order(
                id,
                buildService,
                null,
                null
        );
    }

}
