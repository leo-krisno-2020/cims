package com.jpmchase.cims.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jpmchase.cims.constant.*;
import com.jpmchase.cims.entity.Car;
import com.jpmchase.cims.entity.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InventoryDAOImpl implements InventoryDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryDAOImpl.class);

	private List<Car> cars;
	private List<Inventory> inventories;

	/**
	 * This method is populate some Car objects and Inventory objects
	 */
	@PostConstruct
	public void loadData() {
		cars = new ArrayList<>();
		cars.add(new Car(1, Make.MERCEDES_BENZ, "C-Class Saloon Mild Hybrid", FuelType.PETROL_ELECTRIC, Transmission.AUTOMATIC, BodyType.LUXURY_SEDAN, Color.BLACK, Seat.SEAT_5));
		cars.add(new Car(2, Make.SUZUKI, "Swift Sport Mild Hybrid", FuelType.PETROL_ELECTRIC, Transmission.MANUAL, BodyType.HATCHBACK, Color.WHITE, Seat.SEAT_5));
		cars.add(new Car(3, Make.TESLA, "Model 3 Electric", FuelType.ELECTRIC, Transmission.AUTOMATIC, BodyType.MID_SIZED_SEDAN, Color.BROWN, Seat.SEAT_5));
		cars.add(new Car(4, Make.BYD, "e6 Electric", FuelType.ELECTRIC, Transmission.AUTOMATIC, BodyType.HATCHBACK, Color.GRAY, Seat.SEAT_5));
		cars.add(new Car(5, Make.BMW, "3 Series Sedan", FuelType.PETROL, Transmission.AUTOMATIC, BodyType.LUXURY_SEDAN, Color.SILVER, Seat.SEAT_5));
		cars.add(new Car(6, Make.MERCEDES_BENZ, "E-Class Saloon Diesel", FuelType.DIESEL, Transmission.AUTOMATIC, BodyType.LUXURY_SEDAN, Color.BLUE, Seat.SEAT_5));
		cars.add(new Car(7, Make.BMW, "i4 Gran Coupe Electric", FuelType.ELECTRIC, Transmission.AUTOMATIC, BodyType.LUXURY_SEDAN, Color.RED, Seat.SEAT_5));
		cars.add(new Car(8, Make.HYUNDAI, "Avante", FuelType.PETROL, Transmission.AUTOMATIC, BodyType.MID_SIZED_SEDAN, Color.GREEN, Seat.SEAT_5));
		cars.add(new Car(9, Make.HONDA, "Civic", FuelType.PETROL, Transmission.AUTOMATIC, BodyType.MID_SIZED_SEDAN, Color.ORANGE, Seat.SEAT_5));
		cars.add(new Car(10, Make.MAZDA, "Axela Diesel", FuelType.DIESEL, Transmission.AUTOMATIC, BodyType.MID_SIZED_SEDAN, Color.BEIGE, Seat.SEAT_5));
		cars.add(new Car(11, Make.HONDA, "Civic Hatchback", FuelType.PETROL, Transmission.MANUAL, BodyType.HATCHBACK, Color.BLACK, Seat.SEAT_5));
		cars.add(new Car(12, Make.HONDA, "Vezel 1.5 G", FuelType.PETROL, Transmission.AUTOMATIC, BodyType.HATCHBACK, Color.WHITE, Seat.SEAT_5));
		cars.add(new Car(13, Make.TOYOTA, "Sienta 1.5", FuelType.PETROL, Transmission.AUTOMATIC, BodyType.HATCHBACK, Color.BROWN, Seat.SEAT_7));
		cars.add(new Car(14, Make.HYUNDAI, "Ioniq", FuelType.ELECTRIC, Transmission.AUTOMATIC, BodyType.HATCHBACK, Color.GRAY, Seat.SEAT_5));
		cars.add(new Car(15, Make.ALFA, "Romeo Giulia", FuelType.PETROL, Transmission.AUTOMATIC, BodyType.SPORTS, Color.SILVER, Seat.SEAT_5));
		cars.add(new Car(16, Make.MERCEDES_BENZ, "E-Class Coupe Diesel", FuelType.DIESEL, Transmission.AUTOMATIC, BodyType.SPORTS, Color.BLUE, Seat.SEAT_5));
		cars.add(new Car(17, Make.AUDI, "RS e-tron GT Electric", FuelType.ELECTRIC, Transmission.AUTOMATIC, BodyType.SPORTS, Color.RED, Seat.SEAT_5));
		cars.add(new Car(18, Make.MG, "5 Electric", FuelType.ELECTRIC, Transmission.AUTOMATIC, BodyType.STATION_WAGON, Color.GREEN, Seat.SEAT_5));
		cars.add(new Car(19, Make.HYUNDAI, "i30 Wagon", FuelType.PETROL, Transmission.AUTOMATIC, BodyType.STATION_WAGON, Color.ORANGE, Seat.SEAT_5));
		cars.add(new Car(20, Make.HONDA, "Shuttle 1.5 G Honda Sensing", FuelType.PETROL, Transmission.AUTOMATIC, BodyType.MPV, Color.BEIGE, Seat.SEAT_5));
		cars.add(new Car(21, Make.KIA, "Carnival Diesel", FuelType.DIESEL, Transmission.AUTOMATIC, BodyType.MPV, Color.BLACK, Seat.SEAT_8));
		cars.add(new Car(22, Make.BYD, "M3e Electric", FuelType.ELECTRIC, Transmission.AUTOMATIC, BodyType.MPV, Color.WHITE, Seat.SEAT_7));
		cars.add(new Car(23, Make.MERCEDES_BENZ, "EQV Electric", FuelType.ELECTRIC, Transmission.AUTOMATIC, BodyType.MPV, Color.BROWN, Seat.SEAT_7));
		cars.add(new Car(24, Make.HONDA, "HR-V", FuelType.PETROL, Transmission.AUTOMATIC, BodyType.SUV, Color.GRAY, Seat.SEAT_5));
		cars.add(new Car(25, Make.SSANGYONG, "Tivoli Diesel", FuelType.DIESEL, Transmission.AUTOMATIC, BodyType.SUV, Color.SILVER, Seat.SEAT_5));
		cars.add(new Car(26, Make.MG, "ZS Electric", FuelType.ELECTRIC, Transmission.AUTOMATIC, BodyType.SUV, Color.BLUE, Seat.SEAT_5));
		cars.add(new Car(27, Make.PORSCHE, "911 Carrera S 3", FuelType.PETROL, Transmission.MANUAL, BodyType.SPORTS, Color.RED, Seat.SEAT_4));
		cars.add(new Car(28, Make.HONDA, "Accord 2.4 Euro S", FuelType.PETROL, Transmission.MANUAL, BodyType.LUXURY_SEDAN, Color.GREEN, Seat.SEAT_5));

		inventories = new ArrayList<>();
		inventories.add(new Inventory(1, cars.get(0), new BigDecimal(261888), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(2, cars.get(1), new BigDecimal(136900), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(3, cars.get(2), new BigDecimal(113245), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(4, cars.get(3), new BigDecimal(142888), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(5, cars.get(4), new BigDecimal(258888), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(6, cars.get(5), new BigDecimal(245988), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(7, cars.get(6), new BigDecimal(311888), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(8, cars.get(7), new BigDecimal(120299), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(9, cars.get(8), new BigDecimal(148999), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(10, cars.get(9), new BigDecimal(113800), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(11, cars.get(10), new BigDecimal(180000), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(12, cars.get(11), new BigDecimal(125800), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(13, cars.get(12), new BigDecimal(108588), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(14, cars.get(13), new BigDecimal(180888), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(15, cars.get(14), new BigDecimal(248000), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(16, cars.get(15), new BigDecimal(312388), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(17, cars.get(16), new BigDecimal(680940), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(18, cars.get(17), new BigDecimal(158888), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(19, cars.get(18), new BigDecimal(135999), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(20, cars.get(19), new BigDecimal(105800), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(21, cars.get(20), new BigDecimal(246999), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(22, cars.get(21), new BigDecimal(137888), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(23, cars.get(22), new BigDecimal(353988), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(24, cars.get(23), new BigDecimal(136999), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(25, cars.get(24), new BigDecimal(139888), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(26, cars.get(25), new BigDecimal(139888), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(27, cars.get(26), new BigDecimal(634911), 2, "SYSTEM", new Date(), null, null));
		inventories.add(new Inventory(28, cars.get(27), new BigDecimal(34800), 2, "SYSTEM", new Date(), null, null));
	}

	/**
	 * This method is to retrieve all the stored Inventory objects
	 * @return list of Inventory
	 */
	@Override
	public List<Inventory> findAll() {
		LOGGER.info("findAll is called");
		return inventories;
	}

	/**
	 * This method is to find particular Inventory object by id
	 * @param id
	 * @return Inventory object
	 */
	@Override
	public Inventory findyById(int id) {
		LOGGER.info("findyById is called: {}", id);
		return inventories.stream().filter(inventory -> inventory.getId() == id).findAny().orElse(null);
	}

	/**
	 * This method is to find particular Inventory object by Car id
	 * @param carId
	 * @return Inventory object
	 */
	@Override
	public Inventory findByCarId(int carId) {
		LOGGER.info("findByCarId is called: {}", carId);
		return inventories.stream().filter(inventory -> inventory.getCar().getId() == carId).findAny().orElse(null);
	}

	/**
	 * This method is to find particular Inventory object by Car make and model
	 * @param make
	 * @param model
	 * @return Inventory object
	 */
	@Override
	public Inventory findByCarMakeAndModel(Make make, String model) {
		LOGGER.info("findByCarMakeAndModel is called: {} and {}", make, model);
		return inventories.stream().filter(inventory -> inventory.getCar().getMake() == make && inventory.getCar().getModel().equalsIgnoreCase(model)).findAny().orElse(null);
	}

	/**
	 * This method is to find particular Car object by make and model
	 * @param make
	 * @param model
	 * @return Car object
	 */
	private Car findCarByCarMakeAndModel(Make make, String model) {
		LOGGER.info("findCarByCarMakeAndModel is called: {} and {}", make, model);
		return cars.stream().filter(car -> car.getMake() == make && car.getModel().equalsIgnoreCase(model)).findAny().orElse(null);
	}

	/**
	 * This method is to store particular Inventory object
	 * @param inventory
	 * @return Inventory object
	 */
	@Override
	public Inventory save(Inventory inventory) {
		LOGGER.info("save is called: {} and {}", inventory);
		Inventory returnInventory = null;
		if (inventory.getId() == 0) {
			if (inventory.getCar() != null) {
				Car existingCar = findCarByCarMakeAndModel(inventory.getCar().getMake(), inventory.getCar().getModel());
				if (existingCar == null) {
					Inventory lastInventory = inventories.stream().reduce((first,second) -> second).orElse(null);
					int lastInventoryId = lastInventory == null ? 0 : lastInventory.getId();
					inventory.setId(lastInventoryId+1);
					inventories.add(inventory);

					Car lastCar = cars.stream().reduce((first,second) -> second).orElse(null);
                    int lastCarId = lastCar == null ? 0 : lastCar.getId();
					inventory.getCar().setId(lastCarId+1);
					cars.add(inventory.getCar());

					returnInventory = inventory;
				}
			}
		} else {
			Inventory existingInventory = findyById(inventory.getId());
			if (existingInventory != null && inventory.getCar() != null) {
				Car existingCar = findCarByCarMakeAndModel(inventory.getCar().getMake(), inventory.getCar().getModel());
				if (existingCar != null) {
					existingCar.setMake(inventory.getCar().getMake());
					existingCar.setModel(inventory.getCar().getModel());
					existingCar.setFuelType(inventory.getCar().getFuelType());
					existingCar.setTransmission(inventory.getCar().getTransmission());
					existingCar.setBodyType(inventory.getCar().getBodyType());
					existingCar.setColor(inventory.getCar().getColor());
					existingCar.setSeat(inventory.getCar().getSeat());

					existingInventory.setCar(existingCar);
					existingInventory.setPrice(inventory.getPrice());
					existingInventory.setAvailableStock(inventory.getAvailableStock());
					existingInventory.setUpdatedBy(inventory.getUpdatedBy());
					existingInventory.setUpdatedDate(inventory.getUpdatedDate());

					returnInventory = existingInventory;
				}
			}
		}
		return returnInventory;
	}

	/**
	 * This method is to clear all stored Inventory objects
	 */
	@Override
	public void clearAll() {
		LOGGER.info("clearAll is called");
		inventories.clear();
		cars.clear();
	}

	/**
	 * This method is to delete particular Inventory object by id
	 * @param id
	 */
	@Override
	public void deleteById(int id) {
		LOGGER.info("deleteById is called: {}", id);
		inventories.removeIf(inventory -> inventory.getId() == id);
	}

}
