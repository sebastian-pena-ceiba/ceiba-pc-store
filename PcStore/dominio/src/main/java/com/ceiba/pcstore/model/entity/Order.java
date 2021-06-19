package com.ceiba.pcstore.model.entity;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import static com.ceiba.dominio.ValidadorArgumento.validarNoVacio;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Order {

    public static final String STATUS_PROCESSING = "PROCESSING";
    public static final String STATUS_SHIPPED = "SHIPPED";
    public static final String STATUS_DELIVERED = "DELIVERED";

    private static final String MESSAGE_COMPONENTS_REQUIRED = "The component list is required";
    private static final String MESSAGE_COMPONENTS_INVALID_FOR_BUILD = "The component list requires all component types for building";

    private Long id;
    private Boolean buildService;
    private LocalDate placementDate;
    private LocalDate shippingDate;
    private LocalDate deliveredDate;
    private String status;
    private String trackingCode;
    private BuyerData buyerData;
    private List<Component> orderComponents;
    private BigDecimal orderPrice;

    public Order(Long id, Boolean buildService, BuyerData buyerData, List<Component> orderComponents) {

        // valid component list is not empty
        validarObligatorio(orderComponents, MESSAGE_COMPONENTS_REQUIRED);
        validarNoVacio(orderComponents, MESSAGE_COMPONENTS_REQUIRED);

        // valid if need computer build service
        if (buildService && !isValidBuildComponentList(orderComponents)) {
            throw new ExcepcionValorInvalido(MESSAGE_COMPONENTS_INVALID_FOR_BUILD);
        }

        // General setting fields
        this.id = id;
        this.buildService = buildService;
        this.buyerData = buyerData;

        // set variables internally
        this.placementDate = LocalDate.now();
        this.status = STATUS_PROCESSING;

        // required initialized fields
        this.orderPrice = new BigDecimal(0);
        this.orderComponents = new ArrayList<>();

        // Calculated fields

        // copy order components and calculate total price
        orderComponents.forEach(component -> {
            this.orderComponents.add(component);
            this.orderPrice = this.orderPrice.add(component.getPrice());
        });

        // additional price for building service
        if (this.buildService) {
            BigDecimal buildingServicePrice = this.orderPrice.multiply(BigDecimal.valueOf(0.1));
            this.orderPrice = this.orderPrice.add(buildingServicePrice);
        }

        // calculate shipping date
        int daysForShipping = this.buildService ? 5 : 2;
        this.shippingDate = addDaysWithoutWeekends(this.placementDate, daysForShipping);

        // calculate delivered date
        this.deliveredDate = addDaysWithoutSundays(shippingDate, 1);

        // generate tracking code
        this.trackingCode = UUID.randomUUID().toString();
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * TODO: eliminar esta funcion si no se usa
     * Update to next status.
     */
    public void setNextStatus() {

        switch (status) {
            case STATUS_PROCESSING:
                status = STATUS_SHIPPED;
                break;
            case STATUS_SHIPPED:
                status = STATUS_DELIVERED;
                break;
        }
    }

    /**
     * //TODO: eliminar si no se usa
     * Calculate the delivered date if the order was delivered.
     *
     * @return  the calculated delivered date or null if not delivered
     */
    public LocalDate getCalculatedDeliveredDate() {

        if (status.equals(STATUS_DELIVERED)) {
            return addDaysWithoutSundays(shippingDate, 1);
        }

        return null;
    }

    /**
     * Validate if there are all type of component in the list.
     *
     * NOTE: this method don't validate if there are duplicate components.
     *
     * @param componentList
     * @return
     */
    private boolean isValidBuildComponentList(List<Component> componentList) {

        Set<String> foundTypes = new HashSet<>();

        componentList.forEach(component -> foundTypes.add(component.getType()));

        return foundTypes.size() == Component.TYPE_LIST.size();
    }

    /**
     * Adds a number of days to a date without counting weekends.
     *
     * @param initDate
     * @param numDays
     * @return
     */
    private LocalDate addDaysWithoutWeekends(LocalDate initDate, int numDays) {

        LocalDate nextDate = initDate;

        while (numDays > 0) {

            nextDate = nextDate.plusDays(1);

            if (!nextDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !nextDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                numDays--;
            }
        }

        return nextDate;
    }

    /**
     * Adds a number of days to a date without counting sundays.
     *
     * @param initDate
     * @param numDays
     * @return
     */
    private LocalDate addDaysWithoutSundays(LocalDate initDate, int numDays) {

        LocalDate nextDate = initDate;

        while (numDays > 0) {

            nextDate = nextDate.plusDays(1);

            if (!nextDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                numDays--;
            }
        }

        return nextDate;
    }

}
