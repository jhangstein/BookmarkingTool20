package pt.ipp.isep.dei.examples.tdd.basic.ui;

import pt.ipp.isep.dei.examples.tdd.basic.domain.BookmarkingTool;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BookmarkingTool bookmarkingTool = new BookmarkingTool();
        bookmarkingTool.addURL("https://github.com/SnG1205/BookmarkingTool", "solvingissues.txt", "github");
        bookmarkingTool.addURL("https://www.baeldung.com/java-write-to-file", "solvingissues.txt", "baeldung");
        bookmarkingTool.addURL("https://github.com/SnG1205/BookmarkingTool", "solvingissues.txt", "github");
        bookmarkingTool.addURL("https://www.hltv.org/", "solvingissues.txt", "hltv");
        bookmarkingTool.addURL("https://www.hltv.org/matches", "solvingissues.txt", "hltv");
        bookmarkingTool.addURL("https://www.hltv.org/matches/2356336/detonate-vs-strife-esl-challenger-valencia-2022-north-america-open-qualifier-1", "solvingissues.txt", "hltv");

        /*List<String> list = bookmarkingTool.checkForSecureURLs("solvingissues.txt");
        list.forEach(System.out::println);*/
        List<String>  filteredList = bookmarkingTool.filterURLs("solvingissues.txt", "hltv");
        filteredList.forEach(System.out::println);
    }
}
