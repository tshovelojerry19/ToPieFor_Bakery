package com.topiefor.models;

import java.util.List;

public class Recipe {

    private int recipeID;
    private String instruction;
    private String recipeName;
    private boolean status;
    private List<Ingredient> ingredients;

    public Recipe() {
    }

    public Recipe(String recipeName) {

        this.recipeName = recipeName;
    }

    public Recipe(String instruction, String recipeName) {
        this.instruction = instruction;
        this.recipeName = recipeName;
    }

    public Recipe(int recipeID, String instruction, String recipeName, boolean status, List<Ingredient> ingredients) {
        this.recipeID = recipeID;
        this.instruction = instruction;
        this.recipeName = recipeName;
        this.status = status;
        this.ingredients = ingredients;
    }

    public Recipe(int recipeID) {
        this.recipeID = recipeID;
    }

    public Recipe(int recipeID, String instruction, String recipeName, boolean status) {
        this.recipeID = recipeID;
        this.instruction = instruction;
        this.recipeName = recipeName;
        this.status = status;
    }
    

    public Recipe(int recipeID, String recipeName) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
    }

    public Recipe(int recipeID, String instruction, String recipeName, List<Ingredient> ingredients) {
        this.recipeID = recipeID;
        this.instruction = instruction;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
    }

    public Recipe(int recipeID, boolean status) {
        this.recipeID = recipeID;
        this.status = status;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final Recipe other = (Recipe) obj;

        if (this.recipeID != other.recipeID) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Recipe{" + "recipeID=" + recipeID + ", instruction=" + instruction + ", recipeName=" + recipeName + ", status=" + status + '}';
    }
}
