import controller.*;
import model.*;
import view.MainMenu;
import view.MenuBase;
import view.Quit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static view.utilF.readDate;
/* ToDo:
    1. create directory
    */



/*

The start of the program
 */

public class mainProgram {
    public final static String FILENAME = "data/movies.txt";

    public static void main(String[] args) throws IOException, AddressException, MessagingException {
        /* For Testing Purposes */
        File myObj = new File("data/settings.txt");
        myObj.createNewFile();
        // If File exists:
        File f = new File(FILENAME);
        if (!f.exists()) {

            MovieController mc = new MovieController();

            ArrayList<String> casts0 = new ArrayList<>();
            casts0.add("Dwayne Johnson, Aldis Hodge, Noah Centineo, Sarah Shahi, Marwan Kenzari, Quintessa Swindell");

            ArrayList<String> casts1 = new ArrayList<>();
            casts1.add("Hong Huifang, Jung Dong-Hwan, Kang Hyung Suk, Yeo Jingoo");

            ArrayList<String> casts2 = new ArrayList<>();
            casts2.add("Zheng Ge Ping 郑各评, Vincent Ng 翁清海, Fattah Amin 法达·阿敏, Dato Rosyam Nor 罗斯彦诺, Tien Hsin 天心, Zhu Hou Ren 朱厚任, Henley Hii 许亮宇, Jack Neo 梁志强, Pablo Amirul 帕罗·阿米鲁");

            ArrayList<String> casts3 = new ArrayList<>();
            casts3.add("Chutimon Chuengcharoensukying (Aokbab), Waruntorn Paonil (Ink), VioletteI Wautier, Phantira Pipityakorn (Minnie), Arachaporn Pokinpakorn (Goy), Jennis Oprasert, Sutatta Udomsilp (Punpun), Praewa Suthamphong (Music), Eisaya Hosuwan (Oom), Sawanya Paisarnpayak (Nana)");

            ArrayList<String> casts4 = new ArrayList<>();
            casts4.add("Jacqueline Byers, Colin Salmon, Virginia Madsen, Ben Cross, Christian Navarro");

            ArrayList<String> casts5 = new ArrayList<>();
            casts5.add("Ryu Seung-ryong, Yum Jung-ah, Ong Seong-wu, Park Sewan");

            ArrayList<String> casts6 = new ArrayList<>();
            casts6.add("Letitia Wright, Tenoch Huerta, Martin Freeman, Lupita Nyong'o, Danai Gurira, Winston Duke");

            ArrayList<String> casts7 = new ArrayList<>();
            casts7.add("Mark, Renjun, Jeno, Haechan, Jaemin, Chenle, Jisung");

            ArrayList<String> casts8 = new ArrayList<>();
            casts8.add("Fuka Koshiba, Kazuma Kawamura, Mario Kuroba");

            ArrayList<String> casts9 = new ArrayList<>();
            casts9.add("Sam Worthington, Zoe Saldana, Sigourney Weaver, Kate Winslet, Vin Diesel, Stephen Lang");

            ArrayList<String> casts10 = new ArrayList<>();
            casts10.add("Antonio Banderas, Salma Hayek, Olivia Colman, Harvey Guillén");

            Movie movie0 = new Movie(0, "Black Adam", Enums.MovieType.BLOCKBUSTER, Enums.ShowingStatus.NOW_SHOWING, Enums.MovieRating.PG13, "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods -- and imprisoned just as quickly -- Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.", 125, readDate(), readDate(), "Jaume Collet-Serra", casts0);
            Movie movie1 = new Movie(1, "Ajoomma", Enums.MovieType.TWO_D, Enums.ShowingStatus.NOW_SHOWING, Enums.MovieRating.NC16, "Produced by award-winning filmmaker Anthony Chen. Auntie (Hong Huifang), is a middle-aged Singaporean woman who has dedicated the best years of her life to caring for her family. Now widowed with her grown up son, Sam (Shane Pow) about to fly the roost, Auntie is left to contend with a whole new identity beyond her roles of daughter, wife, and mother.", 90, readDate(), readDate(), "He Shuming", casts1);
            Movie movie2 = new Movie(2, "Deleted", Enums.MovieType.TWO_D, Enums.ShowingStatus.NOW_SHOWING, Enums.MovieRating.NC16, "The story starts with a Malaysian police detective – Chia Zhong Yi. In his desperate search for his daughter Hazel who was being kidnapped by child traffickers. He unintentionally caused grievous hurt to a male suspect in a moment of rashness. As a consequence of his actions, he was convicted and sentenced to 3 years in prison. Nevertheless, he never gave up hope in finding his daughter. Exploiting his status as an ex-convict, he infiltrated the crime syndicate and befriended a human trafficker Ghost, to find out about his daughter’s whereabouts.", 86, readDate(), readDate(), "Ken Ng Lai Huat", casts2);
            Movie movie3 = new Movie(3, "Faces Of Anne", Enums.MovieType.TWO_D, Enums.ShowingStatus.NOW_SHOWING, Enums.MovieRating.NC16, "Everyone is called “Anne” and they are all being chased! n Faces of Anne, Anne takes you on a terrifying mystery journey with secrets lurking in the dark corners. When all the girls named Anne wakes up with fuzzy memories which the doctors and nurses keep injecting and saying it is mental symptoms that they think to themselves, along with hypnosis to make them remember who they are.", 116, readDate(), readDate(), "Kongdej Jaturanrasamee", casts3);
            Movie movie4 = new Movie(4, "Prey For The Devil", Enums.MovieType.TWO_D, Enums.ShowingStatus.NOW_SHOWING, Enums.MovieRating.NC16, "Sister Ann (Jacqueline Byers) believes she is answering a calling to be the first female exorcist… but who, or what, called her? ", 93, readDate(), readDate(), "Daniel Stamm", casts4);
            Movie movie5 = new Movie(5, "Life Is Beautiful", Enums.MovieType.TWO_D, Enums.ShowingStatus.NOW_SHOWING, Enums.MovieRating.PG13, "Se-yeon, who has devoted her entire life to her family as a mother and wife, suddenly learns she is gravely ill. Now determined to live for herself more than ever, ‘Se-yeon’ leaves in search of her ‘first love’ from her high school. Though unhappy with his wife’s decision, ‘Se-yeon’s stern husband, ‘Jin-bong’, goes along on her search to honor her earnest wishes. Through their journey together, ‘Se-yeon’ and ‘Jin-bong’ are reminded of the most glittering, beautiful moments of their lives.", 122, readDate(), readDate(), "Choi Kook-hee", casts5);
            Movie movie6 = new Movie(6, "Marvel Studios' Black Panther: Wakanda Forever", Enums.MovieType.BLOCKBUSTER, Enums.ShowingStatus.PREVIEW, Enums.MovieRating.PG13, "In Marvel Studios’ “Black Panther: Wakanda Forever,” Queen Ramonda (Angela Bassett), Shuri (Letitia Wright), M’Baku (Winston Duke), Okoye (Danai Gurira) and the Dora Milaje (including Florence Kasumba), fight to protect their nation from intervening world powers in the wake of King T’Challa’s death.", 161, readDate(), readDate(), "Ryan Coogler", casts6);
            Movie movie7 = new Movie(7, "NCT DREAM THE MOVIE: In A DREAM", Enums.MovieType.TWO_D, Enums.ShowingStatus.PREVIEW, Enums.MovieRating.PG13, "The brand-new film features high-energy performances with the seven members captured during their second solo concert, “NCT DREAM TOUR 'THE DREAM SHOW2 : In A DREAM,” at the iconic Olympic Stadium, the largest stadium in South Korea and the dream venue for music artists.", 120, readDate(), readDate(), "Margo Yeji Lee", casts7);
            Movie movie8 = new Movie(8, "Sadako Dx", Enums.MovieType.TWO_D, Enums.ShowingStatus.COMING_SOON, Enums.MovieRating.NC16, "The Ring Curse mutates and spreads. Ayaka Ichijo (Fuka Koshiba) is a graduate student with an IQ of 200 who tries to investigate the strange deaths happening nationwide after people supposedly watched a cursed video.", 100, readDate(), readDate(), "Hisashi Kimura", casts8);
            Movie movie9 = new Movie(9, "Avatar: The Way Of Water", Enums.MovieType.BLOCKBUSTER, Enums.ShowingStatus.COMING_SOON, Enums.MovieRating.PG13, "Set more than a decade after the events of the first film, Avatar: The Way of Water begins to tell the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.", 190, readDate(), readDate(), "James Cameron", casts9);
            Movie movie10 = new Movie(10, "Puss In Boots: The Last Wish", Enums.MovieType.THREE_D, Enums.ShowingStatus.COMING_SOON, Enums.MovieRating.PG13, "This fall, everyone's favorite leche-loving, swashbuckling, fear-defying feline returns. For the first time in more than a decade, DreamWorks Animation presents a new adventure in the Shrek universe as daring outlaw Puss in Boots discovers that his passion for peril and disregard for safety have taken their toll. Puss has burned through eight of his nine lives, though he lost count along the way. Getting those lives back will send Puss in Boots on his grandest quest yet.", 102, readDate(), readDate(), "Joel Crawford", casts10);

            mc.createMovie(movie0);
            mc.createMovie(movie1);
            mc.createMovie(movie2);
            mc.createMovie(movie3);
            mc.createMovie(movie4);
            mc.createMovie(movie5);
            mc.createMovie(movie6);
            mc.createMovie(movie7);
            mc.createMovie(movie8);
            mc.createMovie(movie9);
            mc.createMovie(movie10);


            mc.updateMovie(11, 0, 600);
            mc.updateMovie(11, 1, 200);
            mc.updateMovie(11, 2, 300);
            mc.updateMovie(11, 3, 400);
            mc.updateMovie(11, 4, 500);
            mc.updateMovie(11, 5, 100);
            mc.updateMovie(11, 6, 800);
            mc.updateMovie(11, 7, 800);
            mc.updateMovie(11, 8, 700);
            mc.updateMovie(11, 9, 500);
            mc.updateMovie(11, 10, 600);

            Cinema cinema3 = new Cinema(10, 10, "A3", Enums.ClassCinema.NORMAL, null);
            CustomerController customerController = new CustomerController();
            Customer customer = new Customer("a", "a", "riven", "999");
            int temp = customer.getCustomerID();
            //System.out.println(temp);
            customer.setCustomerID(0);
            temp = customer.getCustomerID();
            //System.out.println(temp);
            customerController.createCustomer(customer);
            ArrayList<Ticket> tickets = new ArrayList<>();
            Date currentTime = Calendar.getInstance().getTime();
            Session tempSession = new Session(0, 0, cinema3, movie0, 0, currentTime, Enums.Day.FRI_AFT_SIX);
            //Create the booking transaction
            BookingController bookingController = new BookingController();
//            Booking booking = new Booking("A1", "tid",
//                    customer.getUsername(), movie0, tickets, tempSession, 12.50);
//            bookingController.create(booking);
        }

        File fCinema = new File("data/cinema.txt");

        if (!fCinema.exists()) {

            Cinema cinema1 = new Cinema(6, 6, "A1", Enums.ClassCinema.PLATINUM, null);
            Cinema cinema2 = new Cinema(10, 10, "A2", Enums.ClassCinema.PLATINUM, null);
            Cinema cinema3 = new Cinema(10, 10, "A3", Enums.ClassCinema.NORMAL, null);
            Cinema cinema4 = new Cinema(10, 10, "A4", Enums.ClassCinema.PLATINUM, null);
            Cinema cinema5 = new Cinema(10, 10, "A5", Enums.ClassCinema.PLATINUM, null);
            Cinema cinema6 = new Cinema(10, 10, "A6", Enums.ClassCinema.NORMAL, null);
            Cinema cinema7 = new Cinema(10, 10, "A7", Enums.ClassCinema.PLATINUM, null);
            Cinema cinema8 = new Cinema(10, 10, "A8", Enums.ClassCinema.NORMAL, null);
            Cinema cinema9 = new Cinema(10, 10, "A9", Enums.ClassCinema.NORMAL, null);

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

            Cineplex cineplex1 = new Cineplex("Jem", cinemaArrayList1, null);
            Cineplex cineplex2 = new Cineplex("Orchard", cinemaArrayList2, null);
            Cineplex cineplex3 = new Cineplex("Funan", cinemaArrayList3, null);
            ArrayList<Cineplex> cineplexArrayList = new ArrayList<Cineplex>();
            cineplexArrayList.add(cineplex1);
            cineplexArrayList.add(cineplex2);
            cineplexArrayList.add(cineplex3);


            CineplexController cinplexCtrler = new CineplexController();
            cinplexCtrler.replace(cineplexArrayList);
        }

        // Create Root Admin Account
        Admin rootAdmin = new Admin("a", "a");
        AdminController ac = new AdminController();
        ac.createAdmin(rootAdmin);

        // Creating Customer account to test
        Customer testCustomer = new Customer("twz", "twz", "rivenbryan@gmail.com", "999");
        CustomerController cc = new CustomerController();
        cc.createCustomer(testCustomer);

        MenuBase nextMenu = new MainMenu(null);

        do {
            nextMenu = nextMenu.execute();

        } while (!(nextMenu instanceof Quit));
    }
}

