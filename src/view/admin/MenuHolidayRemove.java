package view.admin;

import controller.HolidayController;
import modal.Constant;
import view.MenuBase;

import java.util.Date;

import static view.utilF.*;

/**
 Menu to Remove holiday
 @author Bryan Tay
 @version 1.0
 @since 2022-08-11
 */

public class MenuHolidayRemove extends MenuBase {
    private HolidayController holCtrl = new HolidayController();
    public MenuHolidayRemove(MenuBase initialMenu) {
        super(initialMenu);
    }

    /**
     * Display remove holiday menu
     * Ask user to input information which holiday they would want to delete
     * and bring user back to configure menu.
     *
     * @return configure menu
     */
    @Override
    public MenuBase execute() {
        printHeader("Delete Holiday");
        if(holCtrl.listAllHolidays()) {
            Date holiday = readDate("Enter holiday date to delete: ", Constant.FORMAT_DATE_LONG);
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
