package modal;
import java.util.*;

public class Customer {
    private String name, phone, email;
    //private ArrayList<Booking> bookings;


    public Customer(String name, String email)
    {
        this.name = name;
        this.email = email;
        //this.bookings = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
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

    public void removeBookings(Booking remove_bookings){
        for(int i = 0; i < this.bookings.size(); i++){
            if(this.bookings.get(i) == remove_bookings){
                this.bookings.remove(i);
                return;
            }
        }
    }

     */

}