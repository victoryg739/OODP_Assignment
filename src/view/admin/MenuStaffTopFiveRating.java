package view.admin;

import controller.MovieController;
import modal.Enums;
import modal.Movie;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static view.utilF.print;
import static view.utilF.printHeader;

public class MenuStaffTopFiveRating extends MenuBase {
    MovieController mc = new MovieController();
    public MenuStaffTopFiveRating(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        printHeader("Top 5 Movies by Rating");
        mc.listTopSalesByRating();
        return this.getPreviousMenu();
    }


}
