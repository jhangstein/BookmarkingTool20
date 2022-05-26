package pt.ipp.isep.dei.examples.tdd.basic.domain;

public class Bookmark {

    private String URL;
    private String tag;
    private int rating;


    public Bookmark (String URL){
        this.URL = URL;
        this.tag = "";
        this.rating = 0;
    }

    public Bookmark (String URL, String tag){
        this.URL = URL;
        this.tag = tag;
        this.rating = 0;
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
        throw new UnsupportedOperationException();
    }
}
