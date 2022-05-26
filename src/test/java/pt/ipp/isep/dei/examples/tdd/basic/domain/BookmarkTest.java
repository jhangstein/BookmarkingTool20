package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkTest {

    @Test
    public void createBookmarkWithURL(){
        String bookmark = "https://github.com";
        Bookmark bm = new Bookmark(bookmark);

        String compare = bm.getURL();

        assertEquals(bookmark, compare);
    }


    @Test
    public void createBookmarkWithTag(){
        String URL = "https://github.com";
        String tag = "favorites";
        Bookmark bm = new Bookmark(URL, tag);

        String compare = bm.getTag();

        assertEquals(tag, compare);
    }
}