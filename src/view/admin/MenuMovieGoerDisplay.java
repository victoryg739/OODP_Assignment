package view.admin;

import controller.SettingController;
import view.MenuBase;

import static view.utilF.*;
/**
 Menu to Configure for admin to filter moviegoer's display
 @author Bryan Tay
 @version 1.0
 @since 2022-08-11
 */
public class MenuMovieGoerDisplay extends MenuBase {
    SettingController sc = new SettingController();
    public MenuMovieGoerDisplay(MenuBase initialMenu) {
        super(initialMenu);
    }


    /**
     * Display options that Admin can enable/disable sales/ratings
     * Ask user which options they want to enable/disable
     * and bring user to next stage of application
     * Consist of four menu
     * 1. Enable/Disable Ticket sales
     * 2. Enable/Disable Review ratings
     * 3. Back
     *
     * @return return to the corresponding menu that the user has selected
     */

    @Override
    public MenuBase execute() {
        MenuBase nextMenu;
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
