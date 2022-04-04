package com.jpmchase.cims.service;

import com.jpmchase.cims.constant.Make;
import com.jpmchase.cims.dao.InventoryDAO;
import com.jpmchase.cims.dao.InventoryDAOImpl;
import com.jpmchase.cims.entity.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private InventoryDAO inventoryDAO;

    @Autowired
    public InventoryServiceImpl(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    @Override
    public List<Inventory> findAll() {
        LOGGER.info("findAll is called");
        List<Inventory> inventories = inventoryDAO.findAll();
        return inventories;
    }

    @Override
    public Inventory findById(int id) {
        LOGGER.info("findById is called: {}", id);
        Inventory inventory = inventoryDAO.findyById(id);
        return inventory;
    }

    @Override
    public Inventory findByCarId(int carId) {
        LOGGER.info("findByCarId is called: {}", carId);
        Inventory inventory = inventoryDAO.findByCarId(carId);
        return inventory;
    }

    public Inventory findByCarMakeAndModel(Make make, String model) {
        LOGGER.info("findByCarMakeAndModel is called: {} and {}", make, model);
        Inventory inventory = inventoryDAO.findByCarMakeAndModel(make, model);
        return inventory;
    }

    @Override
    public Inventory add(Inventory inventory) {
        LOGGER.info("add is called: {}", inventory);
        inventory.setId(0);
        return inventoryDAO.save(inventory);
    }

    @Override
    public Inventory update(Inventory inventory) {
        LOGGER.info("update is called: {}", inventory);
        return inventoryDAO.save(inventory);
    }

    @Override
    public void delete(Inventory inventory) {
        LOGGER.info("delete is called: {}", inventory);
        inventoryDAO.deleteById(inventory.getId());
    }

    @Override
    public void clearAll() {
        LOGGER.info("clearAll is called");
        inventoryDAO.clearAll();
    }

}
