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
    public void create(Admin adminAccount) {

        // Creates an ArrayList of admin
        ArrayList<Admin> allData = new ArrayList<Admin>();
        File tempFile = new File(FILENAME);

        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = read();
        try {
            // Write the data to the adminAccounts
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(adminAccount);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    /**
     * READ and return every Admin in the AdminAccounts.txt
     * If Database file not found, ignore error and return empty list
     * @return Model.{@link Admin}     Return list of Admins if found, else empty list
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Admin> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Admin> adminListing = (ArrayList<Admin>) ois.readObject();
            ois.close();
            return adminListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Admin>();
    }

    /**
     * READ and return an Admin by searching for one with matching email in the Database file
     * @param valueToSearch     Email of admin to search for
     * @return Admin            Return Admin if found, else null object
     */
    public String readByUsername(String valueToSearch) {
        ArrayList<Admin> allData = read();
        for (int i=0; i<allData.size(); i++){
            Admin u = allData.get(i);
            System.out.println(u.getUsername());
            if (u.getUsername().equals(valueToSearch))
                return u.getUsername();
        }
        return null;
    }

    /**
     * READ and return an Admin by searching for one with matching email in the Database file
     * @param valueToSearch     Email of admin to search for
     * @return Admin            Return Admin if found, else null object
     */
    public String readByPassword(String valueToSearch) {
        ArrayList<Admin> allData = read();
        for (int i=0; i<allData.size(); i++){
            Admin u = allData.get(i);
            if (u.getPassword().equals(valueToSearch))
                return u.getPassword();
        }
        return null;
    }


    /**
     * UPDATE an Admin's password in Database file
     * Validate user's input of current password to ensure password is correct before updating it
     * @param email             Email of admin who password will be updated
     * @param currentPassword   Current password (Unencrypted) of Admin
     * @param newPassword       New password (Unencrypted) of Admin
     *
     */
//    public void updatePasswordHashed(String email, String currentPassword, String newPassword) {
//        ArrayList<Admin> allData = read();
//        ArrayList<Admin> returnData = new ArrayList<Admin>();
//
//        for (int i=0; i<allData.size(); i++){
//            Admin u = allData.get(i);
//            if (u.getEmail().equals(email))  // update Admin if email matches
//                u.updatePassword(currentPassword, newPassword);
//            returnData.add(u);
//        }
//
//        replaceExistingFile(FILENAME, returnData);
//    }


    /**
     * Delete an Admin in the Database file, based on the email attribute passed
     * @param email Email of Admin who will be deleted
     */
    public void deleteByEmail(String email) {
        ArrayList<Admin> allData = read();
        ArrayList<Admin> returnData = new ArrayList<Admin>();

        for (int i=0; i<allData.size(); i++){
            Admin u = allData.get(i);
            if (!u.getUsername().equals(email))  // add Admin if email does not match
                returnData.add(u);
        }

        replaceExistingFile(FILENAME, returnData);
    }


    /**
     * Overwrite Database file with new data of list of Admin
     * @param filename      Filename to check for
     * @param returnData    New ArrayList of Admin to be written to the file
     */
    public void replaceExistingFile(String filename, ArrayList<Admin> returnData) {
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
}
