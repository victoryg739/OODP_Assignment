package view.admin;

import controller.AdminController;
import view.MenuBase;

import static view.utilF.read;

public class MenuStaffLogin extends MenuBase {
    public MenuStaffLogin(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {

        System.out.println("Please login using username and password");
        final String username = read("Username: ");
        final String password = read("Password: ");
        AdminController adminCtrl = new AdminController();

        // Authenticate Username and Password
        if (adminCtrl.authenticate(username, password)){
            System.out.println("Welcome "+username);
            MenuBase nextMenu = new MenuStaffMain(this);
            return nextMenu;
        }
        else {
            System.out.println("Wrong username or password");
        }


        return this.getPreviousMenu();
    }
}
