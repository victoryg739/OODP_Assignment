package modal;
import java.io.Serializable;
import java.util.ArrayList;
public class Cineplex implements Serializable {

    private String location;
    private ArrayList<Cinema> cinemas;

    public Cineplex(String location, ArrayList<Cinema> cinemas){
        this.location = location;
        this.cinemas = cinemas;
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

}
