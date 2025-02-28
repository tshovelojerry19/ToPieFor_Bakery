package com.topiefor.models;

public class Category implements Comparable<Category> {

    private int categoryID;
    private String name;
    private boolean status;

    public Category() {

    }

    //---------Constructor-----------//
    public Category(int categoryID, String name, boolean status) {
        this.status = status;
        this.categoryID = categoryID;
        this.name = name;
    }

    public Category(int categoryID, String name) {
        this.status = false;
        this.categoryID = categoryID;
        this.name = name;
    }

    public Category(String name) {

        this.categoryID = 0;
        this.name = name;
        this.status = true;
    }

    public Category(int categoryID) {
        this.categoryID = categoryID;
    }

    //---------Getter and Setters-----------//
    public int getCategoryID() {
        return categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //---------To String-----------//
    @Override
    public String toString() {
        return "Category{" + "categoryID=" + categoryID + ", name=" + name + ", status=" + status + '}';
    }

    //---------HashCode-----------//
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    //---------EqualsTo-----------//
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        final Category other = (Category) obj;
        return this.categoryID == other.categoryID || this.getName().equals(other.name);
    }

    @Override
    public int compareTo(Category c) {
        if (this.status && !c.status) {
            return -1;
        } else if (!this.status && c.status) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Category c = new Category(1, "sherman", true);
        Category d = new Category(2, "new", true);
      

    }
}
