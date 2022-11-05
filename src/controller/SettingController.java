package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static view.utilF.print;

public class SettingController {
    public final static String FILENAME = "data/settings.txt";

    // Write Settings into .txt //
    public void writeSetting(ArrayList<String> message) {
        try {
            FileWriter myWriter = new FileWriter(FILENAME);
            for (String s : message) {
                myWriter.write(s);
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void replaceFile(ArrayList<String> message){
        File myObj = new File(FILENAME);
        if (myObj.delete()) {
            writeSetting(message);
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    /* Function to readSetting files and return a list of Settings */
    public ArrayList<String> readSettings() {
        try {
            ArrayList<String> settingList = new ArrayList<>();
            File myObj = new File(FILENAME);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                settingList.add(data);

            }
            myReader.close();
            return settingList;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public boolean enableTicket(String value) {
        ArrayList<String> settingList;
        settingList = readSettings();
        for (String s : settingList) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    // Function to set 3 cases
    /*
    1. Show Rating
    2. Show Review
    3. Show both Rating and Review
     */
    public int returnResult(){
        // Default: Only show rating and sales
        if(enableTicket("sales") && enableTicket("ratings")){
            return 3;
            // Only show ratings
        }else if(enableTicket("ratings")){
            return 2;
        }else if(enableTicket("sales")){
            // Show sales
            return 1;
        }else{
            // Show None
            return 3;
        }

    }

    public void printSettings(){
        print("Currently Display enabled:");
        ArrayList<String> settingList = readSettings();
        if(settingList.isEmpty()){
            print("[]");
        }
        for(String s: settingList){
            print("[" + s + "]");
        }
    }
}


