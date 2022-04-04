package com.jpmchase.cims.service;

import com.jpmchase.cims.dao.InventoryDAOImpl;
import com.jpmchase.cims.dao.OrderDAO;
import com.jpmchase.cims.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> findAll() {
        LOGGER.info("findAll is called");
        List<Order> orders = orderDAO.findAll();
        return orders;
    }

    @Override
    public Order findById(int id) {
        LOGGER.info("findById is called: {}", id);
        Order order = orderDAO.findyById(id);
        return order;
    }

    @Override
    public List<Order> findByCarId(int carId) {
        LOGGER.info("findByCarId is called: {}", carId);
        List<Order> orders = orderDAO.findByCarId(carId);
        return orders;
    }

    @Override
    public Order add(Order order) {
        LOGGER.info("add is called: {}", order);
        order.setId(0);
        return orderDAO.save(order);
    }

    @Override
    public Order update(Order order) {
        LOGGER.info("update is called: {}", order);
        return orderDAO.save(order);
    }

    @Override
    public void delete(Order order) {
        LOGGER.info("delete is called: {}", order);
        orderDAO.deleteById(order.getId());
    }

    @Override
    public void clearAll() {
        LOGGER.info("clearAll is called");
        orderDAO.clearAll();
    }

}
