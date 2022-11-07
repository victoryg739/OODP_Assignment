package view.admin;

import controller.MovieController;
import view.MenuBase;

import java.util.Scanner;

import static view.utilF.printHeader;
import static view.utilF.readIntInput;

public class MenuStaffMovieListFunction extends MenuBase {
    MovieController mc = new MovieController();

    public MenuStaffMovieListFunction(MenuBase initialMenu) {
        super(initialMenu);
    }


    public MenuBase execute() {
        MenuBase nextMenu ;

        printHeader("Create/Update/Remove Movies");
        int choice = readIntInput(
                "1. Create Movie Listing\n" +
                "2. Update Movie Listing\n" +
                "3. Remove Movie Listing\n" +
                "4. Return to Main Menu\n" +
                "Select option: ");


        switch(choice) {
            case 1:
                nextMenu = new MenuStaffMovieCreate(this);
                break;
            case 2:
                nextMenu = new MenuStaffMovieUpdate(this);
                break;
            case 3:
                nextMenu = new MenuStaffMovieRemove(this);
                break;
            default:
                nextMenu = this.getPreviousMenu();
                break;

        }
        return nextMenu;
    }


    }





