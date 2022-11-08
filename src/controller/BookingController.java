package controller;

import model.Booking;
import model.Constant;

import java.io.*;
import java.util.ArrayList;

public class BookingController {
    /**
     * Create Booking object and store it in the Database file
     * If Database file exist, existing records are read and new Booking object is appended before saving
     * If Database file does not exist, Booking object will be written to a new file and saved
     * @param booking
     */

    public void create(Booking booking) {
        ArrayList<Booking> allData = new ArrayList<Booking>();
        File tempFile = new File(Constant.BOOKINGFILE);
        if (tempFile.exists()) {
            allData = read();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constant.BOOKINGFILE));
            allData.add(booking);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    /**
     * Read the database file of Booking
     * @return arraylist of Booking files
     */
    public ArrayList<Booking> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.BOOKINGFILE));
            ArrayList<Booking> allData = (ArrayList<Booking>) ois.readObject();
            ois.close();
            return allData;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Booking>();
    }


    /**
     * Overwrite Database file with new data of list of Booking
     * @param filename to check for
     * @param returnData New ArrayList of Transaction to be written to the file
     */
    public void replaceExistingFile(String filename, ArrayList<Booking> returnData) {
        File tempFile = new File(filename);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(returnData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A function to read the Customer username and return ArrayList of Booking that is purchased by user
     * @param valueToSearch            Object that is used
     * @return ArrayList of Booking
     */
    public ArrayList<Booking> readbyUsername(Object valueToSearch) {
        ArrayList<Booking> allData = read();
        Booking booking;
        String dbUsername;
        ArrayList<Booking> returnData = new ArrayList<Booking>();
        for (int i = 0; i < allData.size(); i++) {
            booking = allData.get(i);
            dbUsername = booking.getUsername();
            if (dbUsername.toLowerCase().equals(valueToSearch.toString().toLowerCase())) {
                returnData.add(booking);
            }
        }
        return returnData;
    }
}
