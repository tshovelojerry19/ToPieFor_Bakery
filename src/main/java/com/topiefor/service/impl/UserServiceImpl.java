package com.topiefor.service.impl;

import com.topiefor.dao.UserDao;
import com.topiefor.models.User;
import com.topiefor.service.UserService;
import java.util.List;
import java.util.Random;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        setUserDao(userDao);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean addUser(User user) {
        if (checkUserAvailability(user)) {
            return false;
        }
        return user == null ? false : userDao.addUser(user);
    }

    @Override
    public boolean userAvailability(int userID, boolean status) {
        
        return userID < 0 ? false : userDao.userAvailability(userID, status);
    }

    @Override
    public boolean editUser(User user) {

        return user == null ? false : userDao.editUser(user);
    }

    private boolean checkUserAvailability(User user) {
        return getAllUsers().stream().anyMatch(us -> (us.equals(user)));
    }

    @Override
    public List<User> getAllUsers() {
        if (userDao.getAllUsers() != null || !(userDao.getAllUsers().isEmpty())) {
            return userDao.getAllUsers();
        }
        return null;
    }

    @Override
    public User checkUser(String email) {

        if (email != null && !email.isEmpty()) {

            for (User user : getAllUsers()) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
        }
        return null;
    }

    public int generateCode() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }

    @Override
    public User getUserByUserID(int userID) {
        for (User user : getAllUsers()) {
            if (userID == user.getUserID()) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean resetPassword(int userID, String password) {
        if(userID > 0 && password != null && !password.isEmpty() && getUserByUserID(userID) != null){
            return userDao.resetPassword(userID, password);
        }
        return false;
    }

   
}
