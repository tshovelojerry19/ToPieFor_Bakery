package com.topiefor.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {

    private String password;
    private String hashedPassword;

//    public PasswordEncryptor(String password, String hashedPassword) {
//        this.password = password;
//        this.hashedPassword = hashedPassword;
//    }

    public String encryptPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

}
