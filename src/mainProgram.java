import controller.CinemaController;
import controller.CineplexController;
//import controller.MovieController;
import controller.MovieController;
import modal.*;
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
        MovieController mc = new MovieController();

        ArrayList<String> casts = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            casts.add("Bryan");
        }

        Movie movie0 = new Movie(0, "Spiderman", Enums.MovieType.TWO_D,Enums.ShowingStatus.PREVIEW ,Enums.MovieRating.M18, "Away from you", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie1 = new Movie(1, "Ironman", Enums.MovieType.THREE_D, Enums.ShowingStatus.PREVIEW,Enums.MovieRating.M18, "Ironman is the best", 150, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie2 = new Movie(2, "Chickenman", Enums.MovieType.TWO_D, Enums.ShowingStatus.PREVIEW, Enums.MovieRating.M18, "Fuck you victor", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie3 = new Movie(3, "Allahman", Enums.MovieType.BLOCKBUSTER, Enums.ShowingStatus.PREVIEW,Enums.MovieRating.G, "Allahu akbar", 300, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie4 = new Movie(4, "Hohoman", Enums.MovieType.TWO_D, Enums.ShowingStatus.PREVIEW,Enums.MovieRating.NC16, "Away from you", 200, readDate(), readDate(), "Bryan Tay", casts);
        Movie movie5 = new Movie(5, "Aloyman", Enums.MovieType.TWO_D, Enums.ShowingStatus.PREVIEW,Enums.MovieRating.NC16, "Away from you", 200, readDate(), readDate(), "Bryan Tay", casts);

        mc.createMovie(movie0);
        mc.createMovie(movie1);
        mc.createMovie(movie2);
        mc.createMovie(movie3);
        mc.createMovie(movie4);
        mc.createMovie(movie5);

        mc.updateMovie(11, 0, 600);
        mc.updateMovie(11, 1, 200);
        mc.updateMovie(11, 2, 300);
        mc.updateMovie(11, 3, 400);
        mc.updateMovie(11, 4, 500);
        mc.updateMovie(11, 5, 100);

        Cinema cinema1  = new Cinema("A1",null, Enums.ClassCinema.PLATINUM,null);
        Cinema cinema2  = new Cinema("A2",null, Enums.ClassCinema.PLATINUM,null);
        Cinema cinema3 =  new Cinema("A3",null, Enums.ClassCinema.NORMAL,null);
        Cinema cinema4  = new Cinema("A4",null, Enums.ClassCinema.PLATINUM,null);
        Cinema cinema5  = new Cinema("A5",null, Enums.ClassCinema.PLATINUM,null);
        Cinema cinema6 =  new Cinema("A6",null, Enums.ClassCinema.NORMAL,null);
        Cinema cinema7 = new Cinema("A7",null, Enums.ClassCinema.PLATINUM,null);
        Cinema cinema8 =  new Cinema("A8",null, Enums.ClassCinema.NORMAL,null);
        Cinema cinema9 =  new Cinema("A9",null, Enums.ClassCinema.NORMAL,null);

        ArrayList<Cinema> cinemaArrayList1 = new ArrayList<Cinema>();
        cinemaArrayList1.add(cinema1);
        cinemaArrayList1.add(cinema2);
        cinemaArrayList1.add(cinema3);

        ArrayList<Cinema> cinemaArrayList2 = new ArrayList<Cinema>();
        cinemaArrayList2.add(cinema4);
        cinemaArrayList2.add(cinema5);
        cinemaArrayList2.add(cinema6);

        ArrayList<Cinema> cinemaArrayList3 = new ArrayList<Cinema>();
        cinemaArrayList3.add(cinema7);
        cinemaArrayList3.add(cinema8);
        cinemaArrayList3.add(cinema9);

        ArrayList<Cinema> cinemaArrayListAll = new ArrayList<Cinema>();
        cinemaArrayListAll.addAll(cinemaArrayList1);
        cinemaArrayListAll.addAll(cinemaArrayList2);
        cinemaArrayListAll.addAll(cinemaArrayList3);
        CinemaController cinemaCtrler = new CinemaController();
        cinemaCtrler.replace(cinemaArrayListAll);

        Cineplex cineplex1 = new Cineplex("Jem",cinemaArrayList1);
        Cineplex cineplex2 = new Cineplex("Orchard",cinemaArrayList2);
        Cineplex cineplex3 = new Cineplex("Funan",cinemaArrayList3);
        ArrayList<Cineplex> cineplexArrayList = new ArrayList<Cineplex>();
        cineplexArrayList.add(cineplex1);
        cineplexArrayList.add(cineplex2);
        cineplexArrayList.add(cineplex3);


        CineplexController cinplexCtrler = new CineplexController();
        cinplexCtrler.replace(cineplexArrayList);

        ArrayList<Cinema> cinemaFile = cinemaCtrler.read();
        for(int a =0 ; a<cinemaFile.size();a++){
            System.out.print(cinemaFile.get(a).getClassCinema() + "\t");
            System.out.print(cinemaFile.get(a).getCinemaNo()+ "\t");
            System.out.print(cinemaFile.get(a).getSessions()+ "\t");
            System.out.printf("\n");
        }

        ArrayList<Cineplex> cineplexFile = cinplexCtrler.read();
        for(int i =0; i< cineplexFile.size();i++){ //return one section by one for the whole session file
            System.out.print(cineplexFile.get(i).getLocation()+ "\t" );
            System.out.print(cineplexFile.get(i).getCinemas()+ "\t");
            ArrayList<Cinema> cinemasA = new ArrayList<Cinema>();
            cinemasA= cineplexFile.get(i).getCinemas();


            for(int  j=0; j<cinemasA.size();j++){
                System.out.print(cinemasA.get(j).getCinemaNo()+ "\t");

            }
            System.out.printf("\n");


            //GIVe aloy later
        }


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

