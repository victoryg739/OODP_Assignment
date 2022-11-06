package view.admin;
import controller.HolidayController;
import modal.Constant;
import view.MenuBase;
import java.util.Date;
import static view.utilF.*;

/* Todo:
*   1. Check if it updates same date
* */
public class MenuHolidayUpdate extends MenuBase {
    private HolidayController holCtrl = new HolidayController();

    public MenuHolidayUpdate(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        printHeader("Updating holiday");
        if (holCtrl.listAllHolidays()) {
            Date oldHoliday = readDate("Select Holiday to be updated: ", Constant.FORMAT_DATE_LONG);
            if (!holCtrl.isHoliday(oldHoliday)) {
                print("Holiday does not exist!");
                return this.getPreviousMenu();
            }
            Date newHoliday = readDate("Change Holiday date to: ");
            holCtrl.update(oldHoliday, newHoliday);
            println("Successfully updated!");

        }
        return this.getPreviousMenu();
    }
}
