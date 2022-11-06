package view.admin;

import controller.PriceManager;
import view.MenuBase;


import java.util.*;
import static view.utilF.*;

public class MenuStaffConfigureSettings extends MenuBase {

    private PriceManager priceCtrl = new PriceManager();
    public MenuStaffConfigureSettings(MenuBase initialMenu) {
        super(initialMenu);
    }

    public MenuBase execute() {
        int choice;
        MenuBase nextMenu = this;
        printHeader("Configure System Settings");
        print("1. Configure Price \n" +
                "2. Configure Holiday \n" +
                "3. Configure new admin account \n" +
                "4. Configure Moviegoer's display info\n" +
                "5. Back\n");
        choice = readIntInput("Enter Choice: ");

        switch (choice) {
            case 1:
                //nextMenu = new ConfigurePriceMenu(this);
                break;
            case 2:
                nextMenu = new MenuConfigureHoliday(this);
                break;
            case 3:
                nextMenu = new MenuStaffRegister(this);
                break;
            case 4:
                nextMenu = new MenuMovieGoerDisplay(this);
                break;
            case 5:
                nextMenu = this.getPreviousMenu();
                break;

        }

        return nextMenu;
    }
}
