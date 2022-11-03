package view.admin;

import controller.MovieController;
import modal.Enums;
import modal.Movie;
import view.MenuBase;

import java.util.ArrayList;
import java.util.Collections;

public class MenuStaffTopFiveSales extends MenuBase {
    MovieController ac = new MovieController();
    public MenuStaffTopFiveSales(MenuBase initialMenu) {
        super(initialMenu);
    }

    @Override
    public MenuBase execute() {
        // Reads the whole movie list from database to movieList arrayList
        ArrayList<Movie> movieList = ac.read();
        
        try {
            sortTicketSales(movieList);

            int top = 1;
            for (Movie movie : movieList) {
                if(movie.getShowingStatus() != Enums.ShowingStatus.END_SHOWING && movie.getShowingStatus() != Enums.ShowingStatus.COMING_SOON)
                {
                    System.out.println(movie.getTitle() + " | " + movie.getTicketSales());
                    if (top++ == 5) {
                        break;
                    }
                }

            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return this.getPreviousMenu();
    }
    private void sortTicketSales(ArrayList<Movie> movies) {
        Collections.sort(movies, (m1, m2) -> (m1.getTicketSales() - m2.getTicketSales()));
        Collections.reverse(movies);
    }
}
