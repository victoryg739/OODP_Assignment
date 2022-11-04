package view.admin;

import view.MenuBase;

import static view.utilF.*;

public class MenuStaffMovieList extends MenuBase {
    public MenuStaffMovieList(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
            MenuBase nextMenu;
            int choice;
            printHeader("Staff Menu");
            print(" 1. Show Top 5 by sale\n" +
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

