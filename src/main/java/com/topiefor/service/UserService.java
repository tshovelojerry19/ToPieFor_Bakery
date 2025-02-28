package com.topiefor.service;

import com.topiefor.models.User;
import java.util.List;

public interface UserService {

    public boolean addUser(User user);

    User getUserByUserID(int userID);

    public boolean userAvailability(int userID, boolean status);

    public boolean editUser(User user);

    User checkUser(String email);

    boolean resetPassword(int userID, String password);

    List<User> getAllUsers();
}
