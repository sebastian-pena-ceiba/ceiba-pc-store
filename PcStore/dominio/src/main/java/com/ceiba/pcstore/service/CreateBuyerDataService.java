package com.ceiba.pcstore.service;

import com.ceiba.pcstore.model.entity.BuyerData;
import com.ceiba.pcstore.port.repository.IBuyerDataRepository;

public class CreateBuyerDataService {

    private IBuyerDataRepository buyerDataRepository;

    public CreateBuyerDataService(IBuyerDataRepository buyerDataRepository) {
        this.buyerDataRepository = buyerDataRepository;
    }

    public BuyerData execute(BuyerData buyerData) {
        return buyerDataRepository.createBuyerData(buyerData);
    }

}
