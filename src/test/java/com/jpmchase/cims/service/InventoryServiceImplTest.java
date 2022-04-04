package com.jpmchase.cims.service;

import com.jpmchase.cims.constant.*;
import com.jpmchase.cims.entity.Car;
import com.jpmchase.cims.entity.Inventory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventoryServiceImplTest {

    @Autowired
    private InventoryService inventoryService;

    @Test
    public void whenFindAll_thenReturnAllInventories() {
        List<Inventory> inventories = inventoryService.findAll();
        assertFalse(inventories.isEmpty());
    }

    @Test
    public void whenFindById_thenReturnInventory() {
        Inventory inventory = inventoryService.findById(1);
        assertEquals(inventory.getId(), 1);
        assertNotNull(inventory.getCar());
    }

    @Test
    public void whenFindByCarId_thenReturnInventory() {
        Inventory inventory = inventoryService.findByCarId(1);
        assertEquals(inventory.getId(), 1);
        assertNotNull(inventory.getCar());
    }

    @Test
    public void whenFindByCarMakeAndModel_thenReturnInventory() {
        Inventory inventory = inventoryService.findByCarMakeAndModel(Make.HONDA, "Civic");
        assertNotNull(inventory.getCar());
    }

    @Test
    public void whenAdd_thenReturnSavedNewInventory() {
        Inventory inventory = new Inventory(0, new Car(0, Make.HONDA, "Accord 3.4 Asia", FuelType.PETROL, Transmission.MANUAL, BodyType.LUXURY_SEDAN, Color.GREEN, Seat.SEAT_5), new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);
        Inventory savedInventory = inventoryService.add(inventory);
        assertNotNull(savedInventory.getCar());
    }

    @Test
    public void whenUpdate_thenReturnSavedExistingInventory() {
        Inventory inventory = new Inventory(10, new Car(2, Make.SUZUKI, "Swift Sport Mild Hybrid", FuelType.PETROL_ELECTRIC, Transmission.MANUAL, BodyType.HATCHBACK, Color.WHITE, Seat.SEAT_5), new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);
        Inventory savedInventory = inventoryService.update(inventory);
        assertNotNull(savedInventory.getCar());
    }

    @Test
    public void whenXClearAll_thenInventoriesEmpty() {
        inventoryService.clearAll();
        List<Inventory> inventories = inventoryService.findAll();
        assertTrue(inventories.isEmpty());
    }

    @Test
    public void whenDelete_thenSuccess() {
        Inventory inventory = inventoryService.findById(28);
        inventoryService.delete(inventory);
    }

}
