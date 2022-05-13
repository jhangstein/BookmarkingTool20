package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;




/**
 * Calculator class.
 * This class is very simple in order to demonstrate how to build test cases for Unit Testing.
 */
public class BookmarkingTool {




    public BookmarkingTool() throws IOException {
    }

    public File createFile(String pathName) throws IOException {
        File fileToCreate = new File(pathName);
        if (fileToCreate.createNewFile()) {
            System.out.println("File created: " + fileToCreate.getName());
        } else {
            System.out.println("File already exists.");
        }

        return fileToCreate;
    }


}
