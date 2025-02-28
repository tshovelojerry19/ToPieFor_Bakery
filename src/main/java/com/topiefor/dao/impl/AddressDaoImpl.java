package com.topiefor.dao.impl;

import com.topiefor.dao.AddressDao;
import com.topiefor.models.Address;
import com.topiefor.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    private static AddressDaoImpl addressDaoImpl;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private AddressDaoImpl(Connection con) {
        this.con = con;
        fetchAllAddressFromDB();
    }

    //--------------------------------------------------------
    public static AddressDaoImpl getInstance(Connection dbCon) {
        if (addressDaoImpl == null) {
            addressDaoImpl = new AddressDaoImpl(dbCon);
        }
        return addressDaoImpl;
    }

    private List<Address> fetchAllAddressFromDB() {
        List<Address> addressList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT AddressID,Street,Suburb,Code FROM address");
                rs = ps.executeQuery();
                while (rs.next()) {
                    addressList.add(new Address(rs.getInt("AddressID"), rs.getString("Street"), rs.getString("Suburb"), rs.getString("Code")));
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
        return addressList;
    }

    @Override
    public boolean addAddress(Address address) {
   
        boolean retVal = false;
        if (con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO address (Street, Suburb, Code) VALUES (?, ?, ?)");
                
                ps.setString(1, address.getStreet());
                ps.setString(2, address.getSuburb());
                ps.setString(3, address.getCode());
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
    public boolean editAddress(Address address) {
        
        boolean retVal = false;
        if (con != null) {
            try {

                ps = con.prepareStatement("UPDATE address SET Street = ?,Suburb = ?, Code = ? WHERE AddressID = ?");
                ps.setString(1, address.getStreet());
                ps.setString(2, address.getSuburb());
                ps.setString(3, address.getCode());
                ps.setInt(4, address.getAddressID());
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
    public List<Address> getAllAddress() {
        return fetchAllAddressFromDB();
    }
     @Override
    public List<Address> getAllAddressesByUserID(int userID) {
                List<Address> addressByUserIDList = new ArrayList<>();
        if (con != null) {
            try {
                ps = con.prepareStatement("SELECT a.AddressID, address.Street, address.Suburb, address.Code, user.UserID FROM user, address, user_address a WHERE user.UserID = a.UserID AND a.AddressID = address.AddressID AND a.UserID = ?");
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    addressByUserIDList.add(new Address(rs.getInt("AddressID"), rs.getString("Street"), rs.getString("Suburb"), rs.getString("Code"), new User(rs.getInt("UserID"))));
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
        return addressByUserIDList;
    }

    

    

   

}