package modal;
import java.util.*;

public class Customer {
    private String email;
    private String password;
    private ArrayList<Booking> bookings;

    public Customer(String email, String password)  {
        this.email = email;
        this.password = password;
        this.bookings = new ArrayList<>();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBookings(Booking new_bookings){
        if(bookings == null){
            this.bookings = new ArrayList<>();
            this.bookings.add(new_bookings);
        }
        else{
            this.bookings.add(new_bookings);
        }
    }
}