package model;

// Might need to change MovieType - Blockbuster
public class Enums {

    public enum AgeType {
        SENIOR,
        STUDENT,
        NORMAL
    }

    public enum MovieRating {
        G,
        PG13,
        NC16,
        M18,
        R21


    }

    public enum MovieType {
        TWO_D,
        THREE_D,
        BLOCKBUSTER

    }

    public enum ClassCinema {
        PLATINUM,
        NORMAL
    }

    public enum Day {
        MON_WED_BEF_SIX,
        MON_WED_AFT_SIX,
        THU_BEF_SIX,
        THU_AFT_SIX,
        FRI_BEF_SIX,
        FRI_AFT_SIX,
        SAT_SUN,
        PH
    }

    public enum ShowingStatus {

        COMING_SOON("Coming Soon"),
        PREVIEW("Preview"),
        NOW_SHOWING("Now Showing"),
        END_SHOWING("Showing Ended");

        private String s;

        ShowingStatus(String s) {
            this.s = s;
        }

        public String toString() {
            return this.s;
        }
    }


}
