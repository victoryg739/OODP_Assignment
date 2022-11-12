package view.admin;

import view.MenuBase;

import static view.utilF.printHeader;
import static view.utilF.readIntInput;


/**
 * Menu to list all the Movie configuration
 *
 * @author Bryan Tay
 * @version 1.0
 * @since 2022-08-11
 */
public class MenuStaffMovieListFunction extends MenuBase {
    public MenuStaffMovieListFunction(MenuBase initialMenu) {
        super(initialMenu);
    }


    public MenuBase execute() {
        MenuBase nextMenu;

        /**
         * Ask user to choice which movie function to use
         * and bring user to next stage of application
         * Consist of four menu
         * 1. Create movie listing
         * 2. Update movie listing
         * 3. Remove movie listing
         * 4. Return to Main menu
         *
         * @return return to the corresponding menu that the user has selected
         */

        printHeader("Create/Update/Remove Movies");
        int choice = readIntInput(
                "1. Create Movie Listing\n" +
                        "2. Update Movie Listing\n" +
                        "3. Remove Movie Listing\n" +
                        "4. Return to Main Menu\n" +
                        "Select option: ");

        switch (choice) {
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





