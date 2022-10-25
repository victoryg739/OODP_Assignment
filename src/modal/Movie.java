package modal;

import java.io.Serializable;

public class Movie implements Serializable {
    /**
     * This movie's unique ID
     */
    private int id;

    /**
     * This movie's title
     */
    private String title;

    public Movie(String title){
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
