package pt.ipp.isep.dei.examples.tdd.basic.ui;

import pt.ipp.isep.dei.examples.tdd.basic.domain.Bookmark;
import pt.ipp.isep.dei.examples.tdd.basic.domain.BookmarkingTool;

public class Main {

    public static void main(String[] args) {
        // The contents of the CLI application proper were not finished and disabled, as they
        // were not necessary for the purpose of applying TDD to a project.




        /*BookmarkingTool tool = new BookmarkingTool();

        while (true){
            System.out.println("Welcome to the Bookmarking Tool. Please select a function by choosing a number.");
            System.out.println("1 - Add Bookmark");
            System.out.println("2 - Delete Bookmark");
            System.out.println("3 - Filter Bookmark by tag");
            System.out.println("4 - Check number of secure Bookmarks");
            System.out.println("5 - Remove Tag from URL");
            System.out.println("6 - Sort Bookmarks by rating");
            System.out.println("7 - Exit");

            int input = scan.nextInt();

            switch (input){
                case 1:
                    System.out.println("Please enter a URL you want to add:");
                    String URL = scan.next();
                    System.out.println("Do you want to add a tag to the URL you provided (If not, leave empty)?");
                    String tag = scan.next();
                    Bookmark bm = new Bookmark(URL, tag);
                    if (bm.getURL().equals("")){
                        System.out.println("Invalid input.");
                    } else {
                        tool.addBookmark(bm);
                        System.out.println("Bookmark successfully added.");
                    }
                    break;
                case 2:
                    System.out.println("Add functionality for deleting bookmarks");
                    break;
                case 3:
                    System.out.println("Please provide a tag:");
                    String tagToSearch = scan.next();
                    System.out.println(tool.filterByKeyword(tagToSearch));
                    break;
                case 7:
                    System.out.println("Thank you for using the tool :)");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }

            if (input == 7){
                break;
            }
        }*/
    }
}
