package pt.ipp.isep.dei.examples.tdd.basic.ui;

import pt.ipp.isep.dei.examples.tdd.basic.domain.Bookmark;
import pt.ipp.isep.dei.examples.tdd.basic.domain.BookmarkingTool;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Bookmark bm = new Bookmark("https://facebook.com");
        System.out.println(bm.getDate());
    }
}
