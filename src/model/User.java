package model;

/**
 * The User interface class which is used by Customer and Admin class
 *
 * @author Tan Wei Zhong
 * @version 1.0
 * @since 2022-08-11
 */

public interface User {
    /**
     * Abstract Method which will be implemented by Customer and Admin Class
     */
    public abstract String getUsername();
    /**
     * Abstract Method which will be implemented by Customer and Admin Class
     */
    public abstract String getPassword();

}
