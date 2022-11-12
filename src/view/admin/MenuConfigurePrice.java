package view.admin;

import controller.PriceManager;
import modal.Enums;
import modal.Enums.*;
import modal.Price;
import view.MenuBase;

import static view.utilF.*;

public class MenuConfigurePrice extends MenuBase {
    public PriceManager priceManager = new PriceManager();
    public MenuConfigurePrice(MenuBase initialMenu) {
        super(initialMenu);
    }
    public MenuBase execute() {
        Enums.AgeType ageType;
        Enums.MovieType movieType;
        Enums.ClassCinema classCinema;
        Enums.Day day;
        double newPrice;
        printHeader("Configuring Price");
        Price price = priceManager.read();
        System.out.println(price.getPrice(0));

        classCinema =  readClassCinema("Choose Cinema Class: \n" +
        "1. Platinum\n" +
        "2. Normal");

        ageType =  readAgeType("Choose Age Type: \n" +
                "1. Normal\n" +
                "2. Student\n"+
                "3. Senior");

        movieType =  readMovieTypeInput("Choose Movie Type: \n" +
                "1. 2D\n" +
                "2. 3D\n" +
                "3. BlockBuster");

        day =  readDay("Choose Day: \n" +
                "1. Monday to Wednesday(Before 6pm)\n" +
                "2. Monday to Wednesday(After 6pm)\n" +
                "3. Thursday(Before 6pm)\n" +
                "4. Thursday(after 6pm)\n" +
                "5. Friday(Before 6pm)\n" +
                "6. Friday(after 6pm)\n" +
                "7. Saturday and Sunday");
        newPrice = readIntInput("New price: ");
        priceManager.updateTicketPrice(ageType,movieType,classCinema,day,newPrice);
        println("Successfully Updated Price");
        price = priceManager.read();
        System.out.println(price.getPrice(0));


        return getPreviousMenu();
    }


}