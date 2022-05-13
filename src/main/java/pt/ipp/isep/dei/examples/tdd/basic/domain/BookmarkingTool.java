package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;





/**
 * Calculator class.
 * This class is very simple in order to demonstrate how to build test cases for Unit Testing.
 */
public class BookmarkingTool {

    FileWriter fileWriter = new FileWriter("solvingissues.txt");



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


    public boolean validateUrl(String url){
        return true;
    }

    public void  addURL(String url, File fileToWrite) throws MalformedURLException, IOException {
        URL urlToSave = new URL(url);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        System.out.println(urlToSave.getProtocol());




        printWriter.write(url);
        //printWriter.newLine();
        printWriter.close();

    }

}
