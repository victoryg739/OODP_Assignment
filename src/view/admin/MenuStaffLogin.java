package view.admin;

import controller.AdminController;
import view.MenuBase;

import static view.utilF.print;
import static view.utilF.*;

public class MenuStaffLogin extends MenuBase {
    public MenuStaffLogin(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        printHeader("Authentication");
        print("Please login using username and password");
        final String username = read("Username: ");
        final String password = read("Password: ");
        AdminController adminCtrl = new AdminController();

        // Authenticate Username and Password
        if (adminCtrl.authenticate(username, password)){
            return new MenuStaffMain(this, username);
        }
        else {
            println("Wrong username or password");
        }
        return this.getPreviousMenu();
    }
}
