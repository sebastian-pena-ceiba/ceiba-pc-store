package com.ceiba.pcstore.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCommand {

    private Long id;
    private Boolean buildService;
    private String buyerName;
    private Integer buyerIdNumber;
    private String buyerEmail;
    private String buyerPhoneNumber;
    private String buyerAddress;
    private List<ComponentCommand> orderComponents;

}
