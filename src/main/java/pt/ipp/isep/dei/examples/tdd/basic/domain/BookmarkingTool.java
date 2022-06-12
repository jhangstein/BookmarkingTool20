package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.apache.commons.validator.routines.UrlValidator;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * BookmarkingTool class.
 * This class provides functionalities to create bookmarking files, validate URLs and adding them,
 * check for protocols used, group the URLs by keyword, and filter them by keyword. It also
 * adds means to sort the bookmarks and check for domain associations.
 */
public class BookmarkingTool {

    List<Bookmark> allBookmarks = new ArrayList<>();


    public BookmarkingTool() {
    }


    /**
     * Method adds a bookmark to the tool if the provided URL is valid and it isn't already added.
     * If it isn't already in the tool, the bookmark gets added and its rating set to one.
     * @param bm    Bookmark to be added
     */
    public void addBookmark(Bookmark bm){
        UrlValidator validator = new UrlValidator();
        if (validator.isValid(bm.getURL()) && checkForDuplicate(bm)){
            bm.increaseRating();
            allBookmarks.add(bm);
        }
    }

    /**
     * Method removes a bookmark if its URL matches the provided URL.
     * @param URL   URL of bookmark to be deleted
     */
    public void removeBookmark(String URL){
        for (Bookmark bm: allBookmarks){
            if (URL.equals(bm.getURL())){
                allBookmarks.remove(bm);
            }
        }
        //allBookmarks.removeIf(bm -> bm.getURL().equals(URL));
    }


    /**
     * Method checks for duplicate bookmarks in the tool. If it finds a duplicate, it increases that bookmarks
     * rating by one.
     * @param bm    Bookmark to be checked for duplicate
     * @return      True if no duplicate can be found, false if one is found
     */
    public boolean checkForDuplicate(Bookmark bm){
        for (Bookmark bookmark: allBookmarks){
            if (bookmark.getURL().equals(bm.getURL())){
                bookmark.increaseRating();
                return false;
            }
        }
        return true;
    }


    /**
     * Method gets all bookmark's URLs using the HTTPS protocol.
     * @return  count of secure URLs using HTTPS
     */
    public int getSecureUrlCount(){
        int count = 0;
        for (Bookmark bm: allBookmarks){
            if (bm.getURL().contains("https")) {
                count++;
            }
        }
        return count;
    }


    /**
     * Method filters the tool by provided keyword, and groups matching items in a new list.
     * @param tag   keyword/tag to filter by
     * @return      list with all associated bookmarks
     */
    public List<Bookmark> filterByKeyword(String tag){
        List<Bookmark> keywordList = new ArrayList<>();

        for (Bookmark bm: allBookmarks){
            if (bm.getTag().equals(tag)){
                keywordList.add(bm);
            }
        }
        return keywordList;
    }


    /**
     * Method removes a tag from a provided URL.
     * @param URL   URL of which the associated tag should be deleted
     */
    public void removeTag(String URL){
        for (Bookmark bm: allBookmarks){
            if (bm.getURL().equals(URL)){
                bm.setTag("");
            }
        }
    }


    /**
     * Method sorts all bookmarks by their rating in descending order.
     * @return  the list sorted by rating
     */
    public List<Bookmark> listSortedByRating(){
        List<Bookmark> sortedList = allBookmarks;
        sortedList.sort(Comparator.comparingInt(Bookmark::getRating).reversed());
        // removed, as this only serves in the CLI application
        /*for (Bookmark bm: allBookmarks){
            bm.printBookmark();
        }
        */
        return sortedList;
    }


    /**
     * Method sorts all bookmarks by their date from new to old.
     * @return  the list sorted by date
     */
    public List<Bookmark> listSortedByDate(){
        List<Bookmark> sortedList = allBookmarks;
        sortedList.sort(Comparator.comparing(Bookmark::getDate).reversed());
        return sortedList;
    }


    /**
     * Method gets the domain name of all bookmarks, and returns those matching the
     * domain in the provided URL.
     * @param url   URL of which associated domains should be found
     * @return  list with all associated domains
     * @throws MalformedURLException    When the URL is invalid
     */
    public List<Bookmark> getAssociatedDomains(String url) throws MalformedURLException {
        List<Bookmark> associated = new ArrayList<>();
        URL provided = new URL(url);
        for (Bookmark bm: allBookmarks){
            URL check = new URL(bm.getURL());
            if (check.getHost().equals(provided.getHost())){
                associated.add(bm);
            }

        }
        return associated;
    }
}
