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
        int lastId = 0;
        int sessionId;
        ArrayList<Session> allData = read();
        if(allData == null){
            return 1;
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

    public ArrayList<Session> readByMovieId(int movieId){
        ArrayList<Session> sessionArrayList = read();
        ArrayList<Session> returnData  = null;
        for(int i = 0; i < sessionArrayList.size(); i++) {
            if (movieId == sessionArrayList.get(i).getMovie().getId()) {
                returnData.add(sessionArrayList.get(i));
            }
        }
        return returnData;

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

    /**
     * Print out the layout of the seats in the current slots,
     * including seats avaliable, seats occupied and seats chosen
     * With corridor printed out in the middle of the layout
     */

    public void displaySeats(ArrayList<ArrayList<Seat>> seatList, int row, int col)
    {
        Seat seat;
        int i;
        int totalSpace = (col + 1)*3 + 2;
        System.out.print("|");
        for (i = 0; i < (totalSpace - 6 )/2 + 2; i++) {
            System.out.print(" ");
        }
        System.out.print("Screen");
        for (i = 0; i < (totalSpace - 6 )/2 + 2; i++) {
            System.out.print(" ");
        }
        print("|");
        for (i = 0; i < (totalSpace - 6 )/2 + 2; i++)
            System.out.print("---");
        print("");
        int new_row = 0;
        for(i =0; i<row; i++)
        {
            new_row = 0;
            System.out.print(i + 1 + " ");
            for(int j=0; j<col; j++)
            {
                if (new_row != col / 2 - 1) {
                    seat = seatList.get(i).get(j);
                    if (seat.isTaken()) {
                        System.out.print("[X]");
                    }
                    else if (seat.isSelected()) {
                        System.out.print("[#]");
                    }
                    else if (seat.isStairWay()) {

                        System.out.print("   ");
                    }else if(seat.isCouple() && j%2 == 0) {
                        System.out.print("[");
                    }else if(seat.isCouple() && j%2 == 1){
                        System.out.print("    ]");
                    }
                    else
                        System.out.print("[ ]");
                }
                else {
                    System.out.print("   ");
                    j--;
                }
                new_row++;
            }
            print(" ");
        }
        for (i = 0; i < (totalSpace - 6 )/2 + 2; i++)
            System.out.print("---");
        print("");
        println("|Entrance|\n");
        println("([ ] Available  [#] Seat Selected  [X] Sold)");
    }

    /**
     * Method to ask user to select a row and column,
     * check whether the seat is avaliable, and update the information in the data base
     */

    public Seat chooseSeats(ArrayList<ArrayList<Seat>> seatList, int row, int col) {
        println("Please choose your seat(s).");
        int i,j;
        do {
            i = readSeatInput("Please input row number",1,row);
            j = readSeatInput("Please input col number",1,col);
            i--;j--;
            if (seatList.get(i).get(j).isTaken() || seatList.get(i).get(j).isSelected())
                println("Already been taken/selected please choose another seats.");
            else if (seatList.get(i).get(j).isStairWay()) {
                println("Unavailable, please pick a different seat.");
            }
            else break;
        } while (true);

        seatList.get(i).get(j).setSelected(true);
        println("Selected Seat: Row: " + (i+1) + " Col: " + (j+1));
        return seatList.get(i).get(j);
    }

    public void updateSeat(Object valueToSearch, ArrayList<Seat> selectedSeat, Movie movie, Session session) {
        ArrayList<Session> sessionListing = read();
        ArrayList<ArrayList<Seat>> seatList = new ArrayList<ArrayList<Seat>>();

        for (int j=0; j<sessionListing.size(); j++) {
            if (sessionListing.get(j).getSessionId() == (int) valueToSearch) {
                if(sessionListing.get(j).getSeats()  != null) {
                    seatList = sessionListing.get(j).getSeats(); //old list of seat in cinema
                }
                for (Seat s : selectedSeat) {
                    int row = s.getRow();
                    int col = s.getCol();
                    seatList.get(row).get(col).setTaken(true);
                }
            }
            sessionListing.get(j).setSeatPlan(seatList);
        }
        replace(sessionListing);
    }







}
