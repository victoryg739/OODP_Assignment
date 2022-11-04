package view.admin;

import controller.HolidayController;
import view.MenuBase;

import java.util.Date;

import static view.utilF.*;

public class MenuHolidayRemove extends MenuBase {
    private HolidayController holCtrl = new HolidayController();
    public MenuHolidayRemove(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        printHeader("Delete Holiday");
        if(holCtrl.listAllHolidays()) {
            Date holiday = readDate("Enter holiday date to delete: ");
            if (!holCtrl.isHoliday(holiday)) {
                print("Holiday does not exist!");
                return this.getPreviousMenu();
            }
            holCtrl.delete(holiday);
            println("Successfully deleted!");
        }
        return this.getPreviousMenu();
    }
}
