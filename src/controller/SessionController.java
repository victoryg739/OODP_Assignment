package controller;

import modal.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class SessionController {
    public final static String FILENAME = "data/session.txt";


    public void createSession(Cinema cinema, Movie movie, int sessionID, Date sessionDateTime,Enums.Day day, ArrayList<Seat> seat) {
        Session session = new Session(cinema,movie,sessionID,sessionDateTime,day,seat);
        ArrayList<Session> allData = new ArrayList<Session>();
        File tempFile = new File(FILENAME);

        // If it exists then read() the existing data
        if (tempFile.exists())
            allData = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(session);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }
    public ArrayList<Session> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Session> session = (ArrayList<Session>) ois.readObject();
            ois.close();
            return session;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Session>();
    }

}
