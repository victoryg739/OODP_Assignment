package modal;
import java.io.Serializable;
import java.util.*;

public class Customer implements Serializable {
    private String username;
    private String password;
    private String email;

    private int customerID;
    private ArrayList<Booking> bookings;

    public Customer(String username, String password)  {
        this.username = username;
        this.password = password;
        this.bookings = new ArrayList<>();
        this.customerID = generateCustomerID();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getCustomerID() {return customerID;}

    public void setEmail(String email){
        this.email = email;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    public int generateCustomerID() {
        customerID += 1;
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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