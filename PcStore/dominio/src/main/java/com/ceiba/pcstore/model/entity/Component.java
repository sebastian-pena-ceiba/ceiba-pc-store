package com.ceiba.pcstore.model.entity;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Getter
public class Component {

    public static final String TYPE_BOARD = "Board";
    public static final String TYPE_CPU = "CPU";
    public static final String TYPE_RAM = "Ram";
    public static final String TYPE_STORAGE = "Storage";
    public static final String TYPE_PSU = "Power Supply";
    public static final String TYPE_CASE = "Case";
    public static final String TYPE_GRAPHIC_CARD = "Graphic Card";

    public static final List<String> TYPE_LIST = Arrays.asList(TYPE_BOARD, TYPE_CPU, TYPE_RAM, TYPE_STORAGE,
            TYPE_PSU, TYPE_CASE, TYPE_GRAPHIC_CARD);

    private static final String MESSAGE_INVALID_TYPE = "The component type is not valid";
    private static final String MESSAGE_INVALID_PRICE = "The price must be bigger than 0";

    private Long id;
    private String type;
    private String name;
    private BigDecimal price;

    public Component(Long id, String type, String name, BigDecimal price) {

        // values to validate
        if (!TYPE_LIST.contains(type)) {
            throw new ExcepcionValorInvalido(MESSAGE_INVALID_TYPE);
        }

        if (price.signum() != 1) {
            throw new ExcepcionValorInvalido(MESSAGE_INVALID_PRICE);
        }

        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

}
