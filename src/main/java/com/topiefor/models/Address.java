package com.topiefor.models;

import java.util.Objects;


public class Address {
    private int addressID;
    private String street;
    private String suburb;
    private String code;
    private User user;

    public Address() {
    }

    public Address(int addressID, String street, String suburb, String code) {
        this.addressID = addressID;
        this.street = street;
        this.suburb = suburb;
        this.code = code;
    }

    public Address(int addressID, String street, String suburb, String code, User user) {
        this.addressID = addressID;
        this.street = street;
        this.suburb = suburb;
        this.code = code;
        this.user = user;
    }

    public Address(User user) {
        this.user = user;
    }
    

    public Address(int addressID) {
        this.addressID = addressID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

     public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
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
        final Address other = (Address) obj;
       
        if (!Objects.equals(this.street, other.street)) {

            return false;
        }
        if (!Objects.equals(this.suburb, other.suburb)) {
         
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
          
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
        
            return false;
        }
        return true;
    }

 

 

    @Override
    public String toString() {
        return "Address{" + "addressID=" + addressID + ", street=" + street + ", suburb=" + suburb + ", code=" + code + '}';
    }

   

 
    
}