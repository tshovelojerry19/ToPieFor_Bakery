package com.topiefor.dao;

import com.topiefor.models.User;
import java.util.List;

public interface UserDao {

    public boolean addUser(User user);

    public boolean userAvailability(int userID, boolean status);

    public boolean editUser(User user);

    boolean resetPassword(int userID, String password);

    List<User> getAllUsers();
}
