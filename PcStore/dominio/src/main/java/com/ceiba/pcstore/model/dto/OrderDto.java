package com.ceiba.pcstore.model.dto;

import com.ceiba.pcstore.model.entity.BuyerData;
import com.ceiba.pcstore.model.entity.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Boolean buildService;
    private LocalDate placementDate;
    private LocalDate shippingDate;
    private String status;
    private String trackingCode;
    private String buyerName;
    private Integer buyerIdNumber;
    private String buyerEmail;
    private String buyerPhoneNumber;
    private String buyerAddress;
    private List<Component> orderComponents;
    private BigDecimal orderPrice;

}
