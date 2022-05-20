package pt.ipp.isep.dei.examples.tdd.basic.domain;

import jdk.jfr.Description;
import org.apache.commons.validator.routines.UrlValidator;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookmarkingToolTest {

    @BeforeAll
    public static void classSetUp() {
        //HACK: for demonstration purposes only
        System.out.println(
                "This is a CalculatorTest class method and takes place before any @Test is executed");
    }

    @AfterAll
    public static void classTearDown() {
        //HACK: for demonstration purposes only
        System.out.println(
                "This is a CalculatorTest class method and takes place after all @Test are executed");
    }

    @BeforeEach
    public void setUp() {
        //HACK: for demonstration purposes only
        System.out.println(
                "\tThis call takes place before each @Test is executed");
    }

    @AfterEach
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

    /**
     * Test to ensure two positive numbers are summed correctly.<p>
     * <p>
     * For demonstration purposes the Arrange/Act/Assert syntax is used:<p>
     * Arrange: one positive number (three) and another positive number (two).<p>
     * Act: sum both numbers (three and two).<p>
     * Assert: the result is five.
     */


    /**
     * Test to ensure positive and negative numbers are summed correctly.<p>
     * <p>
     * For demonstration purposes the Arrange/Act/Assert syntax is used:<p>
     * Arranje a positive number (three) and a negative number (minus two)<p>
     * Act I sum three to minus two<p>
     * Assert the sum result should be one.
     */
   /* @Test
    public void ensureThreePlusMinusTwoEqualsOne() {
        //HACK: for demonstration purposes only
        System.out.println("\t\tExecuting " + new Object() {
        }.getClass().getEnclosingMethod().getName() + " Test");

        // Arrange
        int firsOperand = 3;
        int secondOperand = -2;
        int expectedResult = 1;
        int result = 3;

        // Act
        result = new Calculator().sum(firsOperand, secondOperand);

        // Assert
        assertEquals(expectedResult, result);
    }*/

    @Test
    public void ensureFileCreationCreatesNewFile() throws IOException {
        // Act
        File file = new BookmarkingTool().createFile("test.txt");

        // Assert
        assertTrue(file.exists());
        file.deleteOnExit();
    }

    @Test
    public void ensureFileCreationDoesntCreateDuplicateFile() throws IOException {
        // Act
        File file = new BookmarkingTool().createFile("test.txt");
        File file2 = new BookmarkingTool().createFile("test.txt");

        // Assert
        assertEquals(file, file2);
        file.deleteOnExit();
    }

    @Test
    public void ensureUrlIsValid() throws IOException {
        assertTrue(new BookmarkingTool().validateUrl("https://github.com/SnG1205/BookmarkingTool"));
    }

    @Test
    public void ensureUrlIsNotValid() throws IOException {
        assertFalse(new BookmarkingTool().validateUrl("https://github.con"));
    }

    @Test
    public void ensureUrlIsAdded() throws IOException {
        BookmarkingTool bookmarkingTool = new BookmarkingTool();

        String url = "https://github.com/SnG1205/BookmarkingTool";
        boolean assertion = false;

        File file = new BookmarkingTool().createFile("testAdd.txt");
        bookmarkingTool.addURL(url, "testAdd.txt", "github");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";


        while((line = br.readLine()) != null){
            if (line.split(" ")[0].equals(url)){
                assertion = true;
            }
        }
        assertTrue(assertion);
    }

    @Test
    public void ensureNotBookmarkedUrlReturnsFalse() throws IOException {
        BookmarkingTool bookmarkingTool = new BookmarkingTool();

        String url = "https://www.baeldung.com/java-write-to-file";
        String wrongUrl = "https://www.hltv.org/";
        boolean assertion = false;

        File file = new BookmarkingTool().createFile("testAdd.txt");
        bookmarkingTool.addURL(url, "testAdd.txt", "hltv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";


        while((line = br.readLine()) != null){
            if (line.split(" ")[0].equals(wrongUrl)){
                assertion = true;
            }
        }
        assertFalse(assertion);
    }

    @Test
    @Description("Added to Increase Code Coverage")
    public void EnsureNewUrlGetsAdded() throws IOException {
        BookmarkingTool bookmarkingTool = new BookmarkingTool();

        String url = "https://www.javatpoint.com/how-to-remove-last-character-from-string-in-java";
        boolean assertion = false;

        File file = new BookmarkingTool().createFile("testAdd.txt");
        bookmarkingTool.addURL(url, "testAdd.txt", "javatpoint");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";


        while((line = br.readLine()) != null){
            if (line.split(" ")[0].equals(url)){
                assertion = true;
            }
        }
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


    @Test
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

    @Test
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

    @Test
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


    @Test
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


    @Test
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

}