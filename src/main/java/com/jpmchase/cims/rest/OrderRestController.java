package com.jpmchase.cims.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jpmchase.cims.constant.OrderStatus;
import com.jpmchase.cims.entity.Inventory;
import com.jpmchase.cims.entity.Order;
import com.jpmchase.cims.service.InventoryService;
import com.jpmchase.cims.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

    private OrderService orderService;
    private InventoryService inventoryService;

    @Autowired
    public OrderRestController(OrderService orderService, InventoryService inventoryService) {
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/order")
    public List<Order> findAll() {
        LOGGER.info("findAll is called");
        List<Order> orders = orderService.findAll();
        return orders;
    }

    @GetMapping("/order/{orderId}")
    public Order findOrderById(@PathVariable int orderId) {
        LOGGER.info("findOrderById is called: {}", orderId);
        Order order = orderService.findById(orderId);
        if (order == null) {
            throw new RuntimeException("Order id " + orderId + " cannot be found");
        }
        return order;
    }

    @PostMapping("/order")
    public Order addOrder(@RequestBody Order order, @RequestParam String userId) {
        LOGGER.info("addOrder is called: {} and {}", order, userId);

        Order savedOrder = null;

        if (order.getId() != 0 || order.getStatus() == OrderStatus.PAID) {
            throw new RuntimeException("Some parameters are invalid");
        }
        validateOrderParam(order);

        Inventory inventory = inventoryService.findByCarId(order.getCarId());
        if (inventory == null) {
            throw new RuntimeException("Car id " + order.getCarId() + " cannot be found");
        }

        List<Order> orders = orderService.findByCarId(order.getCarId());
        int orderedQuantity = orders.stream().mapToInt(x -> x.getQuantity()).sum();
        if (inventory.getAvailableStock() - orderedQuantity < order.getQuantity()) {
            throw new RuntimeException("Available stock of Car " + order.getCarId() + " is not able to meet the requested quantity");
        }

        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(inventory.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        order.setCreatedBy(userId);
        order.setCreatedDate(new Date());
        order.setUpdatedBy(null);
        order.setUpdatedDate(null);

        savedOrder = orderService.add(order);

        return savedOrder;
    }

    @PutMapping("/order")
    public Order updateOrder(@RequestBody Order order, @RequestParam String userId) {
        LOGGER.info("updateOrder is called: {} and {}", order, userId);

        Order savedOrder = null;

        if (order.getId() == 0 || order.getStatus() == null) {
            throw new RuntimeException("Some parameters are invalid");
        }
        validateOrderParam(order);

        Order existingOrder = orderService.findById(order.getId());
        if (existingOrder == null) {
            throw new RuntimeException("Order id " + order.getId() + " cannot be found");
        } else if (existingOrder.getStatus() == OrderStatus.PAID) {
            throw new RuntimeException("Order " + order.getId() + " cannot be updated as it is already " + existingOrder.getStatus());
        }

        Inventory inventory = inventoryService.findByCarId(order.getCarId());
        if (inventory == null) {
            throw new RuntimeException("Car id " + order.getCarId() + " cannot be found");
        }
        List<Order> orders = orderService.findByCarId(order.getCarId());
        int orderedQuantity = orders.stream().filter(x -> x.getId() != order.getId()).mapToInt(x -> x.getQuantity()).sum();

        if (inventory.getAvailableStock() - orderedQuantity < order.getQuantity()) {
            throw new RuntimeException("Available stock of Car " + order.getCarId() + " is not able to meet the requested quantity");
        }

        order.setTotalPrice(inventory.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        order.setUpdatedBy(userId);
        order.setUpdatedDate(new Date());

        savedOrder = orderService.update(order);

        if (savedOrder.getStatus() == OrderStatus.PAID) {
            inventory.setAvailableStock(inventory.getAvailableStock() - order.getQuantity());
            inventory.setUpdatedBy(userId);
            inventory.setUpdatedDate(new Date());
            inventoryService.update(inventory);
        }

        return savedOrder;
    }

    @DeleteMapping("/order/{orderId}")
    public String deleteOrder(@PathVariable int orderId, @RequestParam String userId) {
        LOGGER.info("deleteOrder is called: {} and {}", orderId, userId);

        Order order = orderService.findById(orderId);
        if (order == null) {
            throw new RuntimeException("Order id " + orderId + " cannot be found");
        } else if (order.getStatus() == OrderStatus.PAID) {
            throw new RuntimeException("Order " + order.getId() + " cannot be deleted as it is already " + order.getStatus());
        }
        orderService.delete(order);
        return "Order " + orderId + " is deleted";
    }

    private void validateOrderParam(Order order) {
        LOGGER.info("validateCarParams is called: {}", order);

        if (order.getCarId() == 0 || StringUtils.isBlank(order.getBuyerName()) || order.getQuantity() <= 0 || order.getPaymentMethod() == null) {
            throw new RuntimeException("Some parameters are invalid");
        }
    }

}