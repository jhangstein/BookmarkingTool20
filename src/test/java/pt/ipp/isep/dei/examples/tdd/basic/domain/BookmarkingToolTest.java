package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.apache.commons.validator.routines.UrlValidator;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

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
}