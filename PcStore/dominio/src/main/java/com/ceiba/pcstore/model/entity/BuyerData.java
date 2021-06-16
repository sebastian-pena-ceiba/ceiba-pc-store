package com.ceiba.pcstore.model.entity;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarLongitudMinima;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class BuyerData {

    private static final int MIN_ID_NUMBER_LENGTH = 7;

    private static final String MESSAGE_NAME_IS_REQUIRED = "The name field is required";
    private static final String MESSAGE_ID_NUMBER_IS_REQUIRED = "The id number is required";
    private static final String MESSAGE_ID_NUMBER_LENGTH = "The id number length must be bigger than " + MIN_ID_NUMBER_LENGTH + " digits";
    private static final String MESSAGE_PHONE_NUMBER_IS_REQUIRED = "A phone number must be provided";
    private static final String MESSAGE_ADDRESS_IS_REQUIRED = "The address is required";

    private Long id;
    private String name;
    private Integer idNumber;
    private String email;
    private String phoneNumber;
    private String address;

    public BuyerData(Long id, String name, Integer idNumber, String email, String phoneNumber, String address) {

        // validate required fields
        validarObligatorio(name, MESSAGE_NAME_IS_REQUIRED);
        validarObligatorio(idNumber, MESSAGE_ID_NUMBER_IS_REQUIRED);
        validarLongitudMinima(idNumber, MIN_ID_NUMBER_LENGTH, MESSAGE_ID_NUMBER_LENGTH);
        validarObligatorio(phoneNumber, MESSAGE_PHONE_NUMBER_IS_REQUIRED);
        validarObligatorio(address, MESSAGE_ADDRESS_IS_REQUIRED);

        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
