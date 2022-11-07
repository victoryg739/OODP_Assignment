package controller;

import modal.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static view.utilF.*;


public class CinemaController {
    private CineplexController cineplexController = new CineplexController();

    public final static String FILENAME = "data/cinema.txt";


        public final static int NAME = 0;
        public final static int CINEMAS = 1;

        public void append(int row ,int col,String cinemaNo, Movie movie, Enums.ClassCinema classCinema, ArrayList<Session> sessions ){
            Cinema cinema  = new Cinema(row,col,cinemaNo,movie,classCinema,sessions);

            // Creates an ArrayList of movie
            ArrayList<Cinema> allData = new ArrayList<Cinema>();
            File tempFile = new File(FILENAME);

            // If it exists then read() the existing data
            if (tempFile.exists())
                allData = read();
            try {
                // Write the data to the movie
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
                allData.add(cinema);
                out.writeObject(allData);
                out.flush();
                out.close();
            } catch (IOException e) {
                // ignore error
            }

        }

    public void replace(ArrayList<Cinema> data) {
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

    /**
     * READ and return every Cinema in the Database file
     * @return Model.{@link Cinema}    Return list of Cinemas if found, else empty list
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Cinema> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Cinema> cinemaListing = (ArrayList<Cinema>) ois.readObject();
            ois.close();
            return cinemaListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error

        }
        return new ArrayList<Cinema>();
    }

    public ArrayList<Cinema> readByAttribute(Object valueToSearch) {
        ArrayList<Cinema> returnData = new ArrayList<Cinema>();
        ArrayList<Cinema> cinemaListing = read();
        Cinema cinema = null;

        for (int j=0; j<cinemaListing.size(); j++){
            cinema = cinemaListing.get(j);
            if (cinema.getCinemaNo().equals((String) valueToSearch))
                returnData.add(cinema);

        }
        return returnData;
    }

    public void cinemaUpdateSession(Object valueToSearch,Session newSession) {
        ArrayList<Cinema> cinemaListing = read();
        ArrayList<Session> sessionList = new ArrayList<Session>();

        for (int j=0; j<cinemaListing.size(); j++) {
            if (cinemaListing.get(j).getCinemaNo().equals((String) valueToSearch)) {
                if(cinemaListing.get(j).getSessions()  != null) {
                    sessionList = cinemaListing.get(j).getSessions(); //old list of session in cinema
                }
                sessionList.add(newSession);
                cinemaListing.get(j).setSessions(sessionList);
            }
        }
        replace(cinemaListing);

    }

    public void updateSeat(Object valueToSearch, ArrayList<Seat> selectedSeat, Movie movie, Session session) {
        ArrayList<Cinema> cinemaListing = read();
        ArrayList<ArrayList<Seat>> seatList = new ArrayList<ArrayList<Seat>>();

        for (int j=0; j<cinemaListing.size(); j++) {
            if (cinemaListing.get(j).getCinemaNo().equals((String) valueToSearch)) {
                if(cinemaListing.get(j).getSeats()  != null) {
                    seatList = cinemaListing.get(j).getSeats(); //old list of seat in cinema
                }
                for (Seat s : selectedSeat) {
                    int row = s.getRow();
                    int col = s.getCol();
                    seatList.get(row).get(col).setTaken(true);
                    }
                }
                cinemaListing.get(j).setSeatPlan(seatList);

        }
    replace(cinemaListing);
    }



    public void cinemaUpdateBySessionId(int choice,int sessionId,Object newValue) { //update cinema session arrayList
        ArrayList<Cinema> cinemaListing = read();
        ArrayList<Session> sessionList = new ArrayList<Session>();
        for (int j=0; j<cinemaListing.size(); j++) {
            sessionList =  cinemaListing.get(j).getSessions();
            if(sessionList != null) {
                for (int i = 0; i < sessionList.size(); i++) {
                    if (sessionList.get(i).getSessionId() == sessionId) {
                        if (choice == 1) {
                            cinemaListing.get(j).getSessions().get(i).setMovie((Movie) newValue);
                        } else if (choice == 2) {
                            cinemaListing.get(j).getSessions().get(i).setDateTime((Date) newValue);
                            cinemaListing.get(j).getSessions().get(i).setDay(returnEnumsDay((Date) newValue));


                        } else if (choice == 3) {
                            //return seats
                        }
                    }
                }
            }

        }
        replace(cinemaListing);


    }

    public void cinemaRemoveSession(int sessionId) {
        ArrayList<Cinema> cinemaListing = read();
        ArrayList<Session> sessionList = new ArrayList<Session>();
        for (int j=0; j<cinemaListing.size(); j++) {
            sessionList = cinemaListing.get(j).getSessions();
            if (sessionList != null) {
                for (int i = 0; i < sessionList.size(); i++) {
                    if (sessionList.get(i).getSessionId() == sessionId) {
                        cinemaListing.get(j).getSessions().remove(i);

                    }
                }
            }
        }
        replace(cinemaListing);
    }


        //added
    public Cinema readByCinemaNo(String valueToSearch) {
        ArrayList<Cinema> allData = read();
        for (int i=0; i<allData.size(); i++){
            Cinema cinema = allData.get(i);
            if (cinema.getCinemaNo().equals(valueToSearch))
                return cinema;
        }
        return null;
    }

    /**
     * READ and return every Cinema of a given Cineplex in the Database file
     * @param name                  Name of cineplex to search for
     * @return Model.{@link Cinema}    Return list of Cinemas if found, else empty list
     */
    public ArrayList<Cinema> readByCineplexName(String name){
        ArrayList<Cinema> returnData = new ArrayList<Cinema>();
        ArrayList<Cineplex> cineplexListing = cineplexController.read();
        ArrayList<Cinema> cinemaListing = this.read();


        Cineplex cineplex = null;
        ArrayList<Cinema> cinema = new ArrayList<Cinema>();
        for (int i=0; i<cineplexListing.size(); i++){
            cineplex = cineplexListing.get(i);
            if(cineplex.getLocation().equals(name)){ // Find list of cineplex of same name
                cinema = cineplex.getCinemas();

            }
        }
        for(int j =0; j < cinema.size(); j++) {
            for(int a =0; a < cinemaListing.size(); a++){
                if(cinemaListing.get(a).getCinemaNo().equals(cinema.get(j).getCinemaNo())){
                    returnData.add(cinemaListing.get(a));
                }
            }

        }

        return returnData;
    }
    public int[] getSeatsByCinemaNo(String cinemaNo){
        ArrayList<Cinema> cf = read();
        int[] rowCol = new int[2];
        for(int a =0 ; a<cf.size();a++) {
            if(cf.get(a).getCinemaNo().equals(cinemaNo)){
                rowCol[0] = cf.get(a).getSeats().size();
                rowCol[1] = cf.get(a).getSeats().get(0).size();
                return rowCol;
            }
        }
        return rowCol;

    }
    public void printAllCinema() {
        ArrayList<Cinema> cf = read();
        println("");
        printHeader("Printing all Cinema");
        System.out.printf(" %5s | %10s | %6s | %-30s %n", "CinNo", "Class", "Seats", "Sessions");
        System.out.printf("---------------------------------------------------------------\n");


        for(int a =0 ; a<cf.size();a++){

            System.out.printf(" %5s | %10s | %3s,%3s |", cf.get(a).getCinemaNo(),cf.get(a).getClassCinema(),cf.get(a).getSeats().size(),cf.get(a).getSeats().get(0).size());

            if(cf.get(a).getSessions() != null) {
                for (int i = 0; i < cf.get(a).getSessions().size(); i++) {
                    if(i != 0){
                        System.out.printf(",");
                    }
                    System.out.print(cf.get(a).getSessions().get(i).getSessionId());


                }
            }else{
                System.out.print("No Sessions");
            }
            System.out.printf("\n");
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
        System.out.println("|");
        //print("|      Screen       |");
        for (i = 0; i < (1 + col) * 3 / 2 - 8; i++)
            System.out.print("-----");
        //print("---------------------");

        print("");
        int new_row = 0;
        for(i =0; i<row; i++)
        {
            new_row = 0;
            System.out.print(String.valueOf(i + 1) + " ");
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
                    else
                        System.out.print("[ ]");
                } else {
                    System.out.print("   ");
                    j--;
                }
                new_row++;
            }
            print(" ");
        }
        //println("---------------------");
        for (i = 0; i < (1 + col) * 3 / 2 - 8; i++)
            System.out.print("-----");
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
            else break;
        } while (true);

