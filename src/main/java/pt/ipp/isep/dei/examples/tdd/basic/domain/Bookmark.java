package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bookmark {

    private String URL;
    private String tag;
    private int rating;
    private String date;


    public Bookmark (String URL){
        this.URL = URL;
        this.tag = "";
        this.rating = 0;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now);
    }

    public Bookmark (String URL, String tag){
        this.URL = URL;
        this.tag = tag;
        this.rating = 0;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now);
    }


    public String getURL() {
        return this.URL;
    }

    public String getTag(){
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getRating(){
        return this.rating;
    }

    public void increaseRating() {
        this.rating++;
    }

    public String getDate() {
        return this.date;
    }

    public void printBookmark(){
        System.out.println("Rating: " + this.rating + ", URL: " + this.URL);
    }
}
