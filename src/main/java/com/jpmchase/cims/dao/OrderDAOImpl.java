package com.jpmchase.cims.dao;

import com.jpmchase.cims.constant.OrderStatus;
import com.jpmchase.cims.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDAOImpl implements OrderDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDAOImpl.class);

    private List<Order> orders = new ArrayList<>();

    /**
     * This method is to retrieve all the stored Order objects
     * @return list of Order
     */
    @Override
    public List<Order> findAll() {
        LOGGER.info("findAll is called");
        return orders;
    }

    /**
     * This method is to find particular Order object by id
     * @param id
     * @return Order object
     */
    @Override
    public Order findyById(int id) {
        LOGGER.info("findyById is called: {}", id);
        return orders.stream().filter(inventory -> inventory.getId() == id).findAny().orElse(null);
    }

    /**
     * This method is to find particular Order object by Car id
     * @param carId
     * @return list of Order
     */
    @Override
    public List<Order> findByCarId(int carId) {
        LOGGER.info("findByCarId is called: {}", carId);
        return orders.stream().filter(inventory -> inventory.getCarId() == carId).collect(Collectors.toList());
    }

    /**
     * This method is to store particular Order object
     * @param order
     * @return Order object
     */
    @Override
    public Order save(Order order) {
        LOGGER.info("save is called: {}", order);

        Order returnOrder = null;
        if (order.getId() == 0) {
            if (order.getCarId() != 0) {
                Order lastOrder = orders.stream().reduce((first,second) -> second).orElse(null);
                int lastOrderId = lastOrder == null ? 0 : lastOrder.getId();
                order.setId(lastOrderId+1);
                order.setStatus(OrderStatus.PENDING);
                orders.add(order);
                returnOrder = order;
            }
        } else {
            Order existingOrder = findyById(order.getId());
            if (existingOrder != null && order.getCarId() != 0) {
                existingOrder.setBuyerName(order.getBuyerName());
                existingOrder.setCarId(order.getCarId());
                existingOrder.setPaymentMethod(order.getPaymentMethod());
                existingOrder.setQuantity(order.getQuantity());
                existingOrder.setTotalPrice(order.getTotalPrice());
                existingOrder.setStatus(order.getStatus());
                existingOrder.setUpdatedBy(order.getUpdatedBy());
                existingOrder.setUpdatedDate(order.getUpdatedDate());
                returnOrder = existingOrder;
            }
        }
        return returnOrder;
    }

    /**
     * This method is to clear all stored Order objects
     */
    @Override
    public void clearAll() {
        LOGGER.info("clearAll is called");
        orders.clear();
    }

    /**
     * This method is to delete particular Order object by id
     * @param id
     */
    @Override
    public void deleteById(int id) {
        LOGGER.info("deleteById is called: {}", id);
        orders.removeIf(order -> order.getId() == id);
    }

}
