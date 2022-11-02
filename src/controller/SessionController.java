package controller;

import modal.*;

import java.io.*;
import java.util.ArrayList;

public class SessionController {
    public final static String FILENAME = "data/session.txt";


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
            ArrayList<Session> session = (ArrayList<Session>) ois.readObject();
            ois.close();
            return session;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Session>();
    }

}
