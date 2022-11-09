package controller;

import model.Constant;
import model.Enums;
import model.Price;

import java.io.*;


public class PriceManager {
    /**
     * if is blockbuster then we add blockbuster price
     *
     * @param movieType This is the enum movie type
     * @param price     This is the price
     * @return the calculated price
     */
    public double isBlockbuster(Enums.MovieType movieType, double price) {
        if (movieType == Enums.MovieType.BLOCKBUSTER) {
            return price + 1;
        } else {
            return price;
        }
    }

    /**
     * Calculate the ticket price based on the parameters
     *
     * @param ageType       This is age of moviegoer
     * @param movieType     This is the type of movie
     * @param classCinema   This is the cinema class
     * @param day           This is the range of the day
     * @param showingStatus This is the showing status
     * @param loyaltyCard   This is to see whether there is loyalty card
     * @param holiday       This is to see whether the session is a holiday
     * @return the calculated price
     */
    public double calculateTicketPrice(Enums.AgeType ageType, Enums.MovieType movieType, Enums.ClassCinema classCinema, Enums.Day day, Enums.ShowingStatus showingStatus, Boolean loyaltyCard, Boolean holiday) {
        File f = new File(Constant.PRICEFILE);
        Price price = new Price();
        if (!f.exists()) {
            replace(price);
        }

        price = read();

        if (showingStatus == Enums.ShowingStatus.PREVIEW) {
            return price.getPrice(20);
        }

        if (loyaltyCard == true) {
            return price.getPrice(21);
        }

        if (classCinema == Enums.ClassCinema.PLATINUM) {
            if (day == Enums.Day.FRI_BEF_SIX || day == Enums.Day.FRI_AFT_SIX || day == Enums.Day.SAT_SUN) { // Friday to Sunday
                return price.getPrice(0);
            } else { //Monday to Thursday
                return price.getPrice(1);
            }
        } else {
            if (ageType == Enums.AgeType.NORMAL || holiday == true) {
                if (movieType == Enums.MovieType.TWO_D || movieType == Enums.MovieType.BLOCKBUSTER) {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.MON_WED_AFT_SIX) {
                        return price.getPrice(2);
                    } else if (day == Enums.Day.THU_BEF_SIX || day == Enums.Day.THU_AFT_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        return price.getPrice(3);
                    } else { //friday after 6 and saturday and sunday
                        return price.getPrice(4);
                    }
                } else { // 3d
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.MON_WED_AFT_SIX || day == Enums.Day.THU_AFT_SIX) {
                        return price.getPrice(5);
                    } else { //friday to sunday
                        return price.getPrice(6);
                    }
                }

            } else if (ageType == Enums.AgeType.STUDENT) {
                if (movieType == Enums.MovieType.TWO_D || movieType == Enums.MovieType.BLOCKBUSTER) {
                    if ((day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.FRI_BEF_SIX)) {
                        return price.getPrice(7);
                    } else if (day == Enums.Day.MON_WED_AFT_SIX) {
                        return price.getPrice(8);
                    } else if (day == Enums.Day.THU_AFT_SIX) {
                        return price.getPrice(9);
                    } else { //friday after 6 and saturday and sunday
                        return price.getPrice(10);
                    }
                } else {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        return price.getPrice(11);
                    } else if (day == Enums.Day.MON_WED_AFT_SIX || day == Enums.Day.THU_AFT_SIX) {
                        return price.getPrice(12);
                    } else { //friday after 6 and saturday and sunday
                        return price.getPrice(13);
                    }
                }
            } else { //This is senior
                if (movieType == Enums.MovieType.TWO_D || movieType == Enums.MovieType.BLOCKBUSTER) {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        return price.getPrice(14);
                    } else if (day == Enums.Day.MON_WED_AFT_SIX) {
                        return price.getPrice(15);
                    } else if (day == Enums.Day.THU_AFT_SIX) {
                        return price.getPrice(16);
                    } else { //friday after 6 and saturday and sunday
                        return price.getPrice(17);
                    }
                } else { // 3d
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.MON_WED_AFT_SIX || day == Enums.Day.THU_AFT_SIX) {
                        return price.getPrice(18);
                    } else { //friday to sunday
                        return price.getPrice(19);
                    }
                }

            }

        }

    }

    /**
     * Update the ticket price based on the parameters
     *
     * @param ageType       This is age of moviegoer
     * @param movieType     This is the type of movie
     * @param classCinema   This is the cinema class
     * @param day           This is the range of the day
     * @param showingStatus This is the showing status
     * @param loyaltyCard   This is to see whether there is loyalty card
     */
    public void updateTicketPrice(Enums.AgeType ageType, Enums.MovieType movieType, Enums.ClassCinema classCinema, Enums.Day day, Enums.ShowingStatus showingStatus, Boolean loyaltyCard, double newPrice) {
        File f = new File(Constant.PRICEFILE);

        Price price = new Price();
        price = read();

        if (showingStatus == Enums.ShowingStatus.PREVIEW) {
            price.setPrice(20, newPrice);
        }

        if (loyaltyCard == true) {
            price.setPrice(21, newPrice);
        }

        if (classCinema == Enums.ClassCinema.PLATINUM) {
            if (day == Enums.Day.FRI_BEF_SIX || day == Enums.Day.FRI_AFT_SIX || day == Enums.Day.SAT_SUN) { // Friday to Sunday
                price.setPrice(0, newPrice);
            } else { //Monday to Thursday
                price.setPrice(1, newPrice);
            }
        } else {
            if (ageType == Enums.AgeType.NORMAL) {
                if (movieType == Enums.MovieType.TWO_D) {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.MON_WED_AFT_SIX) {
                        price.setPrice(2, newPrice);
                    } else if (day == Enums.Day.THU_BEF_SIX || day == Enums.Day.THU_AFT_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        price.setPrice(3, newPrice);
                    } else { //friday after 6 and saturday and sunday
                        price.setPrice(4, newPrice);
                    }
                } else { // 3d
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.MON_WED_AFT_SIX || day == Enums.Day.THU_AFT_SIX) {
                        price.setPrice(5, newPrice);
                    } else { //friday to sunday
                        price.setPrice(6, newPrice);
                    }
                }

            } else if (ageType == Enums.AgeType.STUDENT) {
                if (movieType == Enums.MovieType.TWO_D) {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        price.setPrice(7, newPrice);
                    } else if (day == Enums.Day.MON_WED_AFT_SIX) {
                        price.setPrice(8, newPrice);
                    } else if (day == Enums.Day.THU_AFT_SIX) {
                        price.setPrice(9, newPrice);
                    } else { //friday after 6 and saturday and sunday
                        price.setPrice(10, newPrice);
                    }
                } else {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        price.setPrice(11, newPrice);
                    } else if (day == Enums.Day.MON_WED_AFT_SIX || day == Enums.Day.THU_AFT_SIX) {
                        price.setPrice(12, newPrice);
                    } else { //friday after 6 and saturday and sunday
                        price.setPrice(13, newPrice);
                    }
                }
            } else if (ageType == Enums.AgeType.SENIOR) {
                if (movieType == Enums.MovieType.TWO_D) {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        price.setPrice(14, newPrice);
                    } else if (day == Enums.Day.MON_WED_AFT_SIX) {
                        price.setPrice(15, newPrice);
                    } else if (day == Enums.Day.THU_AFT_SIX) {
                        price.setPrice(16, newPrice);
                    } else { //friday after 6 and saturday and sunday
                        price.setPrice(17, newPrice);
                    }
                } else { // 3d
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.MON_WED_AFT_SIX || day == Enums.Day.THU_AFT_SIX) {
                        price.setPrice(18, newPrice);
                    } else { //friday to sunday
                        price.setPrice(19, newPrice);
                    }
                }

            }

        }
        replace(price);
    }

    /**
     * Read and return Price in the Database file
     *
     * @return database of Price    if empty return empty price object
     */
    public Price read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.PRICEFILE));
            Price price = (Price) ois.readObject();
            ois.close();
            return price;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error

        }
        return new Price();
    }

    /**
     * Replace price database file
     *
     * @param data price object
     */
    public void replace(Price data) {
        File tempFile = new File(Constant.PRICEFILE);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constant.PRICEFILE));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            //
        }
    }
}
