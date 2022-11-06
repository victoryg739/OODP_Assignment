package view.admin;

import controller.MovieController;
import modal.Enums;
import modal.Movie;
import view.MenuBase;
import view.Quit;

import java.util.ArrayList;
import java.util.Collections;

import static view.utilF.*;

public class MenuStaffTopFiveSales extends MenuBase {
    MovieController mc = new MovieController();
    public MenuStaffTopFiveSales(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        MenuBase nextMenu;
        printHeader("Top 5 Movies by Sales");
        mc.listTopSalesBySales();
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
