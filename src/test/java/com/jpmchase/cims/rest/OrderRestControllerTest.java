package com.jpmchase.cims.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmchase.cims.constant.*;
import com.jpmchase.cims.entity.Car;
import com.jpmchase.cims.entity.Inventory;
import com.jpmchase.cims.entity.Order;
import com.jpmchase.cims.service.InventoryService;
import com.jpmchase.cims.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderRestController.class)
public class OrderRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private InventoryService inventoryService;

    @Test
    public void whenFindAll_thenReturnAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));

        doReturn(orders).when(orderService).findAll();

        mvc.perform(get("/api/v1/order").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(orders.get(0).getId())));
    }

    @Test
    public void whenFindById_thenReturnOrder() throws Exception {
        Order order = new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null);

        doReturn(order).when(orderService).findById(1);

        mvc.perform(get("/api/v1/order/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(order.getId())));
    }

    @Test
    public void whenAddOrder_thenReturnSavedNewOrder() throws Exception {
        Car car = new Car(1, Make.MERCEDES_BENZ, "C-Class Saloon Mild Hybrid", FuelType.PETROL_ELECTRIC, Transmission.AUTOMATIC, BodyType.LUXURY_SEDAN, Color.BLACK, Seat.SEAT_5);
        Inventory inventory = new Inventory(1, car, new BigDecimal(261888), 2, "SYSTEM", new Date(), null, null);

        Date date = new Date();
        Order order1 = new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", date, null, null);
        Order order2 = new Order(1, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", date, null, null);

        String jsonContent = new ObjectMapper().writeValueAsString(order1);

        doReturn(inventory).when(inventoryService).findByCarId(1);
        doReturn(Collections.emptyList()).when(orderService).findByCarId(1);
        doReturn(order2).when(orderService).add(any());

        mvc.perform(post("/api/v1/order").content(jsonContent).param("userId", "Leo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(order2.getId())));

        verify(inventoryService, times(1)).findByCarId(1);
        verify(orderService, times(1)).findByCarId(1);
        verify(orderService, times(1)).add(any());

    }

    @Test
    public void whenUpdateOrder_thenReturnSavedExistingOrder() throws Exception {
        Car car = new Car(1, Make.MERCEDES_BENZ, "C-Class Saloon Mild Hybrid", FuelType.PETROL_ELECTRIC, Transmission.AUTOMATIC, BodyType.LUXURY_SEDAN, Color.BLACK, Seat.SEAT_5);
        Inventory inventory = new Inventory(1, car, new BigDecimal(261888), 2, "SYSTEM", new Date(), null, null);

        Order order = new Order(1, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null);

        String jsonContent = new ObjectMapper().writeValueAsString(order);

        doReturn(order).when(orderService).findById(1);
        doReturn(inventory).when(inventoryService).findByCarId(1);
        doReturn(Collections.emptyList()).when(orderService).findByCarId(1);
        doReturn(order).when(orderService).update(any());

        mvc.perform(put("/api/v1/order").content(jsonContent).param("userId", "Leo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(order.getId())));

        verify(orderService, times(1)).findById(1);
        verify(inventoryService, times(1)).findByCarId(1);
        verify(orderService, times(1)).findByCarId(1);
        verify(orderService, times(1)).update(any());

    }

    @Test
    public void whenDeleteOrder_thenReturnSuccess() throws Exception {
        Order order = new Order(1, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null);

        String jsonContent = new ObjectMapper().writeValueAsString(order);

        doReturn(order).when(orderService).findById(1);
        doNothing().when(orderService).delete(any());

        mvc.perform(delete("/api/v1/order/1").param("userId", "Leo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Order 1 is deleted"));

        verify(orderService, times(1)).findById(1);
        verify(orderService, times(1)).delete(any());

    }    

}