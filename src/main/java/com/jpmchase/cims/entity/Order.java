package com.jpmchase.cims.entity;

import com.jpmchase.cims.constant.OrderStatus;
import com.jpmchase.cims.constant.PaymentMethod;

import java.math.BigDecimal;
import java.util.Date;

public class Order {

    private int id;

    private int carId;

    private String buyerName;

    private PaymentMethod paymentMethod;

    private int quantity;

    private BigDecimal totalPrice;

    private OrderStatus status;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    public Order() {
    }

    public Order(int id, int carId, String buyerName, PaymentMethod paymentMethod, int quantity, BigDecimal totalPrice, OrderStatus status, String createdBy, Date createdDate, String updatedBy, Date updatedDate) {
        this.id = id;
        this.carId = carId;
        this.buyerName = buyerName;
        this.paymentMethod = paymentMethod;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
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

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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
        return "Inventory [id=" + id + ", carId=" + carId + ", buyerName=" + buyerName + ", paymentMethod=" + paymentMethod + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", status=" + status +
                ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate + "]";
    }

}
