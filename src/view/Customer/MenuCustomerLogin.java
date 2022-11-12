package view.Customer;

import controller.CustomerController;
import model.Customer;
import model.Movie;
import view.MenuBase;

import static view.utilF.*;

/**
 * Menu Page which prompts Customer to login
 *
 * @author Tan Wei Zhong
 * @version 1.0
 * @since 2022-08-11
 */
public class MenuCustomerLogin extends MenuBase {

    private int a;
    private Movie movie;

    public MenuCustomerLogin(MenuBase initialMenu, int a, Movie movie) {
        super(initialMenu);
        this.a = a;
        this.movie = movie;
    }

    /**
     * Display Customer Login menu
     * Prompts Customer whether he has an account
     * Authenticates Username and Password input with the ones Datafile
     * Redirects User to either Purchase Ticket Menu, Booking History Menu or Customer Registration Menu
     *
     * @return MenuPurchaseTicket || MenuBookingHistory || MenuCustomerRegister
     */
    public MenuBase execute() {

        MenuBase nextMenu = this;

        printHeader("Authentication");

        if (confirm("Do you have an account ?")) {
            print("Please login using username and password");
            final String username = read("Username: ");
            final String password = read("Password: ");
            CustomerController custCtrl = new CustomerController();

            // Authenticate Username and Password
            if (custCtrl.authenticate(username, password)) {
                Customer customer = custCtrl.readByUsername(username);
                switch (a) {
                    case 1:
                        nextMenu = new MenuPurchaseTicket(this, movie, username);
                        break;
                    case 2:
                        nextMenu = new MenuBookingHistory(this, username);
                        break;
                }
            } else {
                println("Wrong username or password");
            }
        } else {
            nextMenu = new MenuCustomerRegister(this);
        }

        return nextMenu;
    }
}
