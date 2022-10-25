package view.admin;

import view.MenuBase;

import java.util.Scanner;

public class MenuStaffMovieListFunction extends MenuBase {


    public MenuStaffMovieListFunction(MenuBase initialMenu) {
        super(initialMenu);
    }


    public MenuBase execute() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("\n\nCreate/Update/Remove Movie: \n\n" +
                "1. Create Movie Listing\n" +
                "2. Update Movie Listing\n" +
                "3. Remove Movie Listing\n" +
                "4. Return to Main Menu\n\n" +
                "Select option: ");
        int choice = sc.nextInt();
        MenuBase nextMenu = this;
        switch(choice) {
            case 1:
                nextMenu = new MenuStaffMovieCreate(this);
                break;
            case 2:
                //updateMovie();
                break;
            case 3:
                //removeMovie();
                break;
            case 4:
                nextMenu = this.getPreviousMenu();
                break;

        }

        return nextMenu;
    }


    }





