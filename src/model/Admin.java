package model;


import java.io.Serializable;


/**
 * Represents an Admin object
 *
 * @author Tan Wei Zhong
 * @version 1.0
 * @since 2022-08-11
 */
public class Admin implements Serializable, User {

    private String username;
    private String password;
    private int adminID = 0;

    public Admin() {
    }

    /**
     * Admin Class constructor
     *
     * @param username username of Admin
     * @param password password of Admin
     */

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.adminID = generateAdminID();
    }

    /**
     * Get Admin username
     *
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Get Customer password
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Generates unique Admin ID for each Admin object
     *
     * @return adminID
     */
    // generates a unique AdminID for new Admin account
    public int generateAdminID() {
        adminID += 1;
        return adminID;
    }
}
