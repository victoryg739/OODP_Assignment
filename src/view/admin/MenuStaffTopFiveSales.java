package view.admin;

import controller.MovieController;
import modal.Enums;
import modal.Movie;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Collections;

import static view.utilF.printHeader;

public class MenuStaffTopFiveSales extends MenuBase {
    MovieController mc = new MovieController();
    public MenuStaffTopFiveSales(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        printHeader("Top 5 Movies by Sales");
        mc.listTopSalesBySales();
        return this.getPreviousMenu();
    }
}
