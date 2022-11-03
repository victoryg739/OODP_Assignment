package view.admin;

import view.MenuBase;

import static view.utilF.readIntInput;

public class MenuStaffMovieL extends MenuBase {
    public MenuStaffMovieL(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
            MenuBase nextMenu = this;
            int choice;
            choice = readIntInput("======================= Staff Menu =======================\n" +
                    " 1. Show Top 5 by sale                              \n" + //shows what movies there are
                    " 2. Show Top 5 by rating                               \n" + //shows the timing of each movie
                    " 3. Back                                                               \n " +
                    "===========================================================");
            switch (choice) {
                case 1:
                    // List movies (STAFF)
                    nextMenu = new MenuStaffTopFiveSales(this);

                    break;
                case 2:
                    //nextMenu = new MenuStaffMovieSessionFunction(this);
                    break;
                case 3:
                    nextMenu = this.getPreviousMenu();
                    break;
                default:
                    nextMenu = this.getPreviousMenu();
                    break;
            }
            return nextMenu;
        }
    }

