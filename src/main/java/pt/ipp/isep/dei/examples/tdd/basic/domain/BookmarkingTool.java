package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.validator.routines.*;
import org.apache.commons.io.*;


/**
 * Calculator class.
 * This class is very simple in order to demonstrate how to build test cases for Unit Testing.
 */
public class BookmarkingTool {

    /*FileWriter fileWriter = new FileWriter("solvingissues.txt");
    PrintWriter printWriter = new PrintWriter(fileWriter, true);
    Path path = Paths.get("solvingissues.txt");*/


    public BookmarkingTool() throws IOException {
    }

    public File createFile(String pathName) throws IOException {
        File fileToCreate = new File(pathName);
        if (fileToCreate.createNewFile()) {
            System.out.println("File created: ");
        } else {
            System.out.println("File already exists.");
        }

        return fileToCreate;
    }


    public boolean validateUrl(String url){
        UrlValidator urlValidator = new UrlValidator();

        return urlValidator.isValid(url);
    }

    public void  addURL(String url, String fileName, String tag) throws MalformedURLException, IOException {
        URL urlToSave = new URL(url);
        int count = 0;

        System.out.println(urlToSave.getProtocol());
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        while((line = br.readLine()) != null){
            if (line.split(" ")[0].equals(url)){
                count++;
            }
        }
        if (count == 0) {
            FileUtils.writeStringToFile(
                    new File(fileName), url + " - " + tag + '\n', StandardCharsets.UTF_8, true);
        }



        /*printWriter.write(url + '\n');
        printWriter.newLine();
        printWriter.close();*/

    }

}
