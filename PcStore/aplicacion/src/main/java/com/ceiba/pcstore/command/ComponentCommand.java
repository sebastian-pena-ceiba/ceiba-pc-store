package com.ceiba.pcstore.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComponentCommand {

    private Long id;
    private String type;
    private String name;
    private BigDecimal price;

}
