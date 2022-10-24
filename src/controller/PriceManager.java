package controller;

import modal.Enums;

public class PriceManager {

    public double calculateTicketPrice(Enums.AgeType ageType,Enums.MovieType movieType, Enums.ClassCinema classCinema, Enums.Day day) {
        if (classCinema == Enums.ClassCinema.PLATINUM) {

        } else {
            if (ageType == Enums.AgeType.NORMAL) {
                if (movieType == Enums.MovieType.TWO_D) {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.MON_WED_AFT_SIX) {
                        return 8.50;
                    } else if (day == Enums.Day.THU_BEF_SIX || day == Enums.Day.THU_AFT_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        return 9.50;
                    } else { //friday after 6 and saturday and sunday
                        return 11;
                    }
                } else { // 3d
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.MON_WED_AFT_SIX || day == Enums.Day.THU_AFT_SIX) {
                        return 11;
                    } else { //friday to sunday
                        return 15;
                    }
                }

            } else if (ageType == Enums.AgeType.STUDENT) {
                if (movieType == Enums.MovieType.TWO_D) {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        return 7;
                    } else if (day == Enums.Day.MON_WED_AFT_SIX) {
                        return 8.50;
                    } else if (day == Enums.Day.THU_AFT_SIX) {
                        return 9.50;
                    } else { //friday after 6 and saturday and sunday
                        return 11;
                    }
                } else {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        return 9;
                    } else if (day == Enums.Day.MON_WED_AFT_SIX || day == Enums.Day.THU_AFT_SIX) {
                        return 11;
                    } else { //friday after 6 and saturday and sunday
                        return 15;
                    }
                }
            } else if (ageType == Enums.AgeType.SENIOR) {
                if (movieType == Enums.MovieType.TWO_D) {
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.FRI_BEF_SIX) {
                        return 4;
                    } else if (day == Enums.Day.MON_WED_AFT_SIX) {
                        return 8.50;
                    } else if (day == Enums.Day.THU_AFT_SIX) {
                        return 9.50;
                    } else { //friday after 6 and saturday and sunday
                        return 11;
                    }
                }  else { // 3d
                    if (day == Enums.Day.MON_WED_BEF_SIX || day == Enums.Day.THU_BEF_SIX || day == Enums.Day.MON_WED_AFT_SIX || day == Enums.Day.THU_AFT_SIX) {
                        return 11;
                    } else { //friday to sunday
                        return 15;
                    }
                }

            }

        }
        return -9999999;

    }

}
