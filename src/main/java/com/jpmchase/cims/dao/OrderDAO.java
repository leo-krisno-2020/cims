package com.jpmchase.cims.dao;

import com.jpmchase.cims.entity.Order;

import java.util.List;

public interface OrderDAO {

    public List<Order> findAll();

    public Order findyById(int id);

    public List<Order> findByCarId(int carId);

    public Order save(Order order);

    public void clearAll();

    public void deleteById(int id);

}
