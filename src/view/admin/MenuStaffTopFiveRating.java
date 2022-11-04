package view.admin;

import controller.MovieController;
import modal.Enums;
import modal.Movie;
import view.MenuBase;
import view.Quit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static view.utilF.*;

public class MenuStaffTopFiveRating extends MenuBase {
    private int userID;
    MovieController mc = new MovieController();
    public MenuStaffTopFiveRating(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        MenuBase nextMenu;
        printHeader("Top 5 Movies by Rating");
        mc.listTopSalesByRating();
        print("1. Go back to Previous Menu\n" +
                "2. Quit\n");
        int choice = readIntInput("Choice: ");
        switch(choice){
            case 2:
                nextMenu = new Quit(this);
                System.out.println("Thank you for using our MOBLIMA APP");
                break;
            default:
                nextMenu = getPreviousMenu();
                break;
        }
        return nextMenu;
    }


}
