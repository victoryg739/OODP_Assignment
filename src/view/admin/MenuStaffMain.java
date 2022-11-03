package view.admin;

import modal.Movie;
import view.MainMenu;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static view.utilF.*;

public class MenuStaffMain extends MenuBase {
    public MenuStaffMain(MenuBase initialMenu){
        super(initialMenu);
    }

    public MenuBase execute(){
        int choice;
        MenuBase nextMenu;

        printHeader("Staff Menu");
        print(" 1. Create/Update/Remove Movie Listing                               \n" +
              " 2. Create/Update/Remove Movie Session                               \n" +
              " 3. Configure system settings                                          \n" +
              " 4. List movies                                                        \n" +
              " 5. Back                                                               \n ");
        choice = readIntInput("Enter choice");

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
                nextMenu = new MenuStaffMovieL(this);
                break;
            default:
                nextMenu = new MainMenu(this);
                break;
        }
        return nextMenu;
    }
}
