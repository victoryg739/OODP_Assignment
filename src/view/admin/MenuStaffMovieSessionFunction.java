package view.admin;

import view.MenuBase;

import java.util.Scanner;
/**
 * Menu to list all the Session configuration
 *
 * @author Victor Yoong
 * @version 1.0
 * @since 2022-08-11
 */
public class MenuStaffMovieSessionFunction extends MenuBase {

    public MenuStaffMovieSessionFunction(MenuBase initialMenu) {
        super(initialMenu);
    }


    /**
     * Ask user to choice which movie function to use
     * and bring user to next stage of application
     * Consist of four menu
     * 1. Create Movie Session
     * 2. Update Movie Session
     * 3. Remove Movie Session
     * 4. Return to Main menu
     *
     * @return return to the corresponding menu that the user has selected
     */
    public MenuBase execute() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("\n\nCreate/Update/Remove Movie Session: \n\n" +
                "1. Create Movie Session\n" +
                "2. Update Movie Session\n" +
                "3. Remove Movie Session\n" +
                "4. Return to Main Menu\n\n" +
                "Select option: ");
        int choice = sc.nextInt();
        MenuBase nextMenu = this;
        switch (choice) {
            case 1:
                nextMenu = new MenuStaffMovieSessionCreate(this);
                break;
            case 2:
                nextMenu = new MenuStaffMovieSessionUpdate(this);
                break;
            case 3:
                nextMenu = new MenuStaffMovieSessionRemove(this);
                break;
            case 4:
                nextMenu = this.getPreviousMenu();
                break;

        }

        return nextMenu;
    }


}








