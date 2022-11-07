package modal;
import java.io.Serializable;
import java.util.ArrayList;
public class Cineplex implements Serializable {

    private String location;
    private ArrayList<Cinema> cinemas;


    private ArrayList<Session> sessions;

    public Cineplex(String location, ArrayList<Cinema> cinemas,ArrayList<Session> sessions){
        this.location = location;
        this.cinemas = cinemas;
        this.sessions = sessions;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }
    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

}
