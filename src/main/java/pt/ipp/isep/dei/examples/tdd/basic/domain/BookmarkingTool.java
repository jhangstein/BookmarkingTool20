package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.*;
import org.apache.commons.io.*;


/**
 * BookmarkingTool class.
 * This class provides functionalities to create bookmarking files, validate URLs and adding them,
 * check for protocols used, group the URLs by keyword, and filter them by keyword.
 */
public class BookmarkingTool {

    List<Bookmark> allBookmarks = new ArrayList<>();



    /*FileWriter fileWriter = new FileWriter("solvingissues.txt");
    PrintWriter printWriter = new PrintWriter(fileWriter, true);
    Path path = Paths.get("solvingissues.txt");*/


    public BookmarkingTool() {
    }


    public void addBookmark(Bookmark bm){
        UrlValidator validator = new UrlValidator();
        if (validator.isValid(bm.getURL()) && checkForDuplicate(bm)){
            bm.increaseRating();
            allBookmarks.add(bm);
        }
    }


    public boolean checkForDuplicate(Bookmark bm){
        // Not doing it with .contains() function because of object equality
        for (Bookmark bookmark: allBookmarks){
            if (bookmark.getURL().equals(bm.getURL())){
                bookmark.increaseRating();
                return false;
            }
        }
        return true;
    }

    public int getSecureUrlCount(){
        int count = 0;
        for (Bookmark bm: allBookmarks){
            if (bm.getURL().contains("https")) {
                count++;
            }
        }
        return count;
    }


    public List<Bookmark> filterByKeyword(String tag){
        List<Bookmark> keywordList = new ArrayList<>();

        for (Bookmark bm: allBookmarks){
            if (bm.getTag().equals(tag)){
                keywordList.add(bm);
            }
        }
        return keywordList;
    }








    /**
     * Method creates a new file and checks for duplicates.
     * @param pathName      path to file and file name
     * @return              created file
     * @throws IOException  If an input or output exception occurs
     */
    public File createFile(String pathName) throws IOException {
        File fileToCreate = new File(pathName);
        if (fileToCreate.createNewFile()) {
           //TODO delete the following line
            System.out.println("File created: ");
        } else {
            //TODO delete the following line
            System.out.println("File already exists.");
        }

        return fileToCreate;
    }


    /**
     * Method validates provided URL by utilizing URLValidator Lib.
     * @param url   provided URL String to validate
     * @return      bool whether URL is valid or not
     */
    public boolean validateUrl(String url){
        UrlValidator urlValidator = new UrlValidator();

        return urlValidator.isValid(url);
    }


    /**
     * Method checks for HTTP/S protocol, and adds provided URL + Tag to the provided file
     * if it isn't already present in the given file.
     * @param url           URL to add to file
     * @param fileName      file path to add content to
     * @param tag           Tag to append to URL
     * @throws IOException  If an input or output exception occurs
     */
    public void  addURL(String url, String fileName, String tag) throws IOException {
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


    /**
     * Method checks for secure URLs (using HTTPS) and appends them to a list.
     * @param fileName      file path to read from
     * @return              List with all secure URLs from provided file
     * @throws IOException  If an input or output exception occurs
     */
    public List<String> checkForSecureURLs(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        List<String> secureUrls = new ArrayList<>();


        while((line = br.readLine()) != null){
            if (line.split(":")[0].equals("https")){
                secureUrls.add(line.split(" ")[0]);
            }

        }
        return secureUrls;
    }


    /**
     * Method filters URLs by a provided keyword/tag.
     * @param fileName      file path to read from
     * @param keyword       provided keyword/tag to filter by
     * @return              List with URLs filtered by keyword/tag
     * @throws IOException  If an input or output exception occurs
     */
    public List<String> filterURLs(String fileName, String keyword) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        List<String> filteredUrls = new ArrayList<>();


        while((line = br.readLine()) != null){
            if (line.split("/")[2].contains(keyword)){
                filteredUrls.add(line.split(" ")[0]);
            }

        }
        return filteredUrls;
    }

}
