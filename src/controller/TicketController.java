package controller;

import java.io.*;
import java.util.*;
import modal.*;

public class TicketController {
    public final static String FILENAME = "data/ticket.txt";

    public void create(Booking booking) {
        ArrayList<Booking> allData = new ArrayList<Booking>();
        File tempFile = new File(FILENAME);
        if (tempFile.exists()) allData = read();
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
     * Delete a Transaction in the Database file, based on the TID and MovieGoer's username attribute passed
     * @param TID           Transaction ID of Transaction which will be deleted
     * @param username      Username of Transaction which will be deleted
     */
    public void delete(String TID, String username) {
        ArrayList<Booking> allData = read();
        Booking booking = null;
        ArrayList<Booking> returnData = new ArrayList<Booking>();

        for (int i=0; i<allData.size(); i++){
            booking = allData.get(i);
            if (booking.getTID().equals(TID)
                    && booking.getEmail().equals(username))
                continue;
            returnData.add(booking);
        }
        replaceExistingFile(FILENAME, returnData);
    }

    /**
     * Overwrite Database file with new data of list of Admin
     * @param filename      Filename to check for
     * @param returnData    New ArrayList of Transaction to be written to the file
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

    public ArrayList<Booking> readByEmail(Object valueToSearch) {
        ArrayList<Booking> allData = read();
        ArrayList<Booking> returnData = new ArrayList<Booking>();
        for (int i = 0; i < allData.size(); i++) {
            Booking b = allData.get(i);
            if (b.getEmail().toLowerCase().contains(valueToSearch.toString().toLowerCase())) {
                returnData.add(b);
            }
        }
        return returnData;
    }
}
