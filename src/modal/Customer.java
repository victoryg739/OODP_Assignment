package modal;
import java.io.Serializable;
import java.util.*;

public class Customer implements Serializable {
    private String username;
    private String password;

    private String phoneNumber;
    private String email;

    private int customerID;
    private ArrayList<Booking> bookings;

    public Customer(String username, String password, String email, String phoneNumber)  {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bookings = new ArrayList<>();
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}