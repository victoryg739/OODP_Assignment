package modal;
import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {
    private String cinemaNo; //e.g your ticket writes E35 cinema

    private Enums.ClassCinema classCinema;

    private ArrayList <Session> sessions;

    private ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();


    public Cinema(int row, int col,String cinemaNo, Enums.ClassCinema classCinema, ArrayList<Session> sessions) {
        for(int i=0;i<row;i++)
        {
            this.seats.add(new ArrayList<Seat>());
            for(int j=0;j<col;j++)
            {
                Seat tmpSeat = new Seat();
                if(i<4 && j<2){
                    tmpSeat = new Seat(j,i,false,true);
                    System.out.println("stairway");

                }else{
                    tmpSeat = new Seat(j,i,false,false);
                }
                this.seats.get(i).add(tmpSeat);
            }
        }

        this.cinemaNo = cinemaNo;
        this.classCinema = classCinema;
        this.sessions = sessions;
    }

    public ArrayList<ArrayList<Seat>> getSeats() {
        return seats;
    }

    public void setSeats(int row, int col) {
        for(int i=1;i<=row;i++)
        {
            for(int j=1;j<=col;j++)
            {
                Seat tmpseat = new Seat(j,i,false,false);
                this.seats.get(i).add(tmpseat);
            }
        }
    }
    public String getCinemaNo() {
        return cinemaNo;
    }

    public void setCinemaNo(String cinemaNo) {
        this.cinemaNo = cinemaNo;
    }

    public Enums.ClassCinema getClassCinema() {
        return classCinema;
    }

    public void setClassCinema(Enums.ClassCinema classCinema) {
        this.classCinema = classCinema;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public void setSeatPlan(ArrayList<ArrayList<Seat>> seatList) {this.seats = seatList;}

}
