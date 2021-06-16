package com.ceiba.pcstore.port.repository;

import com.ceiba.pcstore.model.entity.BuyerData;

public interface IBuyerDataRepository {

    /**
     * Create a BuyerData.
     *
     * @param buyerData
     * @return
     */
    BuyerData createBuyerData(BuyerData buyerData);

    /**
     * Find a BuyerData by its id.
     *
     * @param id
     * @return
     */
    BuyerData findById(Long id);

}
