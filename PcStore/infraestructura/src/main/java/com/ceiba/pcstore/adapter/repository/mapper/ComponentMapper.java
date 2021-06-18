package com.ceiba.pcstore.adapter.repository.mapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pcstore.model.entity.Component;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponentMapper implements RowMapper<Component>, MapperResult {

    @Override
    public Component mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String type = rs.getString("type");
        String name = rs.getString("name");
        BigDecimal price = rs.getBigDecimal("price");

        return new Component(id, type, name, price);
    }

}
