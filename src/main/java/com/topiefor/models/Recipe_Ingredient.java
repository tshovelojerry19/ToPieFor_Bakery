/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.topiefor.models;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author SHERMAN
 */
public class Recipe_Ingredient {
    private Recipe recipe;
    private List<Ingredient> ingredients;
    private int quantity;

    public Recipe_Ingredient() {
    }
/////////////////////////////////1,intruction.list of ingredients/////////quantity
    public Recipe_Ingredient(Recipe recipe, List<Ingredient> ingredients, int quantity) {
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        final Recipe_Ingredient other = (Recipe_Ingredient) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.recipe, other.recipe)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Recipe_Ingredient{" + "recipe=" + recipe + ", ingredients=" + ingredients + ", quantity=" + quantity + '}';
    }
    
}