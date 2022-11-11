package view.admin;

import controller.AdminController;
import view.MenuBase;

import static view.utilF.*;


/**
 * Menu Page which prompts Staff to login
 *
 * @author Tan Wei Zhong
 * @version 1.0
 * @since 2022-08-11
 */
public class MenuStaffLogin extends MenuBase {
    public MenuStaffLogin(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * Display Staff Login menu
     * Prompts Staff whether he has an account
     * Authenticates Username and Password input with the ones Datafile
     * Redirects User to either Staff Main Menu the Previous Menu depending on whether User successfully authenticates
     *
     * @return MenuStaffMain || getPreviousMenu
     */
    public MenuBase execute() {
        printHeader("Authentication");
        print("Please login using username and password");
        final String username = read("Username: ");
        final String password = read("Password: ");
        AdminController adminCtrl = new AdminController();

        // Authenticate Username and Password
        if (adminCtrl.authenticate(username, password)) {
            return new MenuStaffMain(this, username);
        } else {
            println("Wrong username or password");
        }
        return this.getPreviousMenu();
    }
}
