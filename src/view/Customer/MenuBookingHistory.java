package view.Customer;

import controller.BookingController;
import controller.CustomerController;
import modal.*;
import view.MenuBase;

import java.util.*;
import static view.utilF.*;

/* ToDO list:
    1. Present the date and time for each booking transaction
    Need to check with Victor about his datetimeFormat
*/

public class MenuBookingHistory extends MenuBase {

    private int tempID;

    public MenuBookingHistory(MenuBase initialMenu, int tempID) {
        super(initialMenu);
        this.tempID = tempID;
    }

    /*
     Display user booking history menu
     Ask user for login information
     Display all user booking detail
     Return to previous menu when done
     */
    public MenuBase execute() {
        printHeader("Booking History:");
        println("Please Login Using Username and Email");

        CustomerController customerController = new CustomerController();
        BookingController bookingController = new BookingController();

        Customer customer;
        CustomerController cController = new CustomerController();
        customer = cController.readByCustomerID(tempID);

//        Customer tempCustomer = customerController.readByUsername(customer.getUsername());
//        //wrong password
//        if (!tempCustomer.getPassword().equals(customer.getPassword())) {
//            customer = null;
//        }

        if (customer != null) { //if login successful
            ArrayList<Booking> booking = bookingController.readbyId(tempID);
            println("In total, " + booking.size() + " bookings found under " + customer.getUsername() + ".");
            int count = 1;

            //Once the user login, for each booking made, display the respective details:
            for (Booking book : booking) {
                println("");
                println("Booking " + count + " :");
                System.out.println(book);
                count++;
                System.out.println("TID: " + book.getTID());
                System.out.println("Show Time: " + book.getSession().getDateTime() + " " + book.getMovie().getRuntime());
                System.out.println("Total Price (GST included): S$" + book.getTotalPrice());
                System.out.println("Movie: " + book.getMovie().getTitle());
                System.out.println("Seats :");
                for (Ticket ticket : book.getTicket()) {
                    System.out.println("Row: " + (ticket.getSeat().getRow() + 1) + " Col: " + (ticket.getSeat().getCol() + 1));
                }
            }
        }
        while (readIntInput("Press 0 to return to Customer Main Menu: ") != 0) ;
        return new MenuCustomerMain(this);
    }

}

