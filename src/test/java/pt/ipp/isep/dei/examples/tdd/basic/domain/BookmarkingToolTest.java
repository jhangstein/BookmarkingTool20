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
















    //ToDo: Delete old tests

    /*@BeforeAll
    @Disabled
    public static void classSetUp() {
        //HACK: for demonstration purposes only
        System.out.println(
                "This is a CalculatorTest class method and takes place before any @Test is executed");
    }

    @AfterAll
    @Disabled
    public static void classTearDown() {
        //HACK: for demonstration purposes only
        System.out.println(
                "This is a CalculatorTest class method and takes place after all @Test are executed");
    }

    @BeforeEach
    @Disabled
    public void setUp() {
        //HACK: for demonstration purposes only
        System.out.println(
                "\tThis call takes place before each @Test is executed");
    }

    @AfterEach
    @Disabled
    public void tearDown() {
        //HACK: for demonstration purposes only
        System.out.println(
                "\tThis call takes place after each @Test is executed");
    }

    @Test
    @Disabled
    public void failingTest() {
        fail("a disabled failing test");
    }



    *//**
     * Test to ensure that the file creation method actually creates a file.
     * Creates a new file, checks for existence, and deletes the file on exiting.
     * @throws IOException If an input or output exception occurs
     *//*
    @Test
    @Disabled
    public void ensureFileCreationCreatesNewFile() throws IOException {
        // Act
        File file = new BookmarkingTool().createFile("test.txt");

        // Assert
        assertTrue(file.exists());
        file.deleteOnExit();
    }


    *//**
     * Test to ensure that the file creation method doesn't allow duplicate files.
     * Tries to create duplicate files, asserts equals (as the method should return the same file twice),
     * and deletes the files on exiting.
     * @throws IOException If an input or output exception occurs
     *//*
    @Test
    @Disabled
    public void ensureFileCreationDoesntCreateDuplicateFile() throws IOException {
        // Act
        File file = new BookmarkingTool().createFile("test.txt");
        File file2 = new BookmarkingTool().createFile("test.txt");

        // Assert
        assertEquals(file, file2);
        file.deleteOnExit();
    }


    *//**
     * Test to ensure that a provided URL is valid, using the Oracle Commons Validator Lib.
     *//*
    @Test
    @Disabled
    public void ensureUrlIsValid() {
        // Arrange
        BookmarkingTool bt = new BookmarkingTool();
        boolean valid;

        // Act
        valid = bt.validateUrl("https://github.com/SnG1205/BookmarkingTool");

        // Assert
        assertTrue(valid);
    }


    *//**
     * Test to ensure that a provided URL is NOT valid (invalid TLD), using the Oracle Commons Validator Lib.
     *//*
    @Test
    @Disabled
    public void ensureUrlIsNotValid() {
        // Arrange
        BookmarkingTool bt = new BookmarkingTool();
        boolean valid;

        // Act
        valid = bt.validateUrl("https://github.con");

        // Assert
        assertFalse(valid);
    }


    *//**
     * Test to ensure that a provided URL is added to the provided file. If the provided URL up to the first
     * regex split is present in the given file (as it should be), the test will pass.
     * @throws IOException If an input or output exception occurs
     *//*
    @Test
    @Disabled
    public void ensureUrlIsAdded() throws IOException {

        // Arrange
        BookmarkingTool bookmarkingTool = new BookmarkingTool();
        String url = "https://github.com/SnG1205/BookmarkingTool";
        boolean assertion = false;
        File file = new BookmarkingTool().createFile("testAdd.txt");

        // Act
        bookmarkingTool.addURL(url, "testAdd.txt", "github");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        while((line = br.readLine()) != null){
            if (line.split(" ")[0].equals(url)){
                assertion = true;
            }
        }

        // Assert
        assertTrue(assertion);
    }


    // ToDo: Refactor for more clarity
    *//**
     * Test to ensure that a URL that has not been added doesn't show up in the search. This test attempts to add a baeldung.com URL
     * using a different domain's tag. It then checks for the hltv domain, and passes if it doesn't find it.
     * @throws IOException If an input or output exception occurs
     *//*
    @Test
    @Disabled
    public void ensureNotBookmarkedUrlReturnsFalse() throws IOException {
        // Arrange
        BookmarkingTool bookmarkingTool = new BookmarkingTool();
        String url = "https://www.baeldung.com/java-write-to-file";
        String wrongUrl = "https://www.hltv.org/";
        boolean assertion = false;
        File file = new BookmarkingTool().createFile("testAdd.txt");

        // Act
        bookmarkingTool.addURL(url, "testAdd.txt", "hltv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        while((line = br.readLine()) != null){
            if (line.split(" ")[0].equals(wrongUrl)){
                assertion = true;
            }
        }

        // Assert
        assertFalse(assertion);
    }


    *//**
     * This test ensures that a new URL is properly added to a RandomAccessFile.
     * It was added to increase Code Coverage.
     * @throws IOException If an input or output exception occurs
     *//*
    @Test
    @Disabled
    //@Description("Added to Increase Code Coverage")
    public void EnsureNewUrlGetsAdded() throws IOException {
        // Arrange
        BookmarkingTool bookmarkingTool = new BookmarkingTool();
        String url = "https://www.javatpoint.com/how-to-remove-last-character-from-string-in-java";
        boolean assertion = false;
        File file = new BookmarkingTool().createFile("testAdd.txt");

        // Act
        bookmarkingTool.addURL(url, "testAdd.txt", "javatpoint");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        while((line = br.readLine()) != null){
            if (line.split(" ")[0].equals(url)){
                assertion = true;
            }
        }

        // Assert
        assertTrue(assertion);

        RandomAccessFile f = new RandomAccessFile("testAdd.txt", "rw");
        long length = f.length() - 1;
        byte b;
        do {
            length -= 1;
            f.seek(length);
             b = f.readByte();
        } while(b != 10);
        f.setLength(length+1);
        f.close();
    }


    *//**
     * This test ensures that a tag provided alongside an URL is properly added. It creates a file, adds URL + tag,
     * and then checks the split lines (URL and tag respectively) to match the ones added.
     * @throws IOException If an input or output exception occurs
     *//*
    @Test
    @Disabled
    public void EnsureThatTagIsAdded() throws IOException {
        BookmarkingTool bookmarkingTool = new BookmarkingTool();

        String url = "https://www.baeldung.com/java-write-to-file";
        String tag = "baeldung";
        boolean assertion = false;

        File file = new BookmarkingTool().createFile("testTags.txt");
        bookmarkingTool.addURL(url, "testTags.txt", tag);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";


        while((line = br.readLine()) != null){
            if (line.split(" ")[0].equals(url)){
                if (line.split(" ")[2].equals(tag)){
                    assertion = true;
                }
            }

        }
        assertTrue(assertion);
    }


    // ToDo: Rewrite test, momentarily it doesn't actually test anything?
    *//**
     * This test ensures that the right tag (belonging to the right domain) is used when adding an URL.
     * It creates a file, adds a URL with a tag belonging to another domain, and then checks for a *correct*
     * set of URL + tag (correlating URL and domain tag).
     * @throws IOException If an input or output exception occurs
     *//*
    @Test
    @Disabled
    public void EnsureThatTheRightTagIsAdded() throws IOException {
        BookmarkingTool bookmarkingTool = new BookmarkingTool();

        String url = "https://www.hltv.org/";
        String tag = "hltv";
        boolean assertion = false;

        File file = new BookmarkingTool().createFile("testTags.txt");
        bookmarkingTool.addURL(url, "testTags.txt", "baeldung");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";


        while((line = br.readLine()) != null){
            if (line.split(" ")[0].equals(url)){
                if (line.split(" ")[2].equals(tag)){
                    assertion = true;
                }
            }

        }
        assertFalse(assertion);
    }


    *//**
     * This test ensures that duplicate URLs added to a file are properly detected and deleted.
     * It creates a file, adds a URL three times (once with a different tag), and then
     * checks the count of the given URL in the file. If the "no duplicates" expectation is met, the test passes.
     * @throws IOException If an input or output exception occurs
     *//*
    @Test
    @Disabled
    public void EnsureThatTheDuplicateIsFound() throws IOException {
        BookmarkingTool bookmarkingTool = new BookmarkingTool();

        String url = "https://www.hltv.org/";
        String tag = "hltv";
        int counter = 0;
        int expected = 1;

        File file = new BookmarkingTool().createFile("testDuplicates.txt");
        bookmarkingTool.addURL(url, "testDuplicates.txt", tag);
        bookmarkingTool.addURL(url, "testDuplicates.txt", tag);
        bookmarkingTool.addURL(url, "testDuplicates.txt", "different tag");

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        while((line = br.readLine()) != null){
            counter++;
        }


        assertEquals(expected, counter);
    }


    *//**
     * This test ensures that the method to check for secure URLs properly returns all URLs using HTTPS.
     * It creates a file, adds multiple URLs to it, filters for secure URLs and compares them to the expected output.
     * @throws IOException If an input or output exception occurs
     *//*
    @Test
    @Disabled
    public void EnsureThatSecureUrlsAreListedCorrectly() throws IOException {
        BookmarkingTool bookmarkingTool = new BookmarkingTool();


        File file = new BookmarkingTool().createFile("testSecure.txt");
        bookmarkingTool.addURL("https://www.hltv.org/", "testSecure.txt", "hltv");
        bookmarkingTool.addURL("https://www.baeldung.com/java-write-to-file", "testSecure.txt", "baeldung");
        bookmarkingTool.addURL("https://github.com/SnG1205/BookmarkingTool", "testSecure.txt", "different tag");
        bookmarkingTool.addURL("http://info.cern.ch/", "testSecure.txt", "cern");

        List<String> secureList = bookmarkingTool.checkForSecureURLs("testSecure.txt");

        List<String> expectedList = new ArrayList<>();
        expectedList.add("https://www.hltv.org/");
        expectedList.add("https://www.baeldung.com/java-write-to-file");
        expectedList.add("https://github.com/SnG1205/BookmarkingTool");

        assertEquals(secureList, expectedList);

    }


    *//**
     * This test ensures that the filter by keyword/tag method properly returns all URLs using the specified tag.
     * It creates a file, adds multiple URLs using different tags, and filters by one tag.
     * Then it compares an expected output list to actual output list.
     * @throws IOException If an input or output exception occurs
     *//*
    @Test
    @Disabled
    public void EnsureThatFilterWorksCorrectly() throws IOException {
        BookmarkingTool bookmarkingTool = new BookmarkingTool();


        File file = new BookmarkingTool().createFile("testFilterByWord.txt");
        bookmarkingTool.addURL("https://www.hltv.org/", "testFilterByWord.txt", "hltv");
        bookmarkingTool.addURL("https://www.baeldung.com/java-write-to-file", "testFilterByWord.txt", "baeldung");
        bookmarkingTool.addURL("https://github.com/SnG1205/BookmarkingTool", "testFilterByWord.txt", "different tag");
        bookmarkingTool.addURL("http://info.cern.ch/", "testFilterByWord.txt", "cern");
        bookmarkingTool.addURL("https://github.com/",  "testFilterByWord.txt", "github");
        bookmarkingTool.addURL("https://github.com/issues",  "testFilterByWord.txt", "github");
        bookmarkingTool.addURL("https://github.com/pulls",  "testFilterByWord.txt", "github");

        List<String> secureList = bookmarkingTool.filterURLs("testFilterByWord.txt", "github");

        List<String> expectedList = new ArrayList<>();
        expectedList.add("https://github.com/SnG1205/BookmarkingTool");
        expectedList.add("https://github.com/");
        expectedList.add("https://github.com/issues");
        expectedList.add("https://github.com/pulls");

        assertEquals(secureList, expectedList);

    }
*/
}