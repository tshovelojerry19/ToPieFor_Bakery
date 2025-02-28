package com.topiefor.dao.impl;

import com.topiefor.dao.CategoryDao;
import com.topiefor.models.Category;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private static CategoryDaoImpl categoryDaoImpl;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private CategoryDaoImpl(Connection con) {
        this.con = con;
        fetchAllCategoriesFromDB();
    }

    //--------------------------------------------------------
    public static CategoryDaoImpl getInstance(Connection dbCon) {
        if (categoryDaoImpl == null) {
            categoryDaoImpl = new CategoryDaoImpl(dbCon);
        }
        return categoryDaoImpl;
    }

    private List<Category> fetchAllCategoriesFromDB() {
        List<Category> categoryList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT CategoryID,Name,Flag FROM category");
                rs = ps.executeQuery();
                while (rs.next()) {
                    categoryList.add(new Category(rs.getInt("CategoryID"), rs.getString("Name"), rs.getBoolean("Flag")));
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
        return categoryList;
    }

    @Override
    public List<Category> getAllCategories() {
        return fetchAllCategoriesFromDB();
    }

    @Override
    public boolean addCategory(Category category) {
        if (checkCategoryAvailability(category)) {
            return false;
        }
        boolean retVal = false;
        if (con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO category(CategoryID,Name,LastDateUpdated, flag) values(?,?,?,?)");
                ps.setInt(1, 0);
                ps.setString(2, category.getName());
                Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                ps.setTimestamp(3, currentTimestamp);
                ps.setBoolean(4, category.getStatus());
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

    private boolean checkCategoryAvailability(Category category) {
        return fetchAllCategoriesFromDB().stream().anyMatch(cat -> (cat.equals(category)));
    }

    @Override
    public boolean editCatgory(Category category) {

        if (checkCategoryAvailability(category)) {
            boolean retVal = false;
            if (con != null) {
                try {

                    ps = con.prepareStatement("UPDATE category SET Name = ?,LastDateUpdated =? WHERE CategoryID = ? ");
                    ps.setString(1, category.getName());
                    Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    ps.setTimestamp(2, currentTimestamp);
                    ps.setInt(3, category.getCategoryID());
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
        return false;
    }

    @Override
    public boolean deleteCategory(Category category) {

        if (checkCategoryAvailability(category)) {
            boolean retVal = false;
            if (con != null) {
                try {
                    ps = con.prepareStatement("UPDATE category SET flag = ?,LastDateUpdated =?  WHERE CategoryID = ?");
                    ps.setBoolean(1, category.getStatus());
                    Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    ps.setTimestamp(2, currentTimestamp);
                    ps.setInt(3, category.getCategoryID());
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
        return false;
    }

    

}