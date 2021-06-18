package com.ceiba.pcstore.adapter.repository.mapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pcstore.model.dto.OrderDto;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDtoMapper implements RowMapper<OrderDto>, MapperResult {

    @Override
    public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        Boolean buildService = rs.getBoolean("build_service");

        LocalDate placementDate = extraerLocalDateTime(rs, "placement_date").toLocalDate();
        LocalDate shippingDate = extraerLocalDateTime(rs, "shipping_date").toLocalDate();
        String status = rs.getString("status");
        String trackingCode = rs.getString("tracking_code");
        String buyerName = rs.getString("b.name");
        Integer buyerIdNumber = rs.getInt("b.id_number");
        String buyerEmail = rs.getString("b.email");
        String buyerPhoneNumber = rs.getString("b.phone_number");
        String buyerAddress = rs.getString("b.address");
        BigDecimal orderPrice = rs.getBigDecimal("order_price");

        return new OrderDto(
                id,
                buildService,
                placementDate,
                shippingDate,
                status,
                trackingCode,
                buyerName,
                buyerIdNumber,
                buyerEmail,
                buyerPhoneNumber,
                buyerAddress,
                null,
                orderPrice
        );
    }

}
