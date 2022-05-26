package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.awt.print.Book;
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


    public BookmarkingTool() {
    }


    /**
     * Method adds a bookmark to the tool if the provided URL is valid and it isn't already added.
     * If it isn't already in the tool, the bookmark gets added and its rating set to one.
     * @param bm    Bookmark to be added
     */
    public void addBookmark(Bookmark bm){
        UrlValidator validator = new UrlValidator();
        if (validator.isValid(bm.getURL()) && checkForDuplicate(bm)){
            bm.increaseRating();
            allBookmarks.add(bm);
        }
    }


    public void removeBookmark(String URL){
        throw new UnsupportedOperationException();
    }


    /**
     * Method checks for duplicate bookmarks in the tool. If it finds a duplicate, it increases that bookmarks
     * rating by one.
     * @param bm    Bookmark to be checked for duplicate
     * @return      True if no duplicate can be found, false if one is found
     */
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


    /**
     * Method gets all bookmark's URLs using the HTTPS protocol.
     * @return  count of secure URLs using HTTPS
     */
    public int getSecureUrlCount(){
        int count = 0;
        for (Bookmark bm: allBookmarks){
            if (bm.getURL().contains("https")) {
                count++;
            }
        }
        return count;
    }


    /**
     * Method filters the tool by provided keyword, and groups matching items in a new list.
     * @param tag   keyword/tag to filter by
     * @return      list with all associated bookmarks
     */
    public List<Bookmark> filterByKeyword(String tag){
        List<Bookmark> keywordList = new ArrayList<>();

        for (Bookmark bm: allBookmarks){
            if (bm.getTag().equals(tag)){
                keywordList.add(bm);
            }
        }
        return keywordList;
    }








/*    *//*
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


    *//*
    public boolean validateUrl(String url){
        UrlValidator urlValidator = new UrlValidator();

        return urlValidator.isValid(url);
    }


    *//*
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



        *//*printWriter.write(url + '\n');
        printWriter.newLine();
        printWriter.close();*//*

    }


    *//*
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


    *//*
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
    }*/

}
