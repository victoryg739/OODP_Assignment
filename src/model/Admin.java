package model;


import java.io.Serializable;

public class Admin implements Serializable, User {

    private String username;
    private String password;
    private int adminID = 0;

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.adminID = generateAdminID();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getAdminID() {
        return this.adminID;
    }

    // generates a unique AdminID for new Admin account
    public int generateAdminID() {
        adminID += 1;
        return adminID;
    }
}
