import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Citation {
    private String dateAcc;
    private String datePosted;
    private String author;
    private String url;
    private String publisher;
    private String title;
    private String webTitle;

    public Citation (String datePosted, String author, String url, String publisher, String title, String webTitle) {
        this.datePosted = datePosted;
        this.author = author;
        this.url = url;
        this.publisher = publisher;
        this.title = title;
        this.webTitle = webTitle;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        dateAcc = dtf.format(localDate);

        //bad date format, fix
    }

    public String getFullMLA () {
        String properName = "";
        for (int i = 0; i < author.length(); i ++) {

        }
        return properName + "\"" + title + ".\" " + webTitle + ". " + publisher + ", " +
                datePosted + ". Web. " + dateAcc;
    }
}