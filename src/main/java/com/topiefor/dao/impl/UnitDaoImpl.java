package com.topiefor.dao.impl;

import com.topiefor.dao.UnitDao;
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

public class UnitDaoImpl implements UnitDao {

    private static UnitDaoImpl unitDaoImpl;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private UnitDaoImpl(Connection con) {
        this.con = con;
        fetchAllUnitsFromDB();
    }

    //--------------------------------------------------------
    public static UnitDaoImpl getInstance(Connection dbCon) {
        if (unitDaoImpl == null) {
            unitDaoImpl = new UnitDaoImpl(dbCon);
        }
        return unitDaoImpl;
    }

    private List<Unit> fetchAllUnitsFromDB() {
        List<Unit> unitList = new ArrayList<>();

        if (con != null) {
            try {

                ps = con.prepareStatement("SELECT UnitID, UnitType FROM units");
                rs = ps.executeQuery();
                while (rs.next()) {
                    unitList.add(new Unit(rs.getInt("UnitID"), rs.getString("UnitType")));
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
        return unitList;
    }

    private boolean checkUnitAvailability(Unit unit) {
      
      return fetchAllUnitsFromDB().stream().anyMatch(un -> (un.equals(unit)));
       
    }

    @Override
    public boolean addUnit(Unit unit) {
        if (checkUnitAvailability(unit)) {
            return false;
        }
        boolean retVal = false;
        if (con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO units(UnitID,UnitType,LastDateUpdated) VALUES(?,?,?)");
                ps.setInt(1, 0);
                ps.setString(2, unit.getUnitType());
                Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                ps.setTimestamp(3, currentTimestamp);

                if (ps.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
                System.out.println("the error is here");
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
    public boolean editUnit(Unit unit) {
        System.out.println(unit);
        boolean retVal = false;
        if (checkUnitAvailability(unit)) {

            if (con != null) {

                try {

                    ps = con.prepareStatement("UPDATE units SET UnitType =?, LastDateUpdated =? WHERE UnitID = ? ");
                    ps.setString(1, unit.getUnitType());
                    Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    ps.setTimestamp(2, currentTimestamp);
                    ps.setInt(3, unit.getUnitID());
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
    public List<Unit> getAllUnits() {
        return fetchAllUnitsFromDB();

    }

    

  
 

}