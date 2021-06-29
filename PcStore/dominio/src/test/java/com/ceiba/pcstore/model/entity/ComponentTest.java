package com.ceiba.pcstore.model.entity;

import com.ceiba.pcstore.testdatabuilder.ComponentTestDataBuilder;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentTest {

    /**
     * Test the exception when the type is not right.
     */
    @Test
    public void createComponentWithInvalidTypeTest() {

        // arrange
        ComponentTestDataBuilder componentBuilder = new ComponentTestDataBuilder().withType("No valid");
        String expectedMessage = "The component type is not valid";

        // act - assert
        Exception exception = assertThrows(Exception.class, componentBuilder::build);
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test the exception when the prices is negative.
     */
    @Test
    public void createComponentWithInvalidPriceTest() {

        ComponentTestDataBuilder componentBuilder = new ComponentTestDataBuilder().withPrice(BigDecimal.valueOf(-10));
        String expectedMessage = "The price must be bigger than 0";

        Exception exception = assertThrows(Exception.class, componentBuilder::build);
        assertEquals(expectedMessage, exception.getMessage());
    }

}
