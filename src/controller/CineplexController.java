package controller;

import modal.Cinema;
import modal.Cineplex;
import modal.Movie;
import modal.Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;


public class CineplexController {


    public final static String FILENAME = "data/cineplex.txt";


    public final static int NAME = 0;
    public final static int CINEMAS = 1;


    public void append(String location, ArrayList<Cinema> cinemas, ArrayList <Session> sessions) {
        // Creates a movie object
        Cineplex cineplex = new Cineplex(location,cinemas,sessions);

        // Creates an ArrayList of movie
        ArrayList<Cineplex> allData = new ArrayList<Cineplex>();
        File tempFile = new File(FILENAME);

        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = read();
        try {
            // Write the data to the movie
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(cineplex);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }
    public void appendByLocation(String location,String cinemaNo ,Session session){
        ArrayList<Cineplex> allData = read();

        for(int i =0 ;i<allData.size() ; i++){
            if(location.equals(allData.get(i).getLocation())){
                ArrayList<Session> tempS = new ArrayList<Session>();

                if(allData.get(i).getSessions() != null) {
                    tempS = allData.get(i).getSessions();
                }
                tempS.add(session);
                allData.get(i).setSessions(tempS);
            }
        }
        for(int i =0 ;i<allData.size() ; i++) {
            for (int j = 0; j < allData.get(i).getCinemas().size(); j++) {
                if (allData.get(i).getCinemas().get(j).getCinemaNo().equals(cinemaNo)) {
                    ArrayList<Session> tempS = new ArrayList<Session>();

                    if (allData.get(i).getCinemas().get(j).getSessions() != null) {
                        tempS = allData.get(i).getCinemas().get(j).getSessions();
                    }
                    tempS.add(session);
                    allData.get(i).getCinemas().get(j).setSessions(tempS);
                }


            }
        }
            replace(allData);
    }


    public void updateCineplex(int choice,int sessionId,Object newValue){
        ArrayList<Cineplex> allData = read();

        for(int i =0 ;i<allData.size() ; i++){
            if (allData.get(i).getSessions() != null) {
                for(int j = 0; j<allData.get(i).getSessions().size();j++) {
                    if (allData.get(i).getSessions().get(j).getSessionId() == sessionId) {
                        if (choice == 1) {
                            allData.get(i).getSessions().get(j).setMovie((Movie) newValue);
                        } else if (choice == 2) {
                            allData.get(i).getSessions().get(j).setDateTime((Date) newValue);
                            allData.get(i).getSessions().get(j).setDay(returnEnumsDay((Date) newValue));

                        }
                    }
                }
            }
        }

        for(int i =0 ;i<allData.size() ; i++) {
            for (int j = 0; j < allData.get(i).getCinemas().size(); j++) {
                if (allData.get(i).getCinemas().get(j).getSessions() != null) {
                    for(int k = 0; k < allData.get(i).getCinemas().get(j).getSessions().size();k++){

                        if(allData.get(i).getCinemas().get(j).getSessions().get(k).getSessionId() == sessionId) {
                            if (choice == 1) {
                                allData.get(i).getCinemas().get(j).getSessions().get(k).setMovie((Movie) newValue);
                            } else if (choice == 2) {
                                allData.get(i).getCinemas().get(j).getSessions().get(k).setDateTime((Date) newValue);
                                allData.get(i).getCinemas().get(j).getSessions().get(k).setDay(returnEnumsDay((Date) newValue));

                            }
                        }
                    }
                }
            }
        }
        replace(allData);
    }
    public void removeSession(int sessionId){
        ArrayList<Cineplex> allData = read();

        for(int i =0 ;i<allData.size() ; i++){
            if (allData.get(i).getSessions() != null) {
                for(int j = 0; j<allData.get(i).getSessions().size();j++) {
                    if (allData.get(i).getSessions().get(j).getSessionId() == sessionId) {
                       allData.get(i).getSessions().remove(j);
                    }
                }
            }
        }

        for(int i =0 ;i<allData.size() ; i++) {
            for (int j = 0; j < allData.get(i).getCinemas().size(); j++) {
                if (allData.get(i).getCinemas().get(j).getSessions() != null) {
                    for(int k = 0; k < allData.get(i).getCinemas().get(j).getSessions().size();k++){
                            if(allData.get(i).getCinemas().get(j).getSessions().get(k).getSessionId() == sessionId ){
                                allData.get(i).getCinemas().get(j).getSessions().remove(k);
                            }

                        }
                    }
                }
            }
        replace(allData);
    }


        public void replace(ArrayList<Cineplex> data) {
        File tempFile = new File(FILENAME);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }

    public ArrayList<Cineplex> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Cineplex> cineplex = (ArrayList<Cineplex>) ois.readObject();
            ois.close();
            return cineplex;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Cineplex>();
    }

    /**
     * READ and return Cineplex based on name passed in the Database file
     * @param name          Name of cineplex to search for
     * @return Cineplex     Return Cineplex if found, else null object
     */
    public Cineplex readByLocation(String location){
        ArrayList<Cineplex> allData = read();
        for (int i=0; i<allData.size(); i++){
            Cineplex cin = allData.get(i);
            if (cin.getLocation().equals(location))
                return cin;
        }
        return null;
    }

    public void printAllCineplex() {
        ArrayList<Cineplex> cp = read();
        println("");
        printHeader("Printing all Cineplexes");
        System.out.printf("| %10s | %10s | %10s | %n", "Location", "Cinema", "Sessions");
        System.out.printf("---------------------------------------------------------------\n");


        for(int a =0 ; a<cp.size();a++){

            System.out.printf("| %10s |", cp.get(a).getLocation());

            if(cp.get(a).getCinemas() != null) {
                System.out.printf("|");
                for (int i = 0; i < cp.get(a).getCinemas().size(); i++) {
                    if(i != 0){
                        System.out.printf(",");
                    }
                    System.out.print(cp.get(a).getCinemas().get(i).getCinemaNo());

                }
                System.out.printf("   |");
            }else{
                System.out.print("No Cinemas");
            }

            if(cp.get(a).getSessions() != null) {
                System.out.printf("|");
                for (int i = 0; i < cp.get(a).getSessions().size(); i++) {
                    if(i != 0){
                        System.out.printf(",");
                    }
                    System.out.print(cp.get(a).getSessions().get(i).getSessionId());

                }
                System.out.printf("       |");

            }else{
                System.out.print("No Sessions  |");
            }

            System.out.printf("\n");
        }
    }





}