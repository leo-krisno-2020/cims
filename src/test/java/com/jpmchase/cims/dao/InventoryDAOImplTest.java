package com.jpmchase.cims.dao;

import com.jpmchase.cims.constant.*;
import com.jpmchase.cims.entity.Car;
import com.jpmchase.cims.entity.Inventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryDAOImplTest {

    @Autowired
    private InventoryDAO inventoryDAO;

    @Test
    public void whenFindAll_thenReturnAllInventories() {
        List<Inventory> inventories = inventoryDAO.findAll();
        assertFalse(inventories.isEmpty());
    }

    @Test
    public void whenFindById_thenReturnInventory() {
        Inventory inventory = inventoryDAO.findyById(1);
        assertEquals(inventory.getId(), 1);
        assertNotNull(inventory.getCar());
    }

    @Test
    public void whenFindById_thenReturnNull() {
        Inventory inventory = inventoryDAO.findyById(100);
        assertNull(inventory);
    }

    @Test
    public void whenFindByCarId_thenReturnInventory() {
        Inventory inventory = inventoryDAO.findByCarId(1);
        assertEquals(inventory.getId(), 1);
        assertNotNull(inventory.getCar());
    }

    @Test
    public void whenFindByCarId_thenReturnNull() {
        Inventory inventory = inventoryDAO.findByCarId(100);
        assertNull(inventory);
    }

    @Test
    public void whenFindByCarMakeAndModel_thenReturnInventory() {
        Inventory inventory = inventoryDAO.findByCarMakeAndModel(Make.HONDA, "Civic");
        assertNotNull(inventory.getCar());
    }

    @Test
    public void whenFindByCarMakeAndModel_thenReturnNull() {
        Inventory inventory = inventoryDAO.findByCarMakeAndModel(Make.HONDA, "");
        assertNull(inventory);
    }

    @Test
    public void whenSave_thenReturnSavedNewInventoryWithId1() {
        inventoryDAO.clearAll();
        Inventory inventory = new Inventory(0, new Car(0, Make.HONDA, "Accord 3.4 Asia", FuelType.PETROL, Transmission.MANUAL, BodyType.LUXURY_SEDAN, Color.GREEN, Seat.SEAT_5), new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);
        Inventory savedInventory = inventoryDAO.save(inventory);
        assertNotNull(savedInventory.getCar());
        ((InventoryDAOImpl)inventoryDAO).loadData();
    }

    @Test
    public void whenSave_thenReturnSavedNewInventoryWithIdNot1() {
        Inventory inventory = new Inventory(0, new Car(0, Make.HONDA, "Accord 3.4 Asia", FuelType.PETROL, Transmission.MANUAL, BodyType.LUXURY_SEDAN, Color.GREEN, Seat.SEAT_5), new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);
        Inventory savedInventory = inventoryDAO.save(inventory);
        assertNotNull(savedInventory.getCar());
    }

    @Test
    public void whenSave_thenReturnNullNewInventory1() {
        Inventory savedInventory = inventoryDAO.save(new Inventory());
        assertNull(savedInventory);
    }

    @Test
    public void whenSave_thenReturnNullNewInventory2() {
        Inventory inventory = new Inventory(0, new Car(1, Make.MERCEDES_BENZ, "C-Class Saloon Mild Hybrid", FuelType.PETROL_ELECTRIC, Transmission.AUTOMATIC, BodyType.LUXURY_SEDAN, Color.BLACK, Seat.SEAT_5), new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);
        Inventory savedInventory = inventoryDAO.save(inventory);
        assertNull(savedInventory);
    }

    @Test
    public void whenSave_thenReturnSavedExistingInventory() {
        Inventory inventory = new Inventory(1, new Car(2, Make.HONDA, "Accord 3.4 Asia", FuelType.PETROL, Transmission.MANUAL, BodyType.LUXURY_SEDAN, Color.GREEN, Seat.SEAT_5), new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);
        Inventory savedInventory = inventoryDAO.save(inventory);
        assertNotNull(savedInventory.getCar());
    }

    @Test
    public void whenSave_thenReturnNullExistingInventory1() {
        Inventory inventory = new Inventory(100, new Car(2, Make.HONDA, "Accord 3.4 Asia", FuelType.PETROL, Transmission.MANUAL, BodyType.LUXURY_SEDAN, Color.GREEN, Seat.SEAT_5), new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);
        Inventory savedInventory = inventoryDAO.save(inventory);
        assertNull(savedInventory);
    }

    @Test
    public void whenSave_thenReturnNullExistingInventory2() {
        Inventory inventory = new Inventory(1, null, new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);
        Inventory savedInventory = inventoryDAO.save(inventory);
        assertNull(savedInventory);
    }

    @Test
    public void whenSave_thenReturnNullExistingInventory3() {
        Inventory inventory = new Inventory(1, new Car(2, Make.HONDA, "Accord 8.8 Asia", FuelType.PETROL, Transmission.MANUAL, BodyType.LUXURY_SEDAN, Color.GREEN, Seat.SEAT_5), new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null);
        Inventory savedInventory = inventoryDAO.save(inventory);
        assertNull(savedInventory);
    }

    @Test
    public void whenClearAll_thenInventoriesEmpty() {
        inventoryDAO.clearAll();
        List<Inventory> inventories = inventoryDAO.findAll();
        assertTrue(inventories.isEmpty());
        ((InventoryDAOImpl)inventoryDAO).loadData();
    }

    @Test
    public void whenDeleteById1_thenSuccess() {
        inventoryDAO.deleteById(28);
    }

    @Test
    public void whenDeleteById2_thenSuccess() {
        inventoryDAO.deleteById(29);
    }

}
