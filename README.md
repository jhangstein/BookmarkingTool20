# Bookmarking Tool
Switching to an OOP approach, redoing the project.

Starting with User Story 1: As a user I want to bookmark an URL

User Story 1a: The URL must be valid






## Bookmark URL

- Create File to store URLs in (only for the first URL)
- Check URL for validity
- Add  URL to created File


## Tag URL by word

- Add parameter "tag" to "addUrl" method, so that this tag appears after URL in .txt file
  
### Tests
1. Ensure that the tag is assigned to a URL.
2. Ensure that the right tag is assigned to a URL. In case another tag was assigned, test should fail.

## Duplicate Detection
-If given URL is duplicate of the one that already exists, it should not be added to the list.


## Secure URLs
- Read from file line by line, if URL contains "https", then it is secure.
- Add Secure file to a List.
- Return List.

## Filtering Bookmarks
- Take a keyword as a parameter.
- Read from file line by line, if URL contains the keyword.
- Add this URL to a List.
- Return List.
