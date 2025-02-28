package com.topiefor.models;

import java.util.Objects;

public class Ingredient implements Comparable<Ingredient> {

    private int ingredientID;
    private String name;
    private int quantity;
    private Unit unit;
    private boolean status;

    public Ingredient() {

    }

    public Ingredient(int ingreidientID, String name, int quantity, Unit unit, boolean status) {
        this.ingredientID = ingreidientID;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.status = status;
    }

    public Ingredient(int ingreidientID, int quantity, Unit unit) {
        this.ingredientID = ingreidientID;
       
        this.quantity = quantity;
        this.unit = unit;
        
    }

    public Ingredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    

    public Ingredient(int ingreidientID, String name, int quantity, Unit unit) {
        this.ingredientID = ingreidientID;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.status = false;
    }

    public Ingredient(int ingreidientID, String name, int quantity) {
        this.ingredientID = ingreidientID;
        this.name = name;
        this.quantity = quantity;
        this.status = true;
    }

    public Ingredient(int ingreidientID, boolean status) {
        this.ingredientID = ingreidientID;
        this.status = status;
    }

    public Ingredient(int ingreidientID, int quantity) {
        this.ingredientID = ingreidientID;
        this.quantity = quantity;

    }

    public Ingredient(String name, int quantity, Unit unit) {
        this.ingredientID = 0;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.status = true;
    }

    public boolean isStatus() {
        return status;
    }

    public int getIngreidientID() {
        return ingredientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        final Ingredient other = (Ingredient) obj;
        return this.ingredientID == other.ingredientID;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "ingreidientID=" + ingredientID + ", name=" + name + ", quantity=" + quantity + ", unit=" + unit + ", status=" + isStatus() + '}';
    }

    @Override
    public int compareTo(Ingredient i) {
        if (this.status && !i.status) {
            return -1;
        } else if (!this.status && i.status) {
            return 1;
        } else {
            return 0;
        }
    }

}