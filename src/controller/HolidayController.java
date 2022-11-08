package controller;

import model.Constant;
import model.Holiday;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static view.utilF.print;
import static view.utilF.println;

public class HolidayController {

    /**
     * Read the database file of Movie
     *
     * @return arraylist of Holiday
     */
    public ArrayList<Holiday> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.HOLIDAYFILE));
            ArrayList<Holiday> holidayListing = (ArrayList<Holiday>) ois.readObject();
            ois.close();
            return holidayListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Holiday>();
    }


    /**
     * CREATE a new holiday and add it into the database file
     * Attributes are validated before creation
     * If attributes are not allowed, throw error and do nothing
     * If Database file exist, existing records are read and new holiday object is aopended before saving
     * If Database file does not exist, holiday object will be written to a new file and saved
     *
     * @param holidayDate Date of this Holiday
     * @param name        Name of the holiday
     */

    public void create(String name, Date holidayDate) {
        Holiday holiday = new Holiday(name, holidayDate);
        ArrayList<Holiday> allData = new ArrayList<Holiday>();
        File tempFile = new File(Constant.HOLIDAYFILE);
        if (tempFile.exists())
            allData = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constant.HOLIDAYFILE));
            allData.add(holiday);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    /**
     * Delete a Holiday in the Database file, based on the date attribute passed
     *
     * @param valueToSearch Date of Holiday to be deleted
     */
    public void delete(Date valueToSearch) {
        ArrayList<Holiday> allData = read();
        ArrayList<Holiday> returnData = new ArrayList<Holiday>();

        for (int i = 0; i < allData.size(); i++) {
            Holiday h = allData.get(i);
            if (!(h.getHolidayDate().equals(valueToSearch)))
                returnData.add(h);
        }

        replaceExistingFile(Constant.HOLIDAYFILE, returnData);
        print("Holiday have been deleted!");
    }

    /**
     * Update a Holiday in the Database file, based on the date attribute passed
     *
     * @param oldDate old date that user wants to replace
     * @param newDate new date that user wants to change
     */
    public void update(Date oldDate, Date newDate) {
        ArrayList<Holiday> allData = read();
        ArrayList<Holiday> returnData = new ArrayList<Holiday>();


        for (int i = 0; i < allData.size(); i++) {
            Holiday h = allData.get(i);
            if (h.getHolidayDate().equals(oldDate)) {
                h.setHolidayDate(newDate);
            }
            returnData.add(h);
        }
        replaceExistingFile(Constant.HOLIDAYFILE, returnData);

    }

    /**
     * Check if a date is an holiday by checking against every records in the Database file
     *
     * @param valueToSearch Date of Holiday to be check
     * @return boolean          Return true if Date passed in is a holiday, else false
     */
    public boolean isHoliday(Date valueToSearch) {
        ArrayList<Holiday> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            if (allData.get(i).getHolidayDate().equals(valueToSearch))
                return true;
        }
        return false;
    }

    /**
     * List down all the holiday
     *
     * @return boolean          returns if there is a list of holiday
     */
    public boolean listAllHolidays() {
        ArrayList<Holiday> holList = read();
        if (holList.isEmpty()) {
            println("There are no holidays declared!");
            return false;
        } else {
            print("Currently declared holidays:");
            holList.forEach(Holiday -> printHol(Holiday));
        }
        return true;
    }

    /**
     * Print each holiday
     */
    public void printHol(Holiday holiday) {

        System.out.println(holiday.getName() + " " + holiday.getFormatedDate());
    }

    /**
     * Replace existing file to a new file
     *
     * @param filename File name of the file that it going to be replace
     * @param data     Data is the new data to be updated
     */
    public void replaceExistingFile(String filename, ArrayList<Holiday> data) {
        File tempFile = new File(filename);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }
}

