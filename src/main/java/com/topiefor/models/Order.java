package com.topiefor.models;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {

    private int orderID;
    private Date date;
    private Date dateToBeDelivered;
    private String status;
    private Address address;
    private User user;
    private Double totalPrice;
    private List<Product> product;
    private Product prod;
    private Payment payment;

    public Order(int orderID, Date date, Date dateToBeDelivered, String status, Address address, User user, Double totalPrice, List<Product> product) {
        this.orderID = orderID;
        this.date = date;
        this.dateToBeDelivered = dateToBeDelivered;
        this.status = status;
        this.address = address;
        this.user = user;
        this.totalPrice = totalPrice;
        this.product = product;

    }

    public Order(Product prod) {
        this.prod = prod;
    }

    public Order(int orderID, Date date, Date dateToBeDelivered, String status, Address address, User user, Double totalPrice, Product prod) {
        this.orderID = orderID;
        this.date = date;
        this.dateToBeDelivered = dateToBeDelivered;
        this.status = status;
        this.address = address;
        this.user = user;
        this.totalPrice = totalPrice;
        this.prod = prod;

    }

    public Order(int orderID, Date date, Date dateToBeDelivered, String status, Address address, User user, Double totalPrice) {
        this.orderID = orderID;
        this.date = date;
        this.dateToBeDelivered = dateToBeDelivered;
        this.status = status;
        this.address = address;
        this.user = user;
        this.totalPrice = totalPrice;

    }

    public Order(Address address, User user, Double totalPrice, List<Product> product, Payment payment) {
        this.address = address;
        this.user = user;
        this.totalPrice = totalPrice;
        this.product = product;
        this.payment = payment;
    }

    public Order(User user) {
        this.user = user;
    }

    public Order(int orderID) {
        this.orderID = orderID;
    }

    public Order(int orderID, User user) {
        this.orderID = orderID;
        this.user = user;
    }

    public Order() {
    }

    public Order(int orderID, String status) {
        this.orderID = orderID;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateToBeDelivered() {
        return dateToBeDelivered;
    }

    public void setDateToBeDelivered(Date dateToBeDelivered) {
        this.dateToBeDelivered = dateToBeDelivered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Product getProd() {
        return prod;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.orderID;
        hash = 89 * hash + Objects.hashCode(this.date);
        hash = 89 * hash + Objects.hashCode(this.dateToBeDelivered);
        hash = 89 * hash + Objects.hashCode(this.status);
        hash = 89 * hash + Objects.hashCode(this.address);
        hash = 89 * hash + Objects.hashCode(this.user);
        hash = 89 * hash + Objects.hashCode(this.totalPrice);
        hash = 89 * hash + Objects.hashCode(this.product);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderID != other.orderID) {
            return false;
        }

        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.dateToBeDelivered, other.dateToBeDelivered)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.totalPrice, other.totalPrice)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", date=" + date + ", dateToBeDelivered=" + dateToBeDelivered + ", status=" + status + ", address=" + address + ", user=" + user + ", totalPrice=" + totalPrice + ", product=" + product + ", prod=" + prod + ", payment=" + payment + '}';
    }

}