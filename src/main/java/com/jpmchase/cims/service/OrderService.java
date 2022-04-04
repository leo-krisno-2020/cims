package com.jpmchase.cims.service;

import com.jpmchase.cims.entity.Order;

import java.util.List;

public interface OrderService {

    public List<Order> findAll();

    public Order findById(int id);

    public List<Order> findByCarId(int carId);

    public Order add(Order order);

    public Order update(Order order);

    public void delete(Order order);

    public void clearAll();

}
