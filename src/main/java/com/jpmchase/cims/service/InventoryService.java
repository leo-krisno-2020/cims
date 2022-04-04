package com.jpmchase.cims.service;

import java.util.List;

import com.jpmchase.cims.constant.Make;
import com.jpmchase.cims.entity.Inventory;

public interface InventoryService {
	
	public List<Inventory> findAll();
	
	public Inventory findById(int id);

	public Inventory findByCarId(int carId);

	public Inventory findByCarMakeAndModel(Make make, String model);
	
	public Inventory add(Inventory inventory);

	public Inventory update(Inventory inventory);

	public void delete(Inventory inventory);

	public void clearAll();

}
