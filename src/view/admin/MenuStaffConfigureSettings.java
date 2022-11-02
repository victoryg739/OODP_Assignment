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

        choice = readIntInput("\nConfigure System Settings: \n" +
                "1.  Configure Price \n" +
                "2.  Configure Holiday \n +" +
                "3. Back                \n");

        switch (choice) {
            case 1:
                //nextMenu = new ConfigurePriceMenu(this);
                break;
            case 2:
                nextMenu = new MenuConfigureHoliday(this);
                break;
            case 3:
                nextMenu = this.getPreviousMenu();
                break;

        }

        return nextMenu;
    }
}
