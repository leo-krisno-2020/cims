package com.jpmchase.cims.service;

import com.jpmchase.cims.constant.OrderStatus;
import com.jpmchase.cims.constant.PaymentMethod;
import com.jpmchase.cims.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void whenFindAll_thenReturnAllOrders() {
        orderService.add(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        List<Order> inventories = orderService.findAll();
        assertFalse(inventories.isEmpty());
        orderService.clearAll();
    }

    @Test
    public void whenFindById_thenReturnOrder() {
        orderService.add(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        Order order = orderService.findById(1);
        assertEquals(order.getId(), 1);
        assertNotEquals(order.getCarId(), 0);
        orderService.clearAll();
    }

    @Test
    public void whenFindByCarId_thenReturnOrder() {
        orderService.add(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        List<Order> orders = orderService.findByCarId(1);
        assertEquals(orders.size(), 1);
        orderService.clearAll();
    }

    @Test
    public void whenAdd_thenReturnSavedNewOrder() {
        Order savedOrder = orderService.add(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        assertNotNull(savedOrder);
        assertEquals(savedOrder.getId(), 1);
        assertNotEquals(savedOrder.getCarId(), 0);
        orderService.clearAll();
    }

    @Test
    public void whenUpdate_thenReturnSavedExistingOrder() {
        Order newOrder = orderService.add(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        Order order = new Order(newOrder.getId(), 1, "Leo", PaymentMethod.CASH, 2, new BigDecimal(500000), OrderStatus.PENDING, "Hendra", new Date(), null, null);
        Order savedOrder = orderService.update(order);
        assertNotNull(savedOrder);
        assertNotEquals(savedOrder.getId(), 0);
        assertNotEquals(savedOrder.getCarId(), 0);
        orderService.clearAll();
    }

    @Test
    public void whenClearAll_thenOrdersEmpty() {
        orderService.clearAll();
        List<Order> inventories = orderService.findAll();
        assertTrue(inventories.isEmpty());
    }

    @Test
    public void whenDelete_thenSuccess() {
        Order savedOrder = orderService.add(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        orderService.delete(savedOrder);
    }
    
}
