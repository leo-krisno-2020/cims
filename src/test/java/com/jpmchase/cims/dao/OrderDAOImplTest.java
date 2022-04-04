package com.jpmchase.cims.dao;

import com.jpmchase.cims.constant.*;
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
public class OrderDAOImplTest {

    @Autowired
    private OrderDAO orderDAO;

    @Test
    public void whenFindAll_thenReturnAllOrders() {
        orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        List<Order> inventories = orderDAO.findAll();
        assertFalse(inventories.isEmpty());
        orderDAO.clearAll();
    }

    @Test
    public void whenFindAll_thenReturnEmptyOrder() {
        List<Order> inventories = orderDAO.findAll();
        assertTrue(inventories.isEmpty());
    }

    @Test
    public void whenFindById_thenReturnOrder() {
        orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        Order order = orderDAO.findyById(1);
        assertEquals(order.getId(), 1);
        assertNotEquals(order.getCarId(), 0);
        orderDAO.clearAll();
    }

    @Test
    public void whenFindById_thenReturnNull() {
        orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        Order order = orderDAO.findyById(100);
        assertNull(order);
        orderDAO.clearAll();
    }

    @Test
    public void whenFindByCarId_thenReturnOrder() {
        orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        List<Order> orders = orderDAO.findByCarId(1);
        assertEquals(orders.size(), 1);
        // assertNotNull(order.getCarId());
        orderDAO.clearAll();
    }

    @Test
    public void whenFindByCarId_thenReturnNull() {
        orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        List<Order> orders = orderDAO.findByCarId(100);
        assertTrue(orders.isEmpty());
        orderDAO.clearAll();
    }

    @Test
    public void whenSave_thenReturnSavedNewOrderWithId1() {
        Order savedOrder = orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        assertNotNull(savedOrder);
        assertEquals(savedOrder.getId(), 1);
        assertNotEquals(savedOrder.getCarId(), 0);
        orderDAO.clearAll();
    }

    @Test
    public void whenSave_thenReturnSavedNewOrderWithIdNot1() {
        orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        Order savedOrder = orderDAO.save(new Order(0, 2, "Ethan", PaymentMethod.CREDIT_CARD, 1, new BigDecimal(350000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        assertNotNull(savedOrder);
        assertNotEquals(savedOrder.getId(), 1);
        assertNotEquals(savedOrder.getCarId(), 0);
        orderDAO.clearAll();
    }

    @Test
    public void whenSave_thenReturnSavedNewOrderWithIdNot1And2() {
        orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        orderDAO.save(new Order(0, 2, "Ethan", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        Order savedOrder = orderDAO.save(new Order(0, 3, "Monuk", PaymentMethod.CREDIT_CARD, 1, new BigDecimal(350000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        assertNotNull(savedOrder);
        assertNotEquals(savedOrder.getId(), 1);
        assertNotEquals(savedOrder.getCarId(), 0);
        orderDAO.clearAll();
    }

    @Test
    public void whenSave_thenReturnNullNewOrder1() {
        Order savedOrder = orderDAO.save(new Order());
        assertNull(savedOrder);
    }

    @Test
    public void whenSave_thenReturnNullNewOrder2() {
        Order savedOrder = orderDAO.save(new Order(0, 0, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        assertNull(savedOrder);
    }

    @Test
    public void whenSave_thenReturnSavedExistingOrder() {
        Order newOrder = orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        Order order = new Order(newOrder.getId(), 1, "Leo", PaymentMethod.CASH, 2, new BigDecimal(500000), OrderStatus.PENDING, "Hendra", new Date(), null, null);
        Order savedOrder = orderDAO.save(order);
        assertNotNull(savedOrder);
        assertNotEquals(savedOrder.getId(), 0);
        assertNotEquals(savedOrder.getCarId(), 0);
        orderDAO.clearAll();
    }

    @Test
    public void whenSave_thenReturnNullExistingOrder1() {
        Order newOrder = orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        Order order = new Order(newOrder.getId(), 0, "Leo", PaymentMethod.CASH, 2, new BigDecimal(500000), OrderStatus.PENDING, "Hendra", new Date(), null, null);
        Order savedOrder = orderDAO.save(order);
        assertNull(savedOrder);
        orderDAO.clearAll();
    }

    @Test
    public void whenSave_thenReturnNullExistingOrder2() {
        orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        Order order = new Order(0, 0, "Leo", PaymentMethod.CASH, 2, new BigDecimal(500000), OrderStatus.PENDING, "Hendra", new Date(), null, null);
        Order savedOrder = orderDAO.save(order);
        assertNull(savedOrder);
        orderDAO.clearAll();
    }

    @Test
    public void whenSave_thenReturnNullExistingOrder3() {
        Order order = new Order(1, 0, "Leo", PaymentMethod.CASH, 2, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null);
        Order savedOrder = orderDAO.save(order);
        assertNull(savedOrder);
    }

    @Test
    public void whenClearAll_thenInventoriesEmpty() {
        orderDAO.clearAll();
        List<Order> inventories = orderDAO.findAll();
        assertTrue(inventories.isEmpty());
    }

    @Test
    public void whenDeleteById1_thenSuccess() {
        Order savedOrder = orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        orderDAO.deleteById(1);
    }

    @Test
    public void whenDeleteById2_thenSuccess() {
        Order savedOrder = orderDAO.save(new Order(0, 1, "Leo", PaymentMethod.CASH, 1, new BigDecimal(250000), OrderStatus.PENDING, "Hendra", new Date(), null, null));
        orderDAO.deleteById(100);
        orderDAO.deleteById(1);
    }

}
