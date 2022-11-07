package controller;

import modal.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;

public class SessionController {
    public final static String FILENAME = "data/session.txt";
    private CinemaController cinemaCtrler = new CinemaController();


    public void append(Object obj) {
        ArrayList<Session> allData = new ArrayList<Session>();
        File tempFile = new File(FILENAME);

        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add((Session) obj);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    public void replace(ArrayList<Session> data) {
        File tempFile = new File(FILENAME);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            //
        }
    }

    public int getLastSessionId() {
        int lastId = -1;
        int sessionId;
        ArrayList<Session> allData = read();
        if(allData == null){
            return 0;
        }
        for (int i = 0; i < allData.size(); i++) {
            sessionId = allData.get(i).getSessionId();
            if (sessionId > lastId)
                lastId = sessionId;
        }
        return lastId;
    }
    public ArrayList<Session> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Session> sessions = (ArrayList<Session>) ois.readObject();
            ois.close();
            return sessions;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Session>();
    }

    public Session readById(int valueToSearch) {
        ArrayList<Session> allData = read();
        for (int i = 0; i < allData.size(); i++) {
            Session s = allData.get(i);
            if (s.getSessionId() == valueToSearch)
                return s;
        }
        return null;
    }

    public void updateById(int choice ,int id, Object newValue) {
        ArrayList<Session> sessionArrayList = read();
        for(int i = 0; i< sessionArrayList.size(); i++){
            if(sessionArrayList.get(i).getSessionId()  == id){
                if(choice == 1) {
                    sessionArrayList.get(i).setMovie((Movie)newValue);
                }else if(choice == 2){
                    sessionArrayList.get(i).setDateTime((Date)newValue);
                    sessionArrayList.get(i).setDay(returnEnumsDay((Date)newValue));

                }
            }
        }

        replace(sessionArrayList);
    }

    public void remove(int id){
        ArrayList<Session> sessionArrayList = read();
        for(int i = 0; i < sessionArrayList.size(); i++){
            if(sessionArrayList.get(i).getSessionId() == id){
                sessionArrayList.remove(i);
            }
        }
        replace(sessionArrayList);
    }


    public void printAllSession() {
        ArrayList<Session> sf = read();
        println("");
        printHeader("Printing all Sessions:");
        System.out.printf("| %5s | %5s | %5s | %5s | %30s | %12s |%n", "SID", "CinNo", "MID","Seats", "DateTime","Day");
        System.out.printf("-------------------------------------------------------------------------\n");

        for (int i = 0; i < sf.size(); i++) { //return one section by one for the whole session file
            System.out.printf("| %5s | %5s | %5s | %2s,%2s | %30s | %12s |%n",sf.get(i).getSessionId(),sf.get(i).getCinema().getCinemaNo(),sf.get(i).getMovie().getId(),sf.get(i).getSeats().size(),sf.get(i).getSeats().get(0).size() ,sf.get(i).getDateTime(),sf.get(i).getDay());

        }
    }

}
