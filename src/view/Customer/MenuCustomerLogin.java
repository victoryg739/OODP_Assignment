package view.Customer;

import controller.AdminController;
import controller.CustomerController;
import modal.Customer;
import modal.Movie;
import view.MenuBase;
import view.admin.MenuStaffMain;

import static view.utilF.*;

public class MenuCustomerLogin extends MenuBase{

    private int a;
    private Movie movie;

    public MenuCustomerLogin(MenuBase initialMenu, int a, Movie movie) {
        super(initialMenu);
        this.a = a;
        this.movie = movie;
    }

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

                switch (a) {
                    case 1:
                        nextMenu = new MenuPurchaseTicket(this, movie, username);
                        break;
                    case 2:
                        nextMenu = new MenuBookingHistory(this, username);
                        break;
                }
            }
            else{
                println("Wrong username or password");
            }
        }
        else {
            nextMenu = new MenuCustomerRegister(this);
        }

        return nextMenu;
    }
}
