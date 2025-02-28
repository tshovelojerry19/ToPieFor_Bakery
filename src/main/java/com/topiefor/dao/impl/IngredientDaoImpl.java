package com.topiefor.dao.impl;

import com.topiefor.dao.IngredientDao;
import com.topiefor.models.Ingredient;
import com.topiefor.models.Unit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IngredientDaoImpl implements IngredientDao {

    private static IngredientDaoImpl ingredientDaoImpl;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private IngredientDaoImpl(Connection con) {
        this.con = con;
        fetchAllIngredientsFromDB();
    }

    //--------------------------------------------------------
    public static IngredientDaoImpl getInstance(Connection dbCon) {
        if (ingredientDaoImpl == null) {
            ingredientDaoImpl = new IngredientDaoImpl(dbCon);
        }
        return ingredientDaoImpl;
    }

    @Override
    public List<Ingredient> getAllIngredient() {
        return fetchAllIngredientsFromDB();
    }

    private List<Ingredient> fetchAllIngredientsFromDB() {
        List<Ingredient> ingredientList = new ArrayList<>();
        if (con != null) {
            try {

                ps = con.prepareStatement(" SELECT ingredient.IngredientID ,ingredient.Name,ingredient.Quantity, units.UnitID ,units.UnitType, ingredient.flag FROM ingredient JOIN units ON ingredient.UnitID = units.UnitID;");
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    // ingredientList.add(new Ingredient(rs.getInt("IngredientID"), rs.getString("Name"), rs.getInt("Quantity"), new Unit(rs.getInt("UnitID"), rs.getString("UnitType"))));
                    ingredientList.add(new Ingredient(rs.getInt("IngredientID"), rs.getString("Name"), rs.getInt("Quantity"), new Unit(rs.getInt("UnitID"), rs.getString("UnitType")), rs.getBoolean("flag")));

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
        return ingredientList;
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) {
        if (checkIngredientAvailability(ingredient)) {
            return false;
        }
        boolean retVal = false;
        if (con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO ingredient (Name, Quantity, UnitID) VALUES (?, ?, ?)");
                ps.setString(1, ingredient.getName());
                ps.setInt(2, ingredient.getQuantity());
                ps.setInt(3, ingredient.getUnit().getUnitID());
                if (ps.executeUpdate() > 0) {
                    retVal = true;
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
        return retVal;
    }

    private boolean checkIngredientAvailability(Ingredient ingredient) {

        System.out.println(fetchAllIngredientsFromDB().stream().anyMatch(ingr -> (ingr.equals(ingredient))));
        return fetchAllIngredientsFromDB().stream().anyMatch(ingr -> (ingr.equals(ingredient)));
    }

    @Override
    public boolean editIngredient(Ingredient ingredient) {
      
        boolean retVal = false;
        if (con != null) {
            try {
              
                ps = con.prepareStatement("UPDATE ingredient SET Name = ?,Quantity = ?, LastDateUpdated = ? WHERE IngredientID = ?");
                ps.setString(1, ingredient.getName());
                ps.setInt(2, ingredient.getQuantity());
                Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                ps.setTimestamp(3, currentTimestamp);
                ps.setInt(4, ingredient.getIngreidientID());

                if (ps.executeUpdate() > 0) {
                    retVal = true;
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
        return retVal;
    }

    @Override
    public boolean deleteIngredient(Ingredient ingredient) {
        boolean retVal = false;
        if (checkIngredientAvailability(ingredient)) {
            if (con != null) {
                try {

                    ps = con.prepareStatement("UPDATE ingredient SET flag = ?,LastDateUpdated =?  WHERE IngredientID = ?");
                    ps.setBoolean(1, ingredient.isStatus());
                    Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    ps.setTimestamp(2, currentTimestamp);
                    ps.setInt(3, ingredient.getIngreidientID());
                    if (ps.executeUpdate() > 0) {

                        retVal = true;
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
        }
        return retVal;

    }

    

}
