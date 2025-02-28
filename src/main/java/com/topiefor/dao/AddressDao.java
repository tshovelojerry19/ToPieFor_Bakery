package com.topiefor.dao;
import com.topiefor.models.Address;
import java.util.List;

public interface AddressDao {

    boolean addAddress(Address address);
    boolean editAddress(Address address);
    List<Address> getAllAddress();
   List<Address> getAllAddressesByUserID(int userID);

}