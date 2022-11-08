package view.admin;

import controller.MovieController;
import view.MenuBase;
import view.Quit;

import static view.utilF.*;

public class MenuStaffTopFiveRating extends MenuBase {
    MovieController mc = new MovieController();

    public MenuStaffTopFiveRating(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * List all the top 5 movies by rating which is sorted
     *
     * @return return to the corresponding menu that the user has selected
     */

    @Override
    public MenuBase execute() {
        MenuBase nextMenu;
        printHeader("Top 5 Movies by Rating");
        mc.listTopByRating();
        print("1. Go back to Previous Menu\n" +
                "2. Quit\n");
        int choice = readIntInput("Choice: ");
        switch (choice) {
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
