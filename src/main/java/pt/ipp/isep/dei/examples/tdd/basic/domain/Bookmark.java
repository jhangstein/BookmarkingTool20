package pt.ipp.isep.dei.examples.tdd.basic.domain;

public class Bookmark {

    private String URL;
    private String tag;


    public Bookmark (String URL){
        this.URL = URL;
    }

    public Bookmark (String URL, String tag){

    }


    public String getURL() {
        return this.URL;
    }

    public String getTag(){
        throw new UnsupportedOperationException();
    }
}
