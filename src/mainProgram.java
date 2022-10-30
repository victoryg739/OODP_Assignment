import controller.MovieController;
import modal.Enums;
import modal.Movie;
import view.MainMenu;
import view.MenuBase;
import view.Quit;

import java.util.ArrayList;

import static view.utilF.*;


/*

The start of the program
 */

public class mainProgram {
    public static void main(String[] args) {

        /* For Testing Purposes */
        ArrayList<String> casts = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            casts.add("Bryan");
        }

        Movie movie0 = new Movie(0, "Spiderman", Enums.MovieType.TWO_D, Enums.MovieRating.M18, "Away from you", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie1 = new Movie(1, "Ironman", Enums.MovieType.THREE_D, Enums.MovieRating.M18, "Ironman is the best", 150, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie2 = new Movie(2, "Chickenman", Enums.MovieType.TWO_D, Enums.MovieRating.M18, "Fuck you victor", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie3 = new Movie(3, "Allahman", Enums.MovieType.BLOCKBUSTER, Enums.MovieRating.G, "Allahu akbar", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie4 = new Movie(4, "Hohoman", Enums.MovieType.TWO_D, Enums.MovieRating.NC16, "Away from you", 200, readDate(), readDate(), "Bryan Tay", casts);

        MovieController mc = new MovieController();
        mc.createMovie(movie0);
        mc.createMovie(movie1);
        mc.createMovie(movie2);
        mc.createMovie(movie3);
        mc.createMovie(movie4);


        // Create first Main Menu
        MainMenu mm = new MainMenu(null);
        // Upcast to a MenuBase
        MenuBase nextMenu = mm;

        // Loop until a user quits the Menu
        do{
            nextMenu =  nextMenu.execute();
        }while(!(nextMenu instanceof Quit));
    }
}

