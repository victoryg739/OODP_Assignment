package modal;
import java.util.*;

public class Customer  {
    public String getId;
    private String customerId, phoneNumber, email;
    private ArrayList<Booking> bookings;


    public Customer(String customerId, String email, String phoneNumber)
    {
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookings = new ArrayList<>();
    }


    public String getId() {
        return customerId;
    }

    public void setId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

//    public void removeBookings(Booking remove_bookings){
//        for(int i = 0; i < this.bookings.size(); i++){
//            if(this.bookings.get(i) == remove_bookings){
//                this.bookings.remove(i);
//                return;
//            }
//        }
//    }

}