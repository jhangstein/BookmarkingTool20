package pt.ipp.isep.dei.examples.tdd.basic.ui;

import pt.ipp.isep.dei.examples.tdd.basic.domain.BookmarkingTool;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BookmarkingTool fileWriter = new BookmarkingTool();
        File toWrite = new File("solvingissues.txt");

        fileWriter.addURL("https://github.com/SnG1205/BookmarkingTool", toWrite );
        fileWriter.addURL("https://www.baeldung.com/java-write-to-file", toWrite );

    }
}
