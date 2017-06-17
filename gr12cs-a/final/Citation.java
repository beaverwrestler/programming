/* Name: Artin S.
 * Due Date: Friday, June 16, 2017
 * Description: This class hold information about the citation; author, date, etc. It also has a method to
 *              determine the proper name to be used for the author
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Citation {
    private String dateAcc;     //variables
    private String datePosted;
    private String author;
    private String url;
    private String publisher;
    private String title;
    private String webTitle;

    //constructor
    //parameters are date posted, author name, url or website, publisher, title, website title, and the date accessed
    Citation (String datePosted, String author, String url, String publisher, String title, String webTitle, String dateAcc) {
        this.datePosted = datePosted;
        this.author = author;
        this.url = url;
        this.publisher = publisher;
        this.title = title;
        this.webTitle = webTitle;

        //sets the date accessed to the current day if one has not been already provided
        if (dateAcc != null && !dateAcc.equals(""))
            this.dateAcc = dateAcc;
        else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            this.dateAcc = dtf.format(localDate);
        }
    }

    String getFullMLA () {  //formats and returns all of the necessary data in an MLA citation
        String properName = getProperName();
        return properName + ". \"" + title + ".\" " + webTitle + ". " + publisher + ", " +
                datePosted + ". Web. " + dateAcc;
    }

    String getFullAPA () {      //formats and returns all of the necessary data in APA form
        String properName = getProperName();
        return properName + ". (" + dateAcc + "). " + title + ". Retrieved from " + url;
    }

    //returns string, no parameters
    //separates the normally written name of the author into Last, F.
    private String getProperName () {
        String last = "", first;
        String authorTrim = author.trim();

        if (authorTrim.length()>0) {    //makes sure the author's name is not blank
            if (!authorTrim.contains(" "))
                return authorTrim.substring(0, 1).toUpperCase() + authorTrim.substring(1);
            for (int i = authorTrim.length() - 1; i > -1; i--)
                if (authorTrim.charAt(i) == ' ') {      //find the first space from the back, indicates last name
                    last = authorTrim.substring(i);
                    last = last.trim();
                    break;
                }
            first = authorTrim.substring(0, 1).toUpperCase();   //capitlises first letter
            last = last.substring(0, 1).toUpperCase() + last.substring(1);
            return last + ", " + first; //combine and return
        }
        return "";
    }

    //lots of getters and setters
    String getDateAcc () {
        return dateAcc;
    }
    String getDatePosted () {
        return datePosted;
    }
    String getAuthor () {
        return author;
    }
    String getUrl () {
        return url;
    }
    String getPublisher () {
        return publisher;
    }
    String getTitle () {
        return title;
    }
    String getWebTitle () {
        return webTitle;
    }

    void setDateAcc (String a) {
        dateAcc = a;
    }
    void setAuthor (String a) {
        author = a;
    }
    void setDatePosted (String a) {
        datePosted = a;
    }
    void setUrl (String a) {
        url = a;
    }
    void setPublisher (String a) {
        publisher = a;
    }
    void setTitle (String a) {
        title = a;
    }
    void setWebTitle (String a) {
        webTitle = a;
    }
}