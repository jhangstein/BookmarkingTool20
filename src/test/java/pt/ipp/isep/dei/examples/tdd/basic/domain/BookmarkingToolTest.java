package pt.ipp.isep.dei.examples.tdd.basic.domain;

//import jdk.jfr.Description;
import org.apache.commons.validator.routines.UrlValidator;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookmarkingToolTest {

    // NEW TESTS (old ones will be kept around for now to reference).

    @Test
    public void ensureURLIsValid(){
        String URL = "http://github.com";
        boolean valid = false;

        BookmarkingTool bt = new BookmarkingTool();
        Bookmark bm = new Bookmark(URL);
        bt.addBookmark(bm);

        valid = bt.allBookmarks.contains(bm);

        assertTrue(valid);
    }

    @Test
    public void ensureURLIsInvalid(){
        String URL = "htt://github.com";
        boolean valid = false;

        BookmarkingTool bt = new BookmarkingTool();
        Bookmark bm = new Bookmark(URL);
        bt.addBookmark(bm);

        valid = bt.allBookmarks.contains(bm);

        assertFalse(valid);
    }

    @Test
    public void ensureNoDuplicatesExistInBookmarkingTool(){
        String URL1 = "https://github.com";
        String URL2 = "https://github.com";
        String tag = "favorites";
        int expected = 1;
        int actual = 0;

        BookmarkingTool bt = new BookmarkingTool();
        Bookmark bm1 = new Bookmark(URL1, tag);
        Bookmark bm2 = new Bookmark(URL2, tag);
        bt.addBookmark(bm1);
        bt.addBookmark(bm2);

        for (Bookmark bookmark: bt.allBookmarks){
            if (bookmark.getURL().equals("https://github.com")){
                actual++;
            }
        }

        assertEquals(expected, actual);
    }


    @Test
    public void ensureRatingIsIncreased(){
        String URL1 = "https://github.com";
        String URL2 = "https://github.com";
        String tag = "favorites";
        int expected = 2;
        int actual = 0;

        BookmarkingTool bt = new BookmarkingTool();
        Bookmark bm1 = new Bookmark(URL1, tag);
        Bookmark bm2 = new Bookmark(URL2, tag);
        bt.addBookmark(bm1);
        bt.addBookmark(bm2);

        actual = bt.allBookmarks.get(0).getRating();

        assertEquals(expected, actual);
    }


    @Test
    public void ensureRequestReturnsAllSecureUrls(){
        String URL1 = "https://github.com";
        String URL2 = "https://facebook.com";
        String URL3 = "http://test.com";
        String tag = "favorites";
        int expected = 2;
        int actual = 0;

        BookmarkingTool bt = new BookmarkingTool();
        Bookmark bm1 = new Bookmark(URL1, tag);
        Bookmark bm2 = new Bookmark(URL2, tag);
        Bookmark bm3 = new Bookmark(URL3, tag);
        bt.addBookmark(bm1);
        bt.addBookmark(bm2);
        bt.addBookmark(bm3);

        actual = bt.getSecureUrlCount();

        assertEquals(expected, actual);

    }


    @Test
    public void ensureFilteringReturnsEveryExpectedBookmark(){
        String URL1 = "https://github.com";
        String URL2 = "https://facebook.com";
        String URL3 = "http://test.com";
        String tag = "favorites";
        String tag2 = "boomer";
        int expected = 2;
        int actual = 0;

        BookmarkingTool bt = new BookmarkingTool();
        Bookmark bm1 = new Bookmark(URL1, tag);
        Bookmark bm2 = new Bookmark(URL2, tag2);
        Bookmark bm3 = new Bookmark(URL3, tag);
        bt.addBookmark(bm1);
        bt.addBookmark(bm2);
        bt.addBookmark(bm3);

        actual = bt.filterByKeyword("favorites").size();

        assertEquals(expected, actual);
    }


    @Test
    public void ensureDeletedBookmarksAreRemoved(){
        String URL1 = "https://github.com";
        String URL2 = "https://facebook.com";
        String URL3 = "http://test.com";
        String tag = "favorites";
        boolean result = false;

        BookmarkingTool bt = new BookmarkingTool();
        Bookmark bm1 = new Bookmark(URL1, tag);
        Bookmark bm2 = new Bookmark(URL2, tag);
        Bookmark bm3 = new Bookmark(URL3, tag);
        bt.addBookmark(bm1);
        bt.addBookmark(bm2);
        bt.addBookmark(bm3);

        bt.removeBookmark("https://facebook.com");

        for (Bookmark bm: bt.allBookmarks){
            if (bm.getURL().equals("https://facebook.com")){
                result = true;
                break;
            }
        }

        assertFalse(result);
    }


    @Test
    public void ensureTagsAreProperlyDeleted(){
        String URL1 = "https://github.com";
        String URL2 = "https://facebook.com";
        String URL3 = "http://test.com";
        String tag = "favorites";
        boolean result = false;

        BookmarkingTool bt = new BookmarkingTool();
        Bookmark bm1 = new Bookmark(URL1, tag);
        Bookmark bm2 = new Bookmark(URL2, tag);
        Bookmark bm3 = new Bookmark(URL3, tag);
        bt.addBookmark(bm1);
        bt.addBookmark(bm2);
        bt.addBookmark(bm3);

        bt.removeTag("https://facebook.com");

        List<Bookmark> filteredList = bt.filterByKeyword("favorites");
        for (Bookmark bm: filteredList){
            if (bm.getURL().equals("https://facebook.com")){
                result = true;
                break;
            }
        }

        assertFalse(result);
    }


    @Test
    public void ensureRatingListingIsCorrectAndInDescendingOrder(){
        Bookmark bm = new Bookmark("https://facebook.com");
        Bookmark bm2 = new Bookmark("https://google.com");
        Bookmark bm3 = new Bookmark("https://wappler.com");
        BookmarkingTool bt = new BookmarkingTool();
        int expected = 6;
        int actual = 0;

        for (int i = 0; i < 15; i++){
            bt.addBookmark(bm);
            if (i<6) bt.addBookmark(bm2);
            if (i<10) bt.addBookmark(bm3);
        }

        // The third element in the sorted list should be bm2 with a rating of 6.
        // Expected Output:
        //   0   Rating: 15, URL: https://facebook.com
        //   1   Rating: 10, URL: https://wappler.com
        //   2   Rating: 6, URL: https://google.com
        actual = bt.listSortedByRating().get(2).getRating();

        assertEquals(expected, actual);
    }
}