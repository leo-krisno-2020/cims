package com.jpmchase.cims.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmchase.cims.constant.*;
import com.jpmchase.cims.entity.Car;
import com.jpmchase.cims.entity.Inventory;
import com.jpmchase.cims.service.InventoryService;
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
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryRestController.class)
public class InventoryRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InventoryService inventoryService;

    @Test
    public void whenFindAll_thenReturnAllInventories() throws Exception {
        Car car = new Car(1, Make.MERCEDES_BENZ, "C-Class Saloon Mild Hybrid", FuelType.PETROL_ELECTRIC, Transmission.AUTOMATIC, BodyType.LUXURY_SEDAN, Color.BLACK, Seat.SEAT_5);

        List<Inventory> inventories = new ArrayList<>();
        inventories.add(new Inventory(1, car, new BigDecimal(261888), 2, "SYSTEM", new Date(), null, null));

        doReturn(inventories).when(inventoryService).findAll();

        mvc.perform(get("/api/v1/inventory").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(inventories.get(0).getId())));

        verify(inventoryService, times(1)).findAll();
    }

    @Test
    public void whenFindById_thenReturnInventory() throws Exception {
        Car car = new Car(1, Make.MERCEDES_BENZ, "C-Class Saloon Mild Hybrid", FuelType.PETROL_ELECTRIC, Transmission.AUTOMATIC, BodyType.LUXURY_SEDAN, Color.BLACK, Seat.SEAT_5);

        Inventory inventory = new Inventory(1, car, new BigDecimal(261888), 2, "SYSTEM", new Date(), null, null);

        doReturn(inventory).when(inventoryService).findById(1);

        mvc.perform(get("/api/v1/inventory/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(inventory.getId())));

        verify(inventoryService, times(1)).findById(1);
    }

    @Test
    public void whenAddInventory_thenReturnSavedNewInventory() throws Exception {
        Car car = new Car(1, Make.HONDA, "Accord 3.4 Asia", FuelType.PETROL, Transmission.MANUAL, BodyType.LUXURY_SEDAN, Color.GREEN, Seat.SEAT_5);
        Date date = new Date();
        Inventory inventory1 = new Inventory(0, car, new BigDecimal(34800), 2, "SYSTEM", date, null, null);
        Inventory inventory2 = new Inventory(1, car, new BigDecimal(34800), 2, "SYSTEM", date, null, null);

        String jsonContent = new ObjectMapper().writeValueAsString(inventory1);

        doReturn(null).when(inventoryService).findByCarMakeAndModel(car.getMake(), car.getModel());
        doReturn(inventory2).when(inventoryService).add(any());

        mvc.perform(post("/api/v1/inventory").content(jsonContent).param("userId", "Leo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(inventory2.getId())));

        verify(inventoryService, times(1)).findByCarMakeAndModel(any(), any());
        verify(inventoryService, times(1)).add(any());

    }

    @Test
    public void whenUpdateInventory_thenReturnSavedExistingInventory() throws Exception {
        Car car = new Car(1, Make.HONDA, "Accord 3.4 Asia", FuelType.PETROL, Transmission.MANUAL, BodyType.LUXURY_SEDAN, Color.GREEN, Seat.SEAT_5);
        Inventory inventory = new Inventory(1, car, new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);

        String jsonContent = new ObjectMapper().writeValueAsString(inventory);

        doReturn(inventory).when(inventoryService).findById(1);
        doReturn(inventory).when(inventoryService).update(any());

        mvc.perform(put("/api/v1/inventory").content(jsonContent).param("userId", "Leo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(inventory.getId())));

        verify(inventoryService, times(1)).findById(1);
        verify(inventoryService, times(1)).update(any());

    }

    @Test
    public void whenDeleteInventory_thenReturnSuccess() throws Exception {
        Car car = new Car(1, Make.HONDA, "Accord 3.4 Asia", FuelType.PETROL, Transmission.MANUAL, BodyType.LUXURY_SEDAN, Color.GREEN, Seat.SEAT_5);
        Inventory inventory = new Inventory(1, car, new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);

        String jsonContent = new ObjectMapper().writeValueAsString(inventory);

        doReturn(inventory).when(inventoryService).findById(1);
        doNothing().when(inventoryService).delete(any());

        mvc.perform(delete("/api/v1/inventory/1").param("userId", "Leo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Inventory 1 is deleted"));

        verify(inventoryService, times(1)).findById(1);
        verify(inventoryService, times(1)).delete(any());

    }

}
