package controller;

import model.Booking;

import java.io.*;
import java.util.ArrayList;

public class BookingController {
    public final static String FILENAME = "data/booking.txt";

    public void create(Booking booking) {
        ArrayList<Booking> allData = new ArrayList<Booking>();
        File tempFile = new File(FILENAME);
        if (tempFile.exists()) {
            allData = read();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(booking);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    public ArrayList<Booking> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Booking> allData = (ArrayList<Booking>) ois.readObject();
            ois.close();
            return allData;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Booking>();
    }


    /**
     * Overwrite Database file with new data of list of Admin
     *
     * @param filename   Filename to check for
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


    public ArrayList<Booking> readbyUsername(String username) {
        ArrayList<Booking> allData = read();
        Booking booking;
        String dbUsername;
        ArrayList<Booking> returnData = new ArrayList<Booking>();
        for (int i = 0; i < allData.size(); i++) {
            booking = allData.get(i);
            dbUsername = booking.getUsername();
            if (dbUsername.toLowerCase().equals(username.toLowerCase())) {
                returnData.add(booking);
            }
        }
        return returnData;
    }
}
