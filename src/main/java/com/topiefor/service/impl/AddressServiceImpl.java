package com.topiefor.service.impl;

import com.topiefor.dao.AddressDao;
import com.topiefor.models.Address;
import com.topiefor.service.AddressService;
import java.util.List;

public class AddressServiceImpl implements AddressService {

    private AddressDao addressDao;

    public AddressServiceImpl(AddressDao addressDao) {
        setAddressDao(addressDao);
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
@Override
    public boolean checkAddressAvailability(Address address) {
        return getAllAddress().stream().anyMatch(ad -> (ad.equals(address)));
    }

    @Override
    public boolean addAddress(Address address) {
        if (checkAddressAvailability(address)) {

            return false;
        }
        return address == null ? false : addressDao.addAddress(address);
    }

    @Override
    public boolean editAddress(Address address) {
        if (!checkAddressAvailability(address)) {

            return false;
        }
        return address == null ? false : addressDao.editAddress(address);
    }

    @Override
    public List<Address> getAllAddress() {
        if (addressDao.getAllAddress() != null || !(addressDao.getAllAddress().isEmpty())) {
            return addressDao.getAllAddress();
        }
        return null;
    }

    @Override
    public Address getAddressByID(int addressID) {

        for (Address address : getAllAddress()) {
            if (address.getAddressID() == addressID) {
                return address;
            }
        }

        return null;
    }

    @Override
    public List<Address> getAllAddressesByUserID(int userID) {
        if (addressDao.getAllAddressesByUserID(userID) != null || !(addressDao.getAllAddressesByUserID(userID).isEmpty())) {
            return addressDao.getAllAddressesByUserID(userID);
        }
        return null;
    }

}