package com.topiefor.service;

import com.topiefor.models.Address;
import java.util.List;

public interface AddressService {

    boolean addAddress(Address address);

    boolean editAddress(Address address);

    List<Address> getAllAddress();

    List<Address> getAllAddressesByUserID(int userID);

    Address getAddressByID(int addressID);
   boolean checkAddressAvailability(Address address);
}