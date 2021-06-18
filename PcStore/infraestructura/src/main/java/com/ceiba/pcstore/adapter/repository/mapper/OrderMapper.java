package com.ceiba.pcstore.adapter.repository.mapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pcstore.model.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderMapper implements RowMapper<Order>, MapperResult {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        Boolean buildService = rs.getBoolean("build_service");

        return new Order(
                id,
                buildService,
                null,
                null
        );
    }

}
