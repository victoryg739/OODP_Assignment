package view.admin;

import controller.HolidayController;
import view.MenuBase;

import java.util.Date;

import static view.utilF.readDate;

public class MenuHolidayUpdate extends MenuBase {
    private HolidayController holCtrl = new HolidayController();

    public MenuHolidayUpdate(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {

        if (holCtrl.listAllHolidays()) {
            Date oldHoliday = readDate("Select Holiday to be updated: ");
            if (!holCtrl.isHoliday(oldHoliday)) {
                System.out.println("Holiday does not exist!\n");
                return this.getPreviousMenu();
            }
            Date newHoliday = readDate("Change Holiday date to: ");
            holCtrl.update(oldHoliday, newHoliday);
            System.out.println("Successfully updated");

        }
        return this.getPreviousMenu();
    }
}
