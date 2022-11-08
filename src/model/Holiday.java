package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a Holiday for the system to referenced
 * Mainly used to determine any markup in the price of ticket(s)
 *
 * @author Bryan Tay
 * @version 1.0
 * @since 2022-08-11
 */

public class Holiday implements Serializable {

    private Date holidayDate;

    private String name;

    /**
     * Creates a Holiday with the given date
     *
     * @param name        this Holiday's name
     * @param holidayDate this Holiday's date
     */
    public Holiday(String name, Date holidayDate) {
        this.name = name;
        this.holidayDate = holidayDate;
    }


    /**
     * Get the date of this Holiday
     *
     * @return LocalDate    this Holiday's date
     */
    public Date getHolidayDate() {
        return this.holidayDate;
    }


    /**
     * Change the date of this Holiday
     *
     * @param holidayDate this Holiday's new date
     */
    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }


    /**
     * Converting this Holiday's date to a string format for readability reason
     *
     * @return String   another format of this Holiday's date
     */


    public String getFormatedDate() {
        return Constant.dateFormatLong.format(holidayDate);
    }

    /**
     * String to return when this Movie_Goer is being called
     *
     * @return String   format holiday
     */
    public String toString() {
        return "The holiday is on " + getFormatedDate();
    }

    /**
     * Change the name of this Holiday
     *
     * @return String   name of the holiday
     */
    public String getName() {
        return name;
    }

    /**
     * Change the date of this Holiday
     *
     * @param name this Holiday's name
     */
    public void setName(String name) {
        this.name = name;
    }
}

/**
 * Create a new Admin account and add into adminAccounts.txt
 *
 * @param admin          admin object
 * @return LocalDate    this Holiday's date
 */