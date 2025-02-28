package com.topiefor.models;

import java.time.LocalDate;
import java.util.Objects;

public class OrderReport {

    private LocalDate startDate;
    private LocalDate EndDate;
    private LocalDate oneDate;
    private Category category;
    private double totAmntPerCat;
    private int totProdOrdered;
    private int orderID;
    private String status;
    private User user;
    private Address address;

    public OrderReport(LocalDate startDate, LocalDate EndDate, LocalDate oneDate, Category category, double totAmntPerCat, int totProdOrdered, int orderID, String status, User user) {
        this.startDate = startDate;
        this.EndDate = EndDate;
        this.oneDate = oneDate;
        this.category = category;
        this.totAmntPerCat = totAmntPerCat;
        this.totProdOrdered = totProdOrdered;
        this.orderID = orderID;
        this.status = status;
        this.user = user;
       
    }
    public OrderReport( int orderID, double totAmntPerCat, String status, User user, Address address) {
        this.totAmntPerCat = totAmntPerCat;
        this.orderID = orderID;
        this.status = status;
        this.user = user;
        this.address = address;
       
    }

    public OrderReport(LocalDate startDate, LocalDate EndDate, LocalDate oneDate, Category category, double totAmntPerCat, int totProdOrdered) {
        this.startDate = startDate;
        this.EndDate = EndDate;
        this.oneDate = oneDate;
        this.category = category;
        this.totAmntPerCat = totAmntPerCat;
        this.totProdOrdered = totProdOrdered;

    }

    public OrderReport(LocalDate startDate, LocalDate EndDate, LocalDate oneDate, Category category, double totAmntPerCat, String status) {
        this.startDate = startDate;
        this.EndDate = EndDate;
        this.oneDate = oneDate;
        this.category = category;
        this.totAmntPerCat = totAmntPerCat;
        this.status = status;

    }

    public OrderReport(LocalDate startDate, LocalDate EndDate, LocalDate oneDate, Category category, double totAmntPerCat, int orderID, String status ) {
        this.startDate = startDate;
        this.EndDate = EndDate;
        this.oneDate = oneDate;
        this.category = category;
        this.totAmntPerCat = totAmntPerCat;
        this.orderID = orderID;
        this.status = status;
         this.user = user;
    }

    public OrderReport(LocalDate startDate, LocalDate EndDate, double totAmntPerCat, int orderID, String status) {
        this.startDate = startDate;
        this.EndDate = EndDate;
        this.totAmntPerCat = totAmntPerCat;
        this.orderID = orderID;
        this.status = status;
    }

    public OrderReport(LocalDate oneDate, Category category, double totAmntPerCat, int totProdOrdered) {
        this.oneDate = oneDate;
        this.category = category;
        this.totAmntPerCat = totAmntPerCat;
        this.totProdOrdered = totProdOrdered;
    }

    public OrderReport(LocalDate startDate, LocalDate EndDate) {
        this.startDate = startDate;
        this.EndDate = EndDate;
    }

    public OrderReport(LocalDate oneDate) {
        this.oneDate = oneDate;
        this.startDate = LocalDate.now();
        this.EndDate = LocalDate.of(2023, 1, 1);

    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate EndDate) {
        this.EndDate = EndDate;
    }

    public LocalDate getOneDate() {
        return oneDate;
    }

    public void setOneDate(LocalDate oneDate) {
        this.oneDate = oneDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getTotAmntPerCat() {
        return totAmntPerCat;
    }

    public void setTotAmntPerCat(double totAmntPerCat) {
        this.totAmntPerCat = totAmntPerCat;
    }

    public int getTotProdOrdered() {
        return totProdOrdered;
    }

    public void setTotProdOrdered(int totProdOrdered) {
        this.totProdOrdered = totProdOrdered;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Category=" + category + ", totAmntPerCat=" + totAmntPerCat + ", totProdOrdered=" + totProdOrdered + ", orderID= " + orderID + ", status =" + status + '}';
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}