package view.admin;

import controller.HolidayController;
import modal.Constant;
import view.MenuBase;

import java.util.Date;

import static modal.Constant.dateFormatLong;
import static view.utilF.*;

public class MenuHolidayCreate extends MenuBase {
    private HolidayController holCtrl = new HolidayController();
    public MenuHolidayCreate(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        printHeader("Creating holiday");
        String newName = read("Input New Holiday Name: ");
        Date holiday = readDate("Enter holiday date:", Constant.FORMAT_DATE_LONG);
        if (holCtrl.isHoliday(holiday)) {
            println("Holiday already exists!");
            return this.getPreviousMenu();
        }
        holCtrl.create(newName,holiday);
        println("Successfully added holiday!");
        return this.getPreviousMenu();
    }
}
