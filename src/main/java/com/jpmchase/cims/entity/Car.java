package com.jpmchase.cims.entity;

import com.jpmchase.cims.constant.*;

public class Car {

    private int id;

    private Make make;

    private String model;

    private FuelType fuelType;

    private Transmission transmission;

    private BodyType bodyType;

    private Color color;

    private Seat seat;

    public Car() {
    }

    public Car(int id, Make make, String model, FuelType fuelType, Transmission transmission, BodyType bodyType, Color color, Seat seat) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.bodyType = bodyType;
        this.color = color;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", make=" + make + ", model=" + model + ", fuelType=" + fuelType +
                ", transmission=" + transmission + ", bodyType=" + bodyType + ", color=" + color + ", seat=" + seat + "]";
    }

}
