package com.jpmchase.cims.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jpmchase.cims.entity.Car;
import com.jpmchase.cims.entity.Inventory;
import com.jpmchase.cims.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class InventoryRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryRestController.class);

	private InventoryService inventoryService;

	@Autowired
	public InventoryRestController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/inventory")
	public List<Inventory> findAll() {
		LOGGER.info("findAll is called");
		List<Inventory> inventory = inventoryService.findAll();
		return inventory;
	}

	@GetMapping("/inventory/{inventoryId}")
	public Inventory findInventoryById(@PathVariable int inventoryId) {
		LOGGER.info("findInventoryById is called: {}", inventoryId);
		Inventory inventory = inventoryService.findById(inventoryId);
		if (inventory == null) {
			throw new RuntimeException("Inventory id " + inventoryId + " cannot be found");
		}
		return inventory;
	}

	@PostMapping("/inventory")
	public Inventory addInventory(@RequestBody Inventory inventory, @RequestParam String userId) {
		LOGGER.info("addInventory is called: {} and {}", inventory, userId);

		Inventory savedInventory = null;

		validateInventoryParams(inventory);
		validateCarParams(inventory.getCar());

		Inventory existingInventory = inventoryService.findByCarMakeAndModel(inventory.getCar().getMake(), inventory.getCar().getModel());
		if (existingInventory != null) {
			throw new RuntimeException("Inventory cannot be added as it exists in the system");
		}

		inventory.setCreatedBy(userId);
		inventory.setCreatedDate(new Date());
		inventory.setUpdatedBy(null);
		inventory.setUpdatedDate(null);
		savedInventory = inventoryService.add(inventory);
		System.out.println("savedInventory: " + savedInventory);

		return savedInventory;
	}

	@PutMapping("/inventory")
	public Inventory updateInventory(@RequestBody Inventory inventory, @RequestParam String userId) {
		LOGGER.info("updateInventory is called: {} and {}", inventory, userId);

		Inventory savedInventory = null;

		validateInventoryParams(inventory);
		validateCarParams(inventory.getCar());

		Inventory existingInventory = inventoryService.findById(inventory.getId());
		if (existingInventory == null) {
			throw new RuntimeException("Inventory id " + inventory.getId() + " cannot be found");
		}
		if (existingInventory.getCar().getMake() != inventory.getCar().getMake() || !existingInventory.getCar().getModel().equalsIgnoreCase(inventory.getCar().getModel())) {
			throw new RuntimeException("The make and model of Car is different");
		}

		inventory.setUpdatedBy(userId);
		inventory.setUpdatedDate(new Date());
		savedInventory = inventoryService.update(inventory);

		return savedInventory;
	}

	@DeleteMapping("/inventory/{inventoryId}")
	public String deleteInventory(@PathVariable int inventoryId, @RequestParam  String userId) {
		LOGGER.info("deleteInventory is called: {} and {}", inventoryId, userId);

		Inventory inventory = inventoryService.findById(inventoryId);
		if (inventory == null) {
			throw new RuntimeException("Inventory id " + inventoryId + " cannot be found");
		}
		inventoryService.delete(inventory);
		return "Inventory " + inventoryId + " is deleted";
	}

	private void validateInventoryParams(Inventory inventory) {
		LOGGER.info("validateInventoryParams is called: {}", inventory);

		if (inventory.getPrice() == null || inventory.getCar() == null || inventory.getPrice().compareTo(BigDecimal.ZERO) <= 0 || inventory.getAvailableStock() <= 0) {
			throw new RuntimeException("Some parameters are invalid");
		}
	}

	private void validateCarParams(Car car) {
		LOGGER.info("validateCarParams is called: {}", car);

		if (car.getMake() == null || car.getFuelType() == null || car.getTransmission() == null || car.getBodyType() == null || car.getColor() == null || car.getSeat() == null) {
			throw new RuntimeException("Some parameters for Car are invalid");
		}
	}

}
