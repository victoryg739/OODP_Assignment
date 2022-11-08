package view.admin;

import view.MenuBase;

import java.util.Scanner;

public class MenuStaffMovieSessionFunction extends MenuBase {

    public MenuStaffMovieSessionFunction(MenuBase initialMenu) {
        super(initialMenu);
    }


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








