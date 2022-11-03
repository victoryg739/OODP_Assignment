package view.admin;

import controller.HolidayController;
import view.MenuBase;

import java.util.Date;

import static view.utilF.read;
import static view.utilF.readDate;

public class MenuHolidayCreate extends MenuBase {
    private HolidayController holCtrl = new HolidayController();
    public MenuHolidayCreate(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        String newName = read("Input New Holiday Name: ");
        Date holiday = readDate("Enter holiday date:");
        if (holCtrl.isHoliday(holiday)) {
            System.out.println("Holiday already exists!\n");
            return this.getPreviousMenu();
        }
        holCtrl.create(newName,holiday);
        System.out.println("Successfully added holiday!");
        return this.getPreviousMenu();
    }
}
