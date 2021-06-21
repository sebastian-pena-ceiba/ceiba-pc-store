package com.ceiba.pcstore.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyerDataTest {

    /**
     * Valid buyer data creation with null required data.
     */
    @Test
    public void validCreateBuyerData_WithNullRequiredDataTest() {

        // arrange
        String exceptionExpectedMessage = "The buyer name field is required";

        // act - assert
        Exception exception = assertThrows(Exception.class, () -> {

        });
        assertEquals(exceptionExpectedMessage, exception.getMessage());
    }

    /**
     * Valid buyer data creation with correct data.
     */
    @Test
    public void validCreateBuyerData_WithAllRequiredDataTest() {

        // arrange - act - assert
        assertDoesNotThrow(() -> {
            BuyerData buyerData = new BuyerData(
                    1L,"Pepito Perez", 1098765432, "example@example.com", "3210987654", "av 1 cra 2"
            );
        });
    }

}
