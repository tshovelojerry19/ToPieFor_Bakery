package com.topiefor.models;

import java.util.Objects;

public class Payment {
    
    private int paymentID;
    private String paymentMethod;
    private boolean Status;
    private Order order;

    public Payment(int paymentID, String paymentMethod, boolean Status, Order order) {
        this.paymentID = paymentID;
        this.paymentMethod = paymentMethod;
        this.Status = Status;
        this.order = order;
    }
    public Payment(int paymentID, String paymentMethod, boolean Status) {
        this.paymentID = paymentID;
        this.paymentMethod = paymentMethod;
        this.Status = Status;
      
    }


    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
   
   public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.paymentID;
        hash = 79 * hash + Objects.hashCode(this.paymentMethod);
        hash = 79 * hash + Objects.hashCode(this.Status);
        hash = 79 * hash + Objects.hashCode(this.order);
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
        final Payment other = (Payment) obj;
        if (this.paymentID != other.paymentID) {
            return false;
        }
        if (!Objects.equals(this.paymentMethod, other.paymentMethod)) {
            return false;
        }
        if (!Objects.equals(this.Status, other.Status)) {
            return false;
        }
        if (!Objects.equals(this.order, other.order)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentID=" + paymentID + ", paymentMethod=" + paymentMethod + ", Status=" + Status + ", order=" + order + '}';
    }

 
   
 
    
    
}