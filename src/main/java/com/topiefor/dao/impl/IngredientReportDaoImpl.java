package com.topiefor.dao.impl;

import com.topiefor.models.Ingredient;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.topiefor.dao.IngredientReportDao;

public class IngredientReportDaoImpl implements IngredientReportDao {

    private static IngredientReportDaoImpl reportDaoImpl;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public IngredientReportDaoImpl(Connection con) {
        this.con = con;
    }

    public static IngredientReportDaoImpl getInstance(Connection dbCon) {
        if (reportDaoImpl == null) {
            reportDaoImpl = new IngredientReportDaoImpl(dbCon);
        }
        return reportDaoImpl;
    }

    @Override
    public List<Ingredient> ingredientInStock() {
        int ingredientAmountAvailable;
        List<Ingredient> ingredientInStockList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT  Name, Quantity FROM ingredient");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ingredientAmountAvailable = rs.getInt("Quantity");
                    if (ingredientAmountAvailable > 0) {
                        ingredientInStockList.add(new Ingredient(rs.getString("Name"), ingredientAmountAvailable));

                    }
                }

            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());

            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return ingredientInStockList;
    }

    @Override
    public List<Ingredient> ingredientsRequiredToBe0rdered() {
        int ingredientAmountDefault = 500;
        int ingredientAmountRequired;
        int ingredientAmountAvailable;
        List<Ingredient> ingredientsRequiredToBe0rdered = new ArrayList<>();

        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT  Name, Quantity FROM ingredient");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ingredientAmountAvailable = rs.getInt("Quantity");
                    if (ingredientAmountAvailable < 500) {

                        ingredientAmountRequired = ingredientAmountDefault - ingredientAmountAvailable;
                        ingredientsRequiredToBe0rdered.add(new Ingredient(rs.getString("Name"), ingredientAmountRequired));
                    }
                }

            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());

            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }

        return ingredientsRequiredToBe0rdered;
    }

    @Override
    public boolean ingredientInStockWriter(String fileName) {
        boolean retval =false;
        File file = new File(fileName);

       
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            return retval;
        }

        List<Ingredient> ingredientList = ingredientInStock();
        try (FileWriter writer = new FileWriter(file)) {
            for (Ingredient ingredient : ingredientList) {
                writer.write(ingredient.getName() + ", " + ingredient.getQuantity() + "\n");
            }
          
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
       return  retval=true;
    }

    @Override
    public boolean ingredientsRequiredToBe0rderedWriter(String fileName) {
         boolean retval =false;
        File file = new File(fileName);

       
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            return retval;
        }

        List<Ingredient> ingredientList = ingredientsRequiredToBe0rdered();
        try (FileWriter writer = new FileWriter(file)) {
            for (Ingredient ingredient : ingredientList) {
                writer.write(ingredient.getName() + ", " + ingredient.getQuantity() + "\n");
            }
           
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
        return  retval=true;
    }

    

}