package view.admin;

import view.MenuBase;

import static view.utilF.*;

/**
 Configure for admin to list movies based on top 5 sales/rating
 @author Bryan Tay
 @version 1.0
 @since 2022-08-11
 */

public class MenuStaffMovieList extends MenuBase {
    public MenuStaffMovieList(MenuBase initialMenu) {
        super(initialMenu);
    }


    /**
     * Display staff main menu for listing
     * consists of 2 menus
     * 1. Show Top 5 by sales
     * 2. Show top 5 by rating
     * @return corresponding menu that the user has selected
     */
    @Override
    public MenuBase execute() {
            MenuBase nextMenu;
            int choice;
            printHeader("Staff Menu");
            print(" 1. Show Top 5 by sales\n" +
                    " 2. Show Top 5 by rating\n" +
                    " 3. Back\n");
        choice = readIntInput("Enter choice:");
            switch (choice) {
                case 1:
                    nextMenu = new MenuStaffTopFiveSales(this);
                    break;
                case 2:
                    nextMenu = new MenuStaffTopFiveRating(this);
                    break;
                default:
                    nextMenu = this.getPreviousMenu();
                    break;
            }
            return nextMenu;
        }
    }

