package com.jpmchase.cims.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Inventory {

	private int id;

	private Car car;

	private BigDecimal price;

	private int availableStock;

	private String createdBy;

	private Date createdDate;

	private String updatedBy;

	private Date updatedDate;

	public Inventory() {
	}

	public Inventory(int id, Car car, BigDecimal price, int availableStock, String createdBy, Date createdDate, String updatedBy, Date updatedDate) {
		this.id = id;
		this.car = car;
		this.price = price;
		this.availableStock = availableStock;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", car=" + car + ", price=" + price + ", availableStock=" + availableStock +
				", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy +
				", updatedDate=" + updatedDate + "]";
	}

}
