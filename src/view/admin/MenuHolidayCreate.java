package view.admin;

import controller.HolidayController;
import modal.Constant;
import view.MenuBase;

import java.util.Date;

import static modal.Constant.dateFormatLong;
import static view.utilF.*;

/**
 Menu to Create holiday
 @author Bryan Tay
 @version 1.0
 @since 2022-08-11
 */


public class MenuHolidayCreate extends MenuBase {
    private HolidayController holCtrl = new HolidayController();
    public MenuHolidayCreate(MenuBase initialMenu) {
        super(initialMenu);
    }


    /**
     * Display create holiday menu
     * Ask user to input information about the holiday name and date
     * and bring user back to configure menu.
     *
     * @return configure menu
     */
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
