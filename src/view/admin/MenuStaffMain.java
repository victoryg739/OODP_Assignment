package view.admin;

import view.MainMenu;
import view.MenuBase;

import static view.utilF.*;

public class MenuStaffMain extends MenuBase {
    String username;
    public MenuStaffMain(MenuBase initialMenu, String username){
        super(initialMenu);
        this.username = username;
    }

    public MenuBase execute(){
        int choice;
        MenuBase nextMenu;

        printHeader("Staff Menu");
        println("Welcome to MOblima Adminstrator Panel: " + username);
        print(" 1. Create/Update/Remove Movie Listing                               \n" +
              " 2. Create/Update/Remove Movie Session                               \n" +
              " 3. Configure system settings                                          \n" +
              " 4. List movies                                                        \n" +
              " 5. Back                                                               \n ");
        choice = readIntInput("Enter choice:");

        switch (choice) {
            case 1:
                nextMenu = new MenuStaffMovieListFunction(this);
                break;
            case 2:
                nextMenu = new MenuStaffMovieSessionFunction(this);
                break;
            case 3:
                nextMenu = new MenuStaffConfigureSettings(this);
                break;
            case 4:
                nextMenu = new MenuStaffMovieList(this);
                break;
            default:
                nextMenu = new MainMenu(this);
                break;
        }
        return nextMenu;
    }
}
