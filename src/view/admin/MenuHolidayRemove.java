package view.admin;

import controller.HolidayController;
import view.MenuBase;

import java.util.Date;

import static view.utilF.readDate;

public class MenuHolidayRemove extends MenuBase {
    private HolidayController holCtrl = new HolidayController();
    public MenuHolidayRemove(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {

        if(holCtrl.listAllHolidays()) {
            Date holiday = readDate("Enter holiday date to delete: ");
            if (!holCtrl.isHoliday(holiday)) {
                System.out.println("Holiday does not exist!\n");
                return this.getPreviousMenu();
            }
            holCtrl.delete(holiday);
        }
        return this.getPreviousMenu();
    }
}
