package controller;

import modal.Admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class AdminController {

    public final static String FILENAME = "data/adminAccounts.txt";

    // Create a new Admin account and add into adminAccounts.txt
    public void create(String username, String password) {
        // Creates an admin object
        Admin admin = new Admin(username, password);

        // Creates an ArrayList of admin
        ArrayList<Admin> allData = new ArrayList<Admin>();
        File tempFile = new File(FILENAME);

        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = read();
        try {
            // Write the data to the adminAccounts
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(admin);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    // Read and Return every Admin account in adminAccounts.txt
    public ArrayList<Admin> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Admin> admin = (ArrayList<Admin>) ois.readObject();
            ois.close();
            return admin;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Admin>();
    }
}
