package view.customer;

import modal.Customer;
import view.MenuBase;

import java.util.*;
import static view.utilF.*;

public class PurchaseTicketMenu extends MenuBase {
    //private final Movie movie;
    //private Manager manager = Manager.getInstance();
    //private PriceManager priceManager = PriceManager.getInstance();

    public PurchaseTicketMenu(MenuBase previousMenu) {
        super(previousMenu);
        //this.movie = movie;
    }


    /*
     List all the slots available according to the movie information
     Ask user to select slots, seats.
     Ask user information whether they are student or senior
     Get user's information about name, email and phone number
     Provide the total ticket price to the user
     */
    public MenuBase execute() {

        ArrayList<String> choices = new ArrayList<>();
        int c;
        System.out.println("Menu for Purchasing Ticket:");

        //.getSlots() is to check for the available slots for that movie timeslot
        /*
        if(movie.getSlots().isEmpty())
        {
            System.out.println("Sorry, there is no available slot currently.");
            return this.getPreviousMenu();
        }

         */

        //For the info on the movie and the slots
        /*
        for(Slot slot:movie.getSlots())
        {
            choices.add(slot.getCinema().getName() + " -- " + slot.getFormattedDate() + " " + slot.getFormattedTime());
        }

         */
        printMenu(choices, 1);
        //c = readChoice("Please Choose a slot", 0, choices.size());
        //Slot slot = movie.getSlots().get(c);

        //ArrayList<ArrayList<Seat>> seats = slot.getSeats();
        //int col = seats.get(0).size(), row=seats.size();

        //find seat & select seat
        //ArrayList<Seat>selected = new ArrayList<>(); //List of all the selected seats
        /*
        while(confirm("Continue to select seats?"))
        {
            displaySeats(seats,row,col);
            selected.add(chooseSeats(seats,row,col));
        }
        /*
            Model for buying tickets:
            When selecting a few seats, they have to belong to 1 ticket type (Senior/Student/Normal)
         */
        /*
        if (selected.size() != 0) {
            ArrayList<Ticket> tickets = new ArrayList<Ticket>();

            if (confirm("Are you eligible for student discount?")) { //student price
                //getTicketType(slot, isstudent, issenior) => determine the type of ticket
                Constant.TicketType ticketType = priceManager.getTicketType(slot, true, false);
                //for every seat, generate 1 ticket according to the ticket type
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(seat, slot.getMovieType(), ticketType);
                    tickets.add(ticket);
                }
            }
            else if (confirm("Are you eligible for senior discount?")) { //senior price
                //getTicketType(slot, isstudent, issenior) => determine the type of ticket
                Constant.TicketType ticketType = priceManager.getTicketType(slot, false, true);
                //for every seat, generate 1 ticket according to the ticket type
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(seat, slot.getMovieType(), ticketType);
                    tickets.add(ticket);
                }
            }
            else { //neither student nor senior
                Constant.TicketType ticketType = priceManager.getTicketType(slot, false, false);
                //for every seat, generate 1 ticket according to the ticket type
                for (Seat seat : selected) {
                    Ticket ticket = new Ticket(seat, slot.getMovieType(), ticketType);
                    tickets.add(ticket);
                }
            }

         */

            //Get the first ticket to calculate the totalprice
            //Ticket ticket = tickets.get(0);
            /*
            Calculate the price of 1 ticket with the various factors: TicketType, MovieType, Platinum, Sneakpeek/FirstWeek/Blockbuster, ...
            After that, multiply by the # of seats
            */
            //double totalprice = priceManager.getPrice(ticket.getTickettype(), ticket.getMovietype(), slot.isPlatinum(), slot.isSneakOrFirstWeekorblockbuster()) * tickets.size();

            //Get the current Date/time to generate the tid according to the format XXXYYYYMMDDhhmm
            Date currentTime = Calendar.getInstance().getTime();
            //String timeStamp = new SimpleDateFormat("YYYYMMDDhhmm").format(currentTime);
            //String tid = slot.getCinema().getCinemaCode() + timeStamp;
            //Generate the booking
            //Booking booking = new Booking(tid, slot, currentTime, totalprice, movie, slot.getCinema(), tickets);

            //Log into the account if have
            Customer customer; //Need to create User class
            if (confirm("Do you have an account")) {
                customer = login();
            }
            else {
                String name = read("New Name: ");
                String email = read("New Email: ");

                customer = new Customer(name, email);
                //manager.add(Constant.Tables.USER, customer);
            }

            //System.out.println("Total price is S$" + booking.getTotalPrice() + " (Inclusive of GST).");
            //if (confirm("Confirm booking? ")) {
            //    for (Seat seat : selected) {
            //        seat.setSelected(false);
            //        seat.setOcccupied(true);
            //    }
            //    customer.addBookings(booking);
            //    booking.setUser(user);
            //    manager.add(Constant.Tables.BOOKING, booking);
            //    movie.addTicketSales(tickets.size());
            //    for (Ticket ticket1 : tickets) manager.add(Constant.Tables.TICKET, ticket1);
            //    System.out.println("Booking successful, tid=" + tid);

            //}
            //else {
            //    for (Seat seat : selected)
            //        seat.setSelected(false);
            //}
            while (readIntInput("Press 0 to return to previous menu: ") != 0) ;

        return this.getPreviousMenu();
    }
    /*
     Print out the layout of the seats in the current slots,
     including seats available, seats occupied and seats chosen
     With corridor printed out in the middle of the layout
     */
    /*
    private void displaySeats(ArrayList<ArrayList<Seat>> seats, int row, int col)
    {
        Seat seat;
        System.out.println(" Select Seats");
        for (int i = 0; i < (1 + col) * 3 / 2 - 8; i++)
            System.out.println(" ");
        System.out.println("|      Screen       |");
        for (int i = 0; i < (1 + col) * 3 / 2 - 8; i++) {
            System.out.println(" ");
        }
        System.out.println("---------------------");

        System.out.println("    ");
        int new_row = 0;
        for(int i=0; i<col; i++){
            if (new_row != col / 2 - 1) {
                System.out.println(i + 1);
            } else {
                System.out.println("  ");
                i--;
            }
            new_row++;
        }

        System.out.println("  ");
        boolean flag = false;
        for(int i =0; i<row; i++)
        {
            new_row = 0;
            System.out.println(i + 1);
            for(int j=0; j<col; j++)
            {
                if (new_row != col / 2 - 1) {
                    seat = seats.get(i).get(j);
                    //Seat not available
                    if (seat.isOcccupied()) {
                        System.out.println("[X]");
                    }
                    //Seat selected
                    else if (seat.isSelected()) {
                        System.out.println("[#]");
                    }
                    //Seats available
                    else
                        System.out.println("[ ]");
                }
                else {
                    System.out.println("   ");
                    j--;
                }
                new_row++;
            }
            System.out.println(" ");
            System.out.println(i + 1);
            System.out.println(" ");
        }

        System.out.println(" ");
        for (int i = 0; i < (1 + col) * 3 / 2 - 5; i++)
            System.out.println(" ");
        System.out.println("----------");
        for (int i = 0; i < (1 + col) * 3 / 2 - 5; i++)
            System.out.println(" ");
        System.out.println("|Entrance|\n");
        System.out.println("([ ] Available  [#] Seat Selected  [X] Sold)");
    }

     */

    // Method to ask user to select a row and column,check whether the seat is available, and update the information in the data base
    /*
    private Seat chooseSeats(ArrayList<ArrayList<Seat>> seats, int row, int col) {
        System.out.println("Please choose your seat(s).");
        int i,j;
        do {
            i = readSeatInput("Please input row number",1,row);
            j = readSeatInput("Please input col number",1,col);
            i--;j--;
            if (seats.get(i).get(j).isOcccupied() || seats.get(i).get(j).isSelected())
                System.out.println("Already been taken/selected please choose another seats.");
            else break;
        } while (true);

        seats.get(i).get(j).setSelected(true);
        System.out.println("Selected Seat: Row: " + (i+1) + " Col: " + (j+1));

        return seats.get(i).get(j);
    }

     */
}
