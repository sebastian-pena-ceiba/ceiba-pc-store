package com.ceiba.pcstore.adapter.repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pcstore.model.entity.BuyerData;
import com.ceiba.pcstore.port.repository.IBuyerDataRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class BuyerDataRepository implements IBuyerDataRepository {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="buyerData", value="create")
    private static String sqlCreate;

    @SqlStatement(namespace = "buyerData", value = "selectById")
    private static String sqlSelectById;

    public BuyerDataRepository(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public BuyerData createBuyerData(BuyerData buyerData) {

        Long id = this.customNamedParameterJdbcTemplate.crear(buyerData, sqlCreate);

        return new BuyerData(
                id,
                buyerData.getName(),
                buyerData.getIdNumber(),
                buyerData.getEmail(),
                buyerData.getPhoneNumber(),
                buyerData.getAddress()
        );
    }

    @Override
    public BuyerData findById(Long id) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlSelectById, paramSource, BuyerData.class);
    }

}
