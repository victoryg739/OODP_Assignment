package view.admin;

import controller.MovieController;
import view.MenuBase;

import java.util.Scanner;

import static view.utilF.readIntInput;

public class MenuStaffMovieListFunction extends MenuBase {
    MovieController mc = new MovieController();

    public MenuStaffMovieListFunction(MenuBase initialMenu) {
        super(initialMenu);
    }


    public MenuBase execute() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("\n\nCreate/Update/Remove Movie: \n\n" +
                "1. Create Movie Listing\n" +
                "2. Update Movie Listing\n" +
                "3. Remove Movie Listing\n" +
                "5. Return to Main Menu\n" +
                "4. Testing Purposes\n\n" +
                "Select option: ");
        int choice = sc.nextInt();
        MenuBase nextMenu = this;
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
            case 4:
                nextMenu = this.getPreviousMenu();
                break;

        }

        return nextMenu;
    }


    }





