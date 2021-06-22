package com.ceiba.pcstore.controller;

import com.ceiba.ApplicationMock;
import com.ceiba.pcstore.command.OrderCommand;
import com.ceiba.pcstore.service.testdatabuilder.OrderCommandTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createOrderTest() throws Exception {

        OrderCommand orderCommand = new OrderCommandTestDataBuilder().build();

        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderCommand))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.id", is(1)));
    }

    @Test
    public void getOrderTest() throws Exception {

        OrderCommand orderCommand = new OrderCommandTestDataBuilder().build();

        MvcResult result = mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderCommand))
        ).andExpect(status().isOk())
                .andReturn();

        String jsonResult = result.getResponse().getContentAsString();

        String trackingCode = JsonPath.parse(jsonResult).read("$.valor.trackingCode", String.class);
        Integer resultId = JsonPath.parse(jsonResult).read("$.valor.id", Integer.class);

        mockMvc.perform(get("/orders/" + trackingCode).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(resultId)));
    }

}
