package view.admin;

import controller.SettingController;
import view.MenuBase;

import static view.utilF.*;

public class MenuMovieGoerDisplay extends MenuBase {
    SettingController sc = new SettingController();
    public MenuMovieGoerDisplay(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        MenuBase nextMenu = this;
        printHeader("Filtering MovieGoer's display");
        sc.printSettings();
        print("1. Enable/Disable Ticket sales\n" +
              "2. Enable/Disable Review ratings \n" +
              "3. Back\n");
        int choice = readIntInput("Enter choice: ");
        switch(choice){
            case 1:
                nextMenu = new FilterTicketSales(this);
                break;
            case 2:
                nextMenu = new FilterReviewRatings(this);
                break;
            default:
                nextMenu = this.getPreviousMenu();
                break;
        }
        return nextMenu;
    }
}