        seatList.get(i).get(j).setSelected(true);
        println("Selected Seat: Row: " + (i+1) + " Col: " + (j+1));
        return seatList.get(i).get(j);
    }

    public ArrayList<Session> showAvailableSessions(String cineplexName, Movie movie) {
        Session tempSession;
        Cinema tempCinema;
        ArrayList<Session> sessionList = new ArrayList<>();
        int count = 1;
        boolean printSeparator = false;
        ArrayList<Cinema> cinemaList = this.readByCineplexName(cineplexName);
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < cinemaList.size() ; i++) {
            tempCinema = cinemaList.get(i);
            System.out.println((i+1) + " Cinema code: " + tempCinema.getCinemaNo() + "		Cinema type: " + tempCinema.getClassCinema());
            System.out.println();
        }

//        while (tempCinema == null) {
        int cinemaChoice = readIntInput("Enter cinema of choice: ");
        tempCinema = cinemaList.get(cinemaChoice - 1);
//        }

        System.out.println("Available screening times of " + movie.getTitle() + " in this cinema:");
        System.out.println();

//        if (tempCinema.getSessions() == null) {
//            return null;
//        }
        for(int j = 0; j < tempCinema.getSessions().size() ; j++) {
            tempSession = tempCinema.getSessions().get(j);
            if (tempSession.getMovie().getTitle().equals(movie.getTitle())) {
                System.out.println(count + ".	Date: " + tempSession.getDateTime());
                System.out.println();
                printSeparator = true;
                count++;
                sessionList.add(tempSession);
            }
            if(printSeparator){
                System.out.println("------------------------------------------------------");
                printSeparator = false;
            }
        }
        return sessionList;
    }

}
