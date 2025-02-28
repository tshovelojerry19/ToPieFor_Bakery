package com.topiefor.models;

public class Product {

    private int productID;
    private String name;
    private double price;
    private String allergies;
    private Recipe recipe;
    private Category category;
    private boolean status;
    private String image;
    private int quantity;

    public Product(int productID, String name, double price, String allergies, Recipe recipe, Category category, boolean status, String image) {
        this.quantity = 1;
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.allergies = allergies;
        this.recipe = recipe;
        this.category = category;
        this.status = status;
        this.image = image;
    }
    public Product(String name, double price,int quantity, String image,Recipe recipe) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.image = image;
        this.recipe = recipe;
    }

    public Product(int productID, String name) {
        this.quantity = 1;
        this.productID = productID;
        this.name = name;
    }

    public Product(int productID, boolean status) {
        this.quantity = 1;
        this.productID = productID;
        this.status = status;

    }

    public Product(int productID, int quantity) {
        this.quantity = 1;
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product(int productID, String name, double price, int quantity, String image) {
        this.quantity = 1;
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }

    public Product(int productID, String name, double price, String allergies, String image) {
        this.quantity = 1;
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.allergies = allergies;
        this.image = image;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(int productID) {
        this.quantity = 1;
        this.productID = productID;
    }

    public Product() {
        this.quantity = 1;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Product other = (Product) obj;
        if (this.productID != other.productID) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", name=" + name + ", price=" + price + ", allergies=" + allergies + ", recipe=" + recipe + ", category=" + category + ", status=" + status + ", image=" + image + '}';
    }
}