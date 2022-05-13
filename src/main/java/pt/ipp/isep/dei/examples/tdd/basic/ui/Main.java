package pt.ipp.isep.dei.examples.tdd.basic.ui;

import pt.ipp.isep.dei.examples.tdd.basic.domain.BookmarkingTool;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BookmarkingTool bookmarkingTool = new BookmarkingTool();
        bookmarkingTool.addURL("https://github.com/SnG1205/BookmarkingTool");
        bookmarkingTool.addURL("https://www.baeldung.com/java-write-to-file");
        bookmarkingTool.addURL("https://github.com/SnG1205/BookmarkingTool");
    }
}
