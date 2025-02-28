package com.topiefor.models;

import java.util.Objects;

public class User {

    private int userID;
    private String userName;
    private String surName;
    private String title;
    private String telephoneNumber;
    private String email;
    private Address address;
    private Role role;
    private boolean status;
    private String password;

    public User() {
    }

    public User(int userID, String userName, String surName, String title, String email, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.surName = surName;
        this.title = title;
        this.email = email;
        this.status = status;
    }

    public User(int userID) {
        this.userID = userID;
    }

    public User(int userID, String userName, String surName) {
        this.userID = userID;
        this.userName = userName;
        this.surName = surName;
    }

    public User(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public User(int userID, String userName, String surName, String title, String email, String telephoneNumber) {
        this.userID = userID;
        this.userName = userName;
        this.surName = surName;
        this.title = title;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public User(int userID, String userName, String surName, String title, String telephoneNumber, String email, Address address, Role role, boolean status, String password) {
        this.userID = userID;
        this.userName = userName;
        this.surName = surName;
        this.title = title;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public User(int userID, String userName, String surName, String title, String telephoneNumber, Address address) {
        this.userID = userID;
        this.userName = userName;
        this.surName = surName;
        this.title = title;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    public User(int userID, String userName, String surname, String telephoneNumber, String email, Address address, Role role, boolean status, String password) {
        this.userID = userID;
        this.userName = userName;
        this.surName = surname;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public User(int userID, Address address, boolean status) {
        this.userID = userID;
        this.address = address;
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final User other = (User) obj;
        if (this.userID != other.userID) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.surName, other.surName)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.telephoneNumber, other.telephoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", userName=" + userName + ", surName=" + surName + ", title=" + title + ", telephoneNumber=" + telephoneNumber + ", email=" + email + ", address=" + address + ", role=" + role + ", status=" + status + ", password=" + password + '}';
    }

}