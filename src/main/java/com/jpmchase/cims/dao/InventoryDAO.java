package com.jpmchase.cims.dao;

import com.jpmchase.cims.constant.Make;
import com.jpmchase.cims.entity.Inventory;

import java.util.List;

public interface InventoryDAO {
	
	public List<Inventory> findAll();
	
	public Inventory findyById(int id);

	public Inventory findByCarId(int carId);

	public Inventory findByCarMakeAndModel(Make make, String model);
	
	public Inventory save(Inventory inventory);

	public void clearAll();

	public void deleteById(int id);

}
